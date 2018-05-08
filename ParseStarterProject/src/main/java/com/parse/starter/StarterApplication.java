/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("b9bc0aa29f4097eca1b104066cea71423ed7944a")
            .clientKey("c3aa65ecfc36a20db4cc865f204198dce13fd24a")
            .server("http://18.220.7.174:80/parse/")
            .build()
    );
    ParseUser user = new ParseUser();
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
  */
    /*
    ParseUser.logOut();
    if (ParseUser.getCurrentUser() != null){
        Log.i("Signed In", ParseUser.getCurrentUser().getUsername());
    }else{
        Log.i("not luck", "Not signed in");
    }
    */


    //ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
