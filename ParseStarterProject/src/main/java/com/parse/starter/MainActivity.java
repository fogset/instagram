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
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
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


public class MainActivity extends AppCompatActivity {
  public void signUpCLicked(View view){
    EditText usernameEditText = findViewById(R.id.usernameEditText);
    EditText passwordEditText = findViewById(R.id.passwordEditText);

    if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
      Toast.makeText(this,"a username and a password is required.", Toast.LENGTH_SHORT).show();
    }else{
      ParseUser user = new ParseUser();
      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());
      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null){
            Log.i("Signup", "Success");
          }else {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    /*
    ParseUser user = new ParseUser();
    user.setUsername("nick");
    user.setPassword("myPassword");
    user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
            if(e == null){
                Log.i("Sign Up OK!", "We did it");
            }else{
                e.printStackTrace();
            }
        }
    });

    ParseUser.logInInBackground("nick", "myPassord", new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
            if(user != null){
                Log.i("Success", "We logged in");
            }else {
                e.printStackTrace();
                Log.i("Unsuccessful", "username or password is wrong");
            }
        }
    });

    ParseUser.logOut();
    if (ParseUser.getCurrentUser() != null){
        Log.i("Signed In", ParseUser.getCurrentUser().getUsername());
    }else{
        Log.i("not luck", "Not signed in");
    }
    */








    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}