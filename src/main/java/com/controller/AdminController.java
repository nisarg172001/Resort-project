package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.admin;
import com.dao.Admindao;
import com.dao.Userdao;
import com.service.Services;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("Verify PIN"))
		{
			String pin=Admindao.verifypin(request.getParameter("pin"));
			String p="1234";
			if(pin.equals(p))
			{
				response.sendRedirect("adminsignup.jsp");
			}
			else
			{
				request.setAttribute("msg", "Pin Does Not Matched!");
				request.getRequestDispatcher("adminpin.jsp").forward(request, response);
			}
		}
		if(action.equalsIgnoreCase("insert"))
		{
			boolean flag=Admindao.checkemail("email");
			if(flag==true)
			{
				if(request.getParameter("password").equals(request.getParameter("cpassword")))
				{
					admin a=new admin();
					a.setFname(request.getParameter("fname"));
					a.setLname(request.getParameter("lname"));
					a.setMobile(request.getParameter("mobile"));
					a.setEmail(request.getParameter("email"));
					a.setAddress(request.getParameter("address"));
					a.setPassword(request.getParameter("password"));
					a.setCpassword(request.getParameter("cpassword"));
					Admindao.signupadmin(a);
					response.sendRedirect("adminlogin.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password & Confirm Password Does Not Matched!");
					request.getRequestDispatcher("adminsignup.jsp").forward(request, response);
				
				}
		    }
		else
		{
			request.setAttribute("msg", "Email Already Registered!");
			request.getRequestDispatcher("adminsignup.jsp").forward(request, response);
		}
	}
		else if (action.equalsIgnoreCase("login"))
		{
			admin a=Admindao.loginadmin(request.getParameter("email"), request.getParameter("password"));
			if(a==null)
			{
				request.setAttribute("msg", "Email Or Password Is Incorrect");
				request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("a", a);
				request.getRequestDispatcher("adminindex.jsp").forward(request, response);
			}
		}
		else if (action.equalsIgnoreCase("change password"))
		{
			HttpSession session=request.getSession();
			admin a=(admin)session.getAttribute("a");
			if(a.getPassword().equals(request.getParameter("oldpassword")))
			{
				if(request.getParameter("newpassword").equals(request.getParameter("cnewpassword")))
				{
					Admindao.changepassword(a.getEmail(), request.getParameter("newpassword"));
					response.sendRedirect("adminlogout.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password & Confirm Password Does Not Matched!");
					request.getRequestDispatcher("adminchangepassword.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("msg", "Old Password Is Incorrect");
				request.getRequestDispatcher("changepassword.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("Send OTP"))
		{
			boolean flag=Userdao.checkemail(request.getParameter("email"));
			if(flag==true)
			{
				int val=((int)(Math.random()*9000)+1000);
				System.out.println(val);
				Services.sendMail(request.getParameter("email"), val);
				request.setAttribute("otp", val);
				request.setAttribute("email", request.getParameter("email"));
				request.getRequestDispatcher("adminotp.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("verify otp"))
		{
			String email=request.getParameter("email");
			String otp=request.getParameter("otp");
			String uotp=request.getParameter("uotp");
			
			if(otp.equals(uotp))
			{
				request.setAttribute("email", email);
				request.getRequestDispatcher("adminnewpassword.jsp").forward(request, response);
			}
		}
		else if (action.equalsIgnoreCase("update password"))
		{
			String email=request.getParameter("email");
			String np=request.getParameter("newpassword");
			String cnp=request.getParameter("cnewpassword");
			
			if(np.equals(cnp))
			{
				Userdao.changepassword(email, np);
				response.sendRedirect("adminlogin.jsp");
			}
		}
		
		
  }

  }



