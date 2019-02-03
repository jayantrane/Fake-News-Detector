package com.sj.newsfacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMessageActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextView12, mTextView14, mTextView15, mTextView16;
    private EditText mEditText2;
    private Button mButton6, mButton7;
    private DatabaseReference mDatabase;
    public String userId = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("messages").child("4");

        mTextView12 = findViewById(R.id.textView12);
        mTextView14 = findViewById(R.id.textView14);
        mTextView15 = findViewById(R.id.textView15);
        mTextView16 = findViewById(R.id.textView16);

        mEditText2 = findViewById(R.id.editText2);

        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);

        mButton6.setOnClickListener(ViewMessageActivity.this);
        mButton7.setOnClickListener(ViewMessageActivity.this);

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Message msg = dataSnapshot.getValue(Message.class);
                Toast.makeText(ViewMessageActivity.this,msg.userName, Toast.LENGTH_SHORT).show();
                mTextView12.setText(msg.userName);
                mEditText2.setText(msg.article);
                mTextView14.setText(msg.summarized);
                mTextView15.setText(msg.relevant);
                mTextView16.setText(msg.fakeProb);
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
            case R.id.button6:
                Intent intent = new Intent(this, ForwardActivity.class);
                startActivity(intent);
                break;
            case R.id.button7:
                finish();
                break;
        }
    }
}
