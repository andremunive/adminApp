package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signInTeacher extends AppCompatActivity {

    private EditText name, lastName, user, email, password;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Teachers");
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_teacher);

        //View
        name = findViewById(R.id.nameTxt);
        lastName = findViewById(R.id.lastNameTxt);
        user = findViewById(R.id.userTxt);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);
    }

    public void registerClick(View view){
        if(name.getText().toString().isEmpty()){
            name.setError("Campo Obligatorio");
        }else if(lastName.getText().toString().isEmpty()){
            lastName.setError("Campo Obligatorio");
        }else if(user.getText().toString().isEmpty()){
            user.setError("Campo Obligatorio");
        }else if(email.getText().toString().isEmpty()){
            email.setError("Campo Obligatorio");
        }else if(password.getText().toString().isEmpty()){
            password.setError("Campo Obligatorio");
        }else{
            signinMethod(
                    name.getText().toString(),
                    lastName.getText().toString(),
                    user.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()
            );
        }

    }

    public void signinMethod(String nombre, String apellido, String usuario,
                             String correo, String clave) {

        teachers teacher = new teachers(
                nombre,
                apellido,
                usuario,
                correo,
                clave
        );

        //Creación del usuario con email y contraseña
        auth.createUserWithEmailAndPassword(email.getText().toString(),
                password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signInTeacher.this, "Profesor Registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDatabase.child(usuario).setValue(teacher)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                        }
                    }
                });
    }


}
