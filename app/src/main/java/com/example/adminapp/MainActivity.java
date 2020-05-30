package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference adminRef = FirebaseDatabase.getInstance().getReference().child("Admins");
    private EditText user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        user = findViewById(R.id.userTxt);
        password = findViewById(R.id.passwordTxt);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                showHome();

        }
    }

    public void signinClick(View view) {
        finish();
        Intent signinIntent = new Intent(MainActivity.this, signIn.class);
        startActivity(signinIntent);

    }

    public void loginClick(View view) {
        adminValidate();
    }

    //Método de seguridad para validar que quien inicia sesión esté registrado como administrador
    public void adminValidate() {

        //Escucha en la BD
        adminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Si la ruta "Admins" existe
                if (dataSnapshot.exists()) {
                    //Si el usuario ingresado existe
                    if (dataSnapshot.child(user.getText().toString()).exists()) {
                        //Se obtiene correo BD
                        String correo = dataSnapshot.child(user.getText().toString())
                                .child("email").getValue().toString();
                        //Se obtiene clave de la BD
                        String clave = dataSnapshot.child(user.getText().toString())
                                .child("password").getValue().toString();
                        //Validación de que la clave escrita en pantalla sea la misma de la BD
                        if (clave.equals(password.getText().toString())) {
                            //Login con FirebaseAuth
                            auth.signInWithEmailAndPassword(correo, clave);
                            showHome();
                        }else{
                            password.setError("Clave incorrecta");
                        }
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Usuario no existe en la base de datos",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showHome() {
        finish();
        Intent homeIntent = new Intent(this, home.class);
        startActivity(homeIntent);
    }
}
