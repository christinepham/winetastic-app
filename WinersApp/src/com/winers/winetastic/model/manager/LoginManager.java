package com.winers.winetastic.model.manager;

import android.content.Context;
import android.widget.TextView;

import com.winers.winetastic.controller.LoginController;

public class LoginManager {
	
	public static void createSnoothAccount(String email) {
		WinetasticManager.dao.createSnoothAccount(email);
	}
	
	// well... 
	public static void attemptLogin( Context context, 
			String email, 
			String password, 
			TextView errorMsg) {
		new LoginController(context, email, password, errorMsg).validate();		
	}
}
