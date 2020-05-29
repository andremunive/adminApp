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

    private ProgressDialog dialog;
    private EditText name, lastName, user, email, password, accesscode;
    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Admins");
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
        dialog = new ProgressDialog(this);

    }

    public void registerClick(View view){
        /**
         * Validación de campos
         */
        if(name.getText().toString().isEmpty()){
            name.setError("Campo obligatorio");
        }else if(lastName.getText().toString().isEmpty()){
            lastName.setError("Campo obligatorio");
        }else if(user.getText().toString().isEmpty()){
            user.setError("Campo obligatorio");
        }else if(email.getText().toString().isEmpty()){
            email.setError("Campo obligatorio");
        }else if(password.getText().toString().isEmpty()){
            password.setError("Campo obligatorio");
        }else if(accesscode.getText().toString().isEmpty()){
            accesscode.setError("Campo obligatorio");
        }else if(!accesscode.getText().toString().equals("123456")){
            accesscode.setError("Código inválido");
        }else{
            /**
             * Campos validados
             * Registro en la base de datos
             */

            final admins administrador = new admins(
                    name.getText().toString(),
                    lastName.getText().toString(),
                    user.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()
            );

            dialog.setMessage("Registrando Administrador...");
            dialog.show();

            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(signIn.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            dbReference.child(user.getText().toString()).setValue(administrador)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if(task.isSuccessful()){
                                showLogin();
                            }
                        }
                    });


        }
    }

    public void showLogin(){
        Intent loginIntent = new Intent(signIn.this, MainActivity.class);
        startActivity(loginIntent);
    }

}
