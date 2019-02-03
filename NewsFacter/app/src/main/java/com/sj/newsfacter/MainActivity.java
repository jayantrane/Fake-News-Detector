package com.sj.newsfacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton, mButton2, mButton3, mButton4;
    private TextView mTextView, mTextView2;
    private DatabaseReference mDatabase;
    public String userId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(userId);
        //mDatabase.child("users").child(userName).child("msgCount").setValue("3");

        mTextView = findViewById(R.id.textView2);
        mTextView2 = findViewById(R.id.textView4);


        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton.setOnClickListener(MainActivity.this);
        mButton2.setOnClickListener(MainActivity.this);
        mButton3.setOnClickListener(MainActivity.this);
        mButton4.setOnClickListener(MainActivity.this);

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                mTextView.setText(user.name);
                mTextView2.setText(user.balance);
                Toast.makeText(MainActivity.this,user.name, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        myRef.addValueEventListener(userListener);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, CreateMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, ViewMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}
