package com.sagar.smartcontactmanager.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagar.smartcontactmanager.service.EmailServiceSender;

@Controller
public class ForgotController {
	Random random = new Random(1000);
	int otp = random.nextInt(9999);
	
	@Autowired
	private EmailServiceSender service;
	
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forget_email_form";
	}
	
	
	@RequestMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email,HttpSession session)
	{
		System.out.println("Email"+email);
		
		
		//generting otp of 4 digit

		
		
		String s=String.valueOf(otp);  
		
		System.out.println("OTP"+otp);
		
		service.sendEmail(email,s,"Your OTP");
		session.setAttribute("myotp", otp);
		session.setAttribute("email",email);
		//write code for send otp to email
		return "verify_otp";
	}


	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") Integer otp,HttpSession session)
	{
		Integer otp1 = (int)session.getAttribute("myotp");
		String email = (String)session.getAttribute("email");
		
		if(myotp==otp1)
		{
			return "password_change_form";
		}
		else
			
		{
			session.setAttribute("message","You have entered wrong otp !!!");
			return "verify_otp";
		}
	}
	

}
