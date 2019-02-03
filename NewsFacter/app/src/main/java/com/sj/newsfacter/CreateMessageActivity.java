package com.sj.newsfacter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateMessageActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private EditText edit;
    private DatabaseReference mDatabase;
    public String userId = "1";
    int msgcount, balance, MINIMUMCONTRIBUTION = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        edit = (EditText)findViewById(R.id.editText);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButton = findViewById(R.id.button5);
        mButton.setOnClickListener(CreateMessageActivity.this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                msgcount = Integer.parseInt(user.msgCount);
                balance = Integer.parseInt(user.balance) - MINIMUMCONTRIBUTION;
                Toast.makeText(CreateMessageActivity.this,user.name, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(userListener);



    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button5:
                String result = edit.getText().toString();
                sendDataToContract(result);
                break;
        }
    }

    public void sendDataToContract(String result) {
        Toast.makeText(CreateMessageActivity.this, "Article Posted", Toast.LENGTH_SHORT).show();
        writeNewMessage("Jayant", result);
        finish();
    }

    private void writeNewMessage(String userName, String article) {

        Message msg = new Message(userName, article);
        mDatabase.child("msgCount").setValue(String.valueOf(msgcount+1));
        mDatabase.child("balance").setValue(String.valueOf(balance-MINIMUMCONTRIBUTION));
        mDatabase.child("messages").child(String.valueOf(msgcount+1)).setValue(msg);
    }

}
