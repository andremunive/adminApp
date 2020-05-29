package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        user = findViewById(R.id.userTxt);
        password = findViewById(R.id.passwordTxt);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            showHome();
        }
    }

    public void signinClick(View view){
        finish();
        Intent signinIntent = new Intent(MainActivity.this, signIn.class);
        startActivity(signinIntent);

    }

    public void loginClick(View view){
        auth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logeado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void showHome(){
        finish();
        Intent homeIntent = new Intent(this, home.class);
        startActivity(homeIntent);
    }
}
