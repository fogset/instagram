/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

import static com.google.android.gms.analytics.internal.zzy.i;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

  TextView loginTextView;
  Boolean signUpModeActive = true;
  EditText usernameEditText;
  EditText passwordEditText;

  @Override
  public boolean onKey(View v, int keyCode, KeyEvent event) {
    if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
        signUpCLicked(v);
    }

    return false;
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.loginTextVIew){
      Button signUpBUtton = findViewById(R.id.signUpButton);

      if(signUpModeActive){
        signUpModeActive = false;
        signUpBUtton.setText("Login");
        loginTextView.setText("or, Sign Up");
      }else {
        signUpModeActive = true;
        signUpBUtton.setText("Sign Up");
        loginTextView.setText("or, Login");
      }
    }
    else if(v.getId() == R.id.logoImageView ||v.getId() ==R.id.backgroundLayout ){
      InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
  }

  public void signUpCLicked(View view){

    if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
      Toast.makeText(this,"a username and a password is required.", Toast.LENGTH_SHORT).show();
    }else{
      if(signUpModeActive) {
        ParseUser user = new ParseUser();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Log.i("Signup", "Success");
              Toast.makeText(MainActivity.this, "a new user signed up.", Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
      else
        {
        //Login
        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
            if(user != null){
              Log.i("Login ok!", usernameEditText.getText().toString()+" just logged in");
            }else{
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    }
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    loginTextView = findViewById(R.id.loginTextVIew);
    loginTextView.setOnClickListener(this);
    usernameEditText = findViewById(R.id.usernameEditText);
    passwordEditText = findViewById(R.id.passwordEditText);
    ImageView logoImageView = findViewById(R.id.logoImageView);
    RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout);
    logoImageView.setOnClickListener(this);
    backgroundLayout.setOnClickListener(this);

    passwordEditText.setOnKeyListener(this);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}