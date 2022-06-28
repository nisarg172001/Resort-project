package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.bean.admin;
import com.util.UserUtil;

public class Admindao {

	public static String verifypin(String pin)
	{
		try {
			Connection conn=UserUtil.createConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pin;
		 
	}
	public static void signupadmin(admin a)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="insert into admin(fname,lname,mobile,email,address,password,cpassword) values(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, a.getFname());
			pst.setString(2, a.getLname());
			pst.setString(3, a.getMobile());
			pst.setString(4, a.getEmail());
			pst.setString(5, a.getAddress());
			pst.setString(6, a.getPassword());
			pst.setString(7, a.getCpassword());
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean checkemail(String email)
	{
		boolean flag=false;
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select * from admin where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,email);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	public static admin loginadmin(String email,String password)
	{
		admin a=null;
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select * from admin where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				a=new admin();
				a.setId(rs.getInt("admin_id"));
				a.setFname(rs.getString("fname"));
				a.setLname(rs.getString("lname"));
				a.setMobile(rs.getString("mobile"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setPassword(rs.getString("password"));
				a.setCpassword(rs.getString("cpassword"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	public static void changepassword(String email,String password)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="update admin set password=?,cpassword=? where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void forgot(String email,String password)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="update admin set password=?,cpassword=? where email=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

	
	


	

