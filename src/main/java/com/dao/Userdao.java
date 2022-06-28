package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.util.UserUtil;

public class Userdao {

	public static void signupuser(User u)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="insert into user(fname,lname,mobile,email,address,password,cpassword) values(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getMobile());
			pst.setString(4, u.getEmail());
			pst.setString(5, u.getAddress());
			pst.setString(6, u.getPassword());
			pst.setString(7, u.getCpassword());
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
			String sql="select * from user where email=?";
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
	public static User loginuser(String email,String password)
	{
		User u=null;
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select * from user where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setAddress(rs.getString("address"));
				u.setPassword(rs.getString("password"));
				u.setCpassword(rs.getString("cpassword"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public static List<User> getAllUser()
	{
		List<User> list=new ArrayList<User>();
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select * from user";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setAddress(rs.getString("address"));
				list.add(u);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static void changepassword(String email,String password)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="update user set password=?,cpassword=? where email=?";
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
			String sql="update user set password=?,cpassword=? where email=?";
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
