package com.winers.winetastic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.winers.winetastic.model.manager.DatabaseHandler;
import com.winers.winetastic.model.manager.LoginManager;
import com.winers.winetastic.model.manager.SystemManager;
import com.winers.winetastic.model.manager.UserFunctions;

public class RegisterAccount extends AbstractActivity {
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
	RegisterNetworkTasks networkTask;
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	private final static String REGEX_EMAIL_VALIDATION = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		// Import assets
		inputEmail = (EditText) findViewById(R.id.register_email);
		inputPassword = (EditText) findViewById(R.id.register_password);
		btnRegister = (Button) findViewById(R.id.register_submit);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);
		ImageButton logoutButton = (ImageButton) findViewById(R.id.logout_button);
		logoutButton.setVisibility(View.GONE);
		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				networkTask = new RegisterNetworkTasks();
				networkTask.execute();
			}
		});

	}
	
	private class RegisterNetworkTasks extends AsyncTask<Void, Void, Boolean> {
		
		String email = inputEmail.getText().toString();
		String password = inputPassword.getText().toString();
		UserFunctions userFunction = new UserFunctions();
		JSONObject json;
		boolean error = false;
		private boolean isOnline;
		
		@Override
		protected void onPreExecute() {
			registerErrorMsg.setText("");
			if (!SystemManager.isOnline(getApplicationContext())) {
				isOnline = false;
				} else {
				isOnline = true;
				// check for valid email address
				Pattern pattern = Pattern.compile(REGEX_EMAIL_VALIDATION);
				Matcher match = pattern.matcher(email);
				if(!match.find()) {
					registerErrorMsg.setText("Please enter a valid email address");
					error = true;
				}
				else if (password.length() < 6) {
				registerErrorMsg.setText("Your password must be at least 6 characters");
					error = true;
				}
			}
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			if (isOnline) {
				if (!error) {
					LoginManager.createSnoothAccount(email);
					json = userFunction.registerUser(email, password);
					
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (!isOnline) {
				Toast.makeText(getApplicationContext(), "You must be connected to the internet to use this feature", Toast.LENGTH_SHORT).show();
			} else {
				if (!error) {
					System.err.println("Created account with email address: " + email);
					try {
						if (json.getString(KEY_SUCCESS) != null) {
							registerErrorMsg.setText("");
							String res = json.getString(KEY_SUCCESS); 
							if(Integer.parseInt(res) == 1){
								// user successfully logged in
								// Store user details in SQLite Database
								DatabaseHandler db = new DatabaseHandler(getApplicationContext());
								JSONObject json_user = json.getJSONObject("user");
								
								// Clear all previous data in database
								userFunction.logoutUser(getApplicationContext());
								db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
								
								Intent homeScreen = new Intent(getApplicationContext(), Home.class);
								Toast.makeText(RegisterAccount.this, "Account created successfully!", Toast.LENGTH_LONG).show();
								// Close all views before launching Home activity
								homeScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(homeScreen);
								
								// Close Register screen
								finish();
							}else{
								// Error in login
								registerErrorMsg.setText("Email address already exists in database.");
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	protected int getTitleText() {
		return R.string.title_activity_register;
	}

}
