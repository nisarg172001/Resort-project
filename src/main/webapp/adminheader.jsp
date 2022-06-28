<%@page import="com.bean.admin" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
  <head>
  </head>
  <body>
  	
    <header class="site-header js-site-header">
    
    
      <div class="container-fluid">
      
        <div class="row align-items-center">
        
          <div class="col-6 col-lg-4 site-logo" data-aos="fade"><a href="index.jsp">Serenity Resort</a></div>
          <div class="col-6 col-lg-8">
          
    
	<div class="site-menu-toggle js-site-menu-toggle"  data-aos="fade">    
              <span></span>
              <span></span>
              <span></span>
            </div>
            
            <!-- END menu-toggle -->
            <div class="site-navbar js-site-navbar">
              <nav role="navigation">
                <div class="container">
                  <div class="row full-height align-items-center">
                    <div class="col-md-6 mx-auto">
                      <ul class="list-unstyled menu">
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <li><a href="rooms.jsp">Rooms</a></li>
                        <li><a href="about.jsp">About</a></li>
                        <li><a href="events.jsp">Events</a></li>
                        <li><a href="contact.jsp">Contact</a></li>
                        <li><a href="reservation.jsp">Reservation</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </nav>
            </div>
            <div align="right">
    <%
	admin a=null;
    if(session!=null)
		{
			if(session.getAttribute("a")!=null)
			{
				a=(admin)session.getAttribute("a");
	%>
				
		  <a href="adminlogout.jsp"><button type="button" class="btn btn-dark btn-sm" >Logout</button></a>
	      <a href="adminchangepassword.jsp"><button type="button" class="btn btn-dark btn-sm">Change Password</button></a>
	
	<%			
			}
			else
			{
	%>
				  <a href="selection.jsp"><button type="button" class="btn btn-dark btn-sm" >Sign Up</button></a>
	     		  <a href="selectionlogin.jsp"><button type="button" class="btn btn-dark btn-sm">Login</button></a>
	
	<%			
			}
		}
		else
		{
	%>
			  <a href="selection.jsp"><button type="button" class="btn btn-dark btn-sm" >Sign Up</button></a>
	          <a href="selectionlogin.jsp"><button type="button" class="btn btn-dark btn-sm">Login</button></a>
	
		    
	<%		
		}
    %>
    
            
    </div>
          </div>
        </div>
      </div>
    </header>
    
    
    
    
    <!-- END head -->
