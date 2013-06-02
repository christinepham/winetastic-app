/* Login by email window.
 */

package com.winers.winetastic;

import java.io.IOException;
import java.net.MalformedURLException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


//import com.facebook.Session;
//import com.facebook.SessionState;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.z
 */
public class Login extends Activity implements OnClickListener{
	
	//app_id

//	Button fbButton;
	Facebook fb;
	ImageView pic;
	ImageView fbButton;
	SharedPreferences sp;
	
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	// Holds layout
	PopupWindow loginWindow;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Creating login");
 	
		setContentView(R.layout.activity_login);
		//setupActionBar();  //log cat says error
		
		String app_id = getString(R.string.app_id);
		fb = new Facebook(app_id);
		
		
		/* immediate login
		sp = getPreferences(MODE_PRIVATE);
		String access_token = sp.getString("access_token", null);
		long expires = sp.getLong("access_expires", 0);
		
		if(access_token != null){
			fb.setAccessToken(access_token);
		}
		if(expires != 0){
			fb.setAccessExpires(expires);
		}
		*/
		
		
		//login_title???
		//fbButton = (Button)findViewById(R.id.register_facebook_button);
		fbButton = (ImageView)findViewById(R.id.login_fb_icon);
		fbButton.setOnClickListener(this);


		updateButtonImage();


		/*  this works! change back and stop implementation
        fbButton.setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			System.out.println("CLICK MEE");
			loginToFacebook(v);
			updateButtonImage();
		}
    });

*/
		

		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(fb.isSessionValid()){
			//button close our session- log off facebook
			try {
				fb.logout(getApplicationContext());
				updateButtonImage();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//log into facebook
		//	fb.authorize(Login.this, new String[] {"email"}, new DialogListener() {
		
			fb.authorize(this, new String[] { "email" }, new DialogListener() {

				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					Toast.makeText(Login.this, "onfbErrorFML what TTT ", Toast.LENGTH_SHORT).show();

					
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					Toast.makeText(Login.this, "onError", Toast.LENGTH_SHORT).show();

					
				}
				
				@Override
				public void onComplete(Bundle values) { 
					// TODO Auto-generated method stub
					
					/* immediate login from fb
					Editor editor = sp.edit();
					editor.putString("access_token", fb.getAccessToken());
					editor.putLong("access_expires", fb.getAccessExpires());
					editor.commit();
					
					*/
					
					Toast.makeText(Login.this, "SUCCESS NEW ACTIVITY HERE", Toast.LENGTH_SHORT).show();
					//Intent i = new Intent(Login.this, WineSearch.class);  //this works
					Intent i = new Intent(Login.this, Home.class);  //this doesnt work... 
					startActivity(i);

				//	updateButtonImage();
					
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

	 
		// Set up the login form.
//		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
//		mEmailView = (EditText) findViewById(R.id.login_email);
//		mEmailView.setText(mEmail);
//		
//		
//		
//
//		mPasswordView = (EditText) findViewById(R.id.login_password);
//		mPasswordView
//				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//					@Override
//					public boolean onEditorAction(TextView textView, int id,
//							KeyEvent keyEvent) {
////						if (id == R.id.login || id == EditorInfo.IME_NULL) {
////							attemptLogin();
////							return true;
////						}
//						return false;
//					}
//					
//					
//					
//		});
//
//		mLoginFormView = findViewById(R.id.loginWindow);
		//mLoginStatusView = findViewById(R.id.login_status);
		//mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);



	


	//Set up the {@link android.app.ActionBar}, if the API is available.
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}else{
			fbButton.setBackgroundResource(R.drawable.log_in2);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.

			//NavUtils.navigateUpFromSameTask(this);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				finish();
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

	public void loginToFacebook(View v) {
		
		System.out.println("herkeer");
		// TODO Auto-generated method stub
		if(fb.isSessionValid()){
			//button close our session - log out of facebook
			try {
				fb.logout(getApplicationContext());
				updateButtonImage();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			//login to facebook
			fb.authorize(Login.this, new String[]{"email"}, new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
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

	

}
