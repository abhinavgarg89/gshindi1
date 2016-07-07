package com.gshindi.android.gshindi;

import android.app.Application;

import com.firebase.client.Firebase;


/**
 * Created by abhinavgarg on 03/07/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initializing firebase
        Firebase.setAndroidContext(getApplicationContext());
        Firebase myFirebaseRef = new Firebase("https://gshindi-27fbe.firebaseio.com/");
    }
}
