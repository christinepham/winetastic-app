/* Login by email window.
 */

package com.winers.winetastic;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.z
 */
public class Login extends AbstractActivity implements OnClickListener {
	
	/*
     * Facebook stuff
     */
	Facebook fb;
	ImageView pic;
	ImageView fbButton;
	SharedPreferences sp;
	
	UserFunctions uF;

	
    /*
     * Stuff for login with email/password
     */
	Button btnLogin;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	LoginNetworkTasks networkTask;
	
	/*
     * JSON response node names
     */
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Creating login");
 	
		setContentView(R.layout.activity_login);
		
		// for DB stuff
        uF = new UserFunctions();
		
		String app_id = getString(R.string.app_id);
		fb = new Facebook(app_id);
				
		fbButton = (ImageView)findViewById(R.id.login_fb_icon);
		fbButton.setOnClickListener(this);

		updateButtonImage();
	
		// Import assets for email login
		inputEmail = (EditText) findViewById(R.id.login_email);
		inputPassword = (EditText) findViewById(R.id.login_password);
		btnLogin = (Button) findViewById(R.id.login_submit);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
				
		// Login button click event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				networkTask = new LoginNetworkTasks();
				networkTask.execute();
			}
		});
		
		
	}

	
	@Override
	public void onClick(View v) {
		if(fb.isSessionValid()){
			//button close our session- log off facebook
			try {
				fb.logout(getApplicationContext());
				updateButtonImage();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			fb.authorize(this, new String[] { "email" }, new DialogListener() {

				@Override
				public void onFacebookError(FacebookError e) {
					Toast.makeText(Login.this, "Facebook error (sad face)", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onError(DialogError e) {
					Toast.makeText(Login.this, "onError", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Bundle values) { 
					Toast.makeText(Login.this, "You have been logged in", Toast.LENGTH_SHORT).show();
					// Clear local DB then add user
					uF.logoutUser(getApplicationContext());
					DatabaseHandler db = new DatabaseHandler(getApplicationContext());
					db.addUser("Username", "Useremail", "UID", "CREATED_AT");
					Intent i = new Intent(Login.this, Home.class);  //this doesnt work... 
					startActivity(i);
				}
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					Toast.makeText(Login.this, "onCancel", Toast.LENGTH_SHORT).show();
					
				}
			});
		}	
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	 // Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	  fb.authorizeCallback(requestCode, resultCode, data);
	}
	
	 private void updateButtonImage() {
		// TODO Auto-generated method stub
		 if(fb.isSessionValid()){
			 fbButton.setImageResource(R.drawable.logout_temp);
		 }
		 else{
			 fbButton.setImageResource(R.drawable.log_in2);
		 }
		
	}

	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	
	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class LoginNetworkTasks extends AsyncTask<Void, Void, Boolean> {
		
		String email = inputEmail.getText().toString();
		String password = inputPassword.getText().toString();
		UserFunctions userFunction = new UserFunctions();
		JSONObject json;
		boolean error = false;
		
		@Override
		protected void onPreExecute() {
			loginErrorMsg.setText("");
			if (email.length() < 1) {
				loginErrorMsg.setText("Please enter an email address");
				error = true;
			}
			else if (password.length() < 1) {
				loginErrorMsg.setText("Please enter a password");
				error = true;
			}
			
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			//Log.d("Button", "Login");
			if (!error) json = userFunction.loginUser(email, password);
			return null;
		}

		@Override
		protected void onPostExecute(final Boolean result) {
			if (!error){
			try {
				if (json.getString(KEY_SUCCESS) != null) {
					loginErrorMsg.setText("");
					String res = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res) == 1){
						
						// user successfully logged in
						// Store user details in SQLite Database
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						JSONObject json_user = json.getJSONObject("user");
						
						// Clear all previous data in database
						userFunction.logoutUser(getApplicationContext());
						db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
						
						System.err.println("User logged in");
						
						Intent homeScreen = new Intent(getApplicationContext(), Home.class);
						
						// Close all views before launching Home activity
						homeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(homeScreen);
						
						// Close Login Screen
						finish();
					}else{
						// Error in login
						loginErrorMsg.setText("Invalid email address or password. Please try again.");
					}
				}
			} catch (JSONException e) {
				System.err.println("JSON error");
				e.printStackTrace();
			}
			}
		}

	}


	public void loginToFacebook(View v) {
		
		System.out.println("herkeer");
		if(fb.isSessionValid()){
			//button close our session - log out of facebook
			try {
				fb.logout(getApplicationContext());
				updateButtonImage();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			//login to facebook
			fb.authorize(Login.this, new String[]{"email"}, new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					Toast.makeText(Login.this, "fbError", Toast.LENGTH_SHORT).show();

					
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					Toast.makeText(Login.this, "onCancel", Toast.LENGTH_SHORT).show();

					
				}
				
				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					updateButtonImage();
					
				}
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					Toast.makeText(Login.this, "onCancel", Toast.LENGTH_SHORT).show();
					
				}
			});
		}
		
	}


	@Override
	protected int getTitleText() {
		return R.string.title_activity_login;
	}

	

}
