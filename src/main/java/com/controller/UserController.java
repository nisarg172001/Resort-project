package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.Userdao;
import com.service.Services;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if(action.equalsIgnoreCase("insert"))
		{
			boolean flag=Userdao.checkemail("email");
			if(flag==true)
			{
				if(request.getParameter("password").equals(request.getParameter("cpassword")))
				{
							User u=new User();
					u.setFname(request.getParameter("fname"));
					u.setLname(request.getParameter("lname"));
					u.setMobile(request.getParameter("mobile"));
					u.setEmail(request.getParameter("email"));
					u.setAddress(request.getParameter("address"));
					u.setPassword(request.getParameter("password"));
					u.setCpassword(request.getParameter("cpassword"));
					Userdao.signupuser(u);
					response.sendRedirect("login.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password & Confirm Password Does Not Matched!");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				
				}
		    }
		else
		{
			request.setAttribute("msg", "Email Already Registered!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}
		else if (action.equalsIgnoreCase("login"))
		{
			User u=Userdao.loginuser(request.getParameter("email"), request.getParameter("password"));
			if(u==null)
			{
				request.setAttribute("msg", "Email Or Password Is Incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("u", u);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		else if (action.equalsIgnoreCase("change password"))
		{
			HttpSession session=request.getSession();
			User u=(User)session.getAttribute("u");
			if(u.getPassword().equals(request.getParameter("oldpassword")))
			{
				if(request.getParameter("newpassword").equals(request.getParameter("cnewpassword")))
				{
					Userdao.changepassword(u.getEmail(), request.getParameter("newpassword"));
					response.sendRedirect("logout.jsp");
				}
				else
				{
					request.setAttribute("msg", "Password & Confirm Password Does Not Matched!");
					request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
				request.getRequestDispatcher("otp.jsp").forward(request, response);
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
				request.getRequestDispatcher("newpassword.jsp").forward(request, response);
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
				response.sendRedirect("login.jsp");
			}
		}
		
		
  }

}
