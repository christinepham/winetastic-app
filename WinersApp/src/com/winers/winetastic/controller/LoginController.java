package com.winers.winetastic.controller;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.winers.winetastic.Home;
import com.winers.winetastic.model.manager.DatabaseHandler;
import com.winers.winetastic.model.manager.LoginManager;
import com.winers.winetastic.model.manager.UserFunctions;

/**
 * Performs validation tasks for login.
 * 
 *
 */

public class LoginController {
	/*
     * JSON response node names
     */
	static final String KEY_SUCCESS = "success";
	static final String KEY_UID = "uid";
	static final String KEY_NAME = "name";
	static final String KEY_EMAIL = "email";
	static final String KEY_CREATED_AT = "created_at";	
	
	private String email, password;
	private TextView errorMsg;
	private Context context;
	
	public LoginController(Context context, 
			String email, 
			String password,
			TextView errorMsg) {
		this.email = email;
		this.password = password;
		this.context = context;
		this.errorMsg = errorMsg;
	}
	
	public void validate() {
		if(!hasError()) {
			new LoginNetworkTask(context).execute();
		}	
	}
	
	private boolean hasError() {
		if (email.length() < 1) {
			errorMsg.setText("Please enter an email address");
			return true;
		}
		if (password.length() < 1) {
			errorMsg.setText("Please enter a password");
			return true;
		}
		errorMsg.setText("");
		return false;
	}
	
	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class LoginNetworkTask extends AsyncTask<Void, Void, Void> {
		UserFunctions userFunction = new UserFunctions();
		JSONObject json;
		Context context;
		private boolean isOnline;
		
		public LoginNetworkTask(Context context) {
			this.context = context;
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			if(!SystemManager.isOnline(context)) {
				isOnline = false;
			} else {
				isOnline = true;
				LoginManager.createSnoothAccount(email);
				json = userFunction.loginUser(email, password);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (!isOnline) {
				Toast.makeText(context, "You must be connected to the internet to use this feature", Toast.LENGTH_SHORT).show();
			} else {
				System.err.println("Made it into onPostExecute");
				try {
					if (json.getString(LoginController.KEY_SUCCESS) != null) {
						errorMsg.setText("");
						String res = json.getString(LoginController.KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(context.getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(context.getApplicationContext());
							db.addUser(json_user.getString(LoginController.KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							
							System.err.println("User logged in");
							
							Intent homeScreen = new Intent(context.getApplicationContext(), Home.class);
							
							// Close all views before launching Home activity
							homeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							context.startActivity(homeScreen);
							
							// Close Login Screen
							((Activity) context).finish();
						} else {
							// Error in login
							errorMsg.setText("Invalid email address or password. Please try again.");
						}
					}
				} catch (JSONException e) {
					System.err.println("JSON error");
					e.printStackTrace();
				}
			}
		}
	}

}
