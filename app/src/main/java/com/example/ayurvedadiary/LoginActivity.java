package com.example.ayurvedadiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //widgets
    private EditText mEmail, mPassword;
    private TextView mRegisterTextView;
    private ProgressBar mProgressBar;
    private RelativeLayout mLoginButton;

    //Firebase Authentication
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide();

        mEmail=findViewById(R.id.username);
        mPassword=findViewById(R.id.password);
        mRegisterTextView=findViewById(R.id.register_tv);
        mProgressBar=findViewById(R.id.loading);
        mLoginButton=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();
        //add onclickListener to login Button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
        //add onClickListener to register TextView
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //revert the user to register activity
                startActivity(new Intent(LoginActivity.this ,
                        RegisterActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    //method to logIn existing users
    private void logIn()
    {
        final String email, password;
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        //make sure the password and email are not empty
        if(!email.equals("") && !password.equals(""))
        {
            mProgressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if(task.isSuccessful())
                            {
                                Intent intent =
                                        new Intent(LoginActivity.this
                                                , SelectionActivity.class);
                                intent.putExtra("email" , email);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                //login unsuccessful
                                Toast.makeText(LoginActivity.this, "Login Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}














