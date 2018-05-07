package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    ListView userList;
    ArrayList<String> usernames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userList = findViewById(R.id.userList);
        usernames = new ArrayList<String>();
        usernames.add("test");
        usernames.add("test1");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usernames);
        userList.setAdapter(arrayAdapter);

        //ParseQuery<ParseUser>

    }
}
