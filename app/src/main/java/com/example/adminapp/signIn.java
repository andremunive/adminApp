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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signIn extends AppCompatActivity {

    private EditText name, lastName, user, email, password, accesscode;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //View
        name = findViewById(R.id.nameTxt);
        lastName = findViewById(R.id.lastNameTxt);
        user = findViewById(R.id.userTxt);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);
        accesscode = findViewById(R.id.accessCodeTxt);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void registerClick(View view) {
        if (name.getText().toString().isEmpty()) {
            name.setError("Campo obligatorio");
        } else if (lastName.getText().toString().isEmpty()) {
            lastName.setError("Campo obligatorio");
        } else if (user.getText().toString().isEmpty()) {
            user.setError("Campo obligatorio");
        } else if (email.getText().toString().isEmpty()) {
            email.setError("Campo obligatorio");
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Campo obligatorio");
        } else if (accesscode.getText().toString().isEmpty()) {
            accesscode.setError("Campo obligatorio");
        } else if (!accesscode.getText().toString().equals("123456")) {
            accesscode.setError("Código inválido");
        } else {
            signinMethod(name.getText().toString().trim(),
                    lastName.getText().toString().trim(),
                    user.getText().toString().trim(),
                    email.getText().toString().trim(),
                    password.getText().toString().trim());
        }
    }

    public void signinMethod(String nombre, String apellido, String usuario,
                             String correo, String clave) {

            admins administrator = new admins(
                    nombre,
                    apellido,
                    usuario,
                    correo,
                    clave
            );

            //Creación del usuario con email y contraseña
            auth.createUserWithEmailAndPassword(correo, clave)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(signIn.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            mDatabase.child("Admins").child(usuario).setValue(administrator)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                showLogin();
                            }
                        }
                    });
    }

    public void showLogin() {
        Intent loginIntent = new Intent(signIn.this, MainActivity.class);
        startActivity(loginIntent);
    }

}
