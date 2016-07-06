package com.example.samu.poolmii;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samu.poolmii.Beans.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sing_up extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSingup;
    private EditText etNombre;
    private EditText etDni;
    private EditText etFecha_nacimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //inicializar firebase componentes
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //views
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSingup = (Button) findViewById(R.id.btnSingup);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDni = (EditText) findViewById(R.id.etDni);
        etFecha_nacimiento = (EditText) findViewById(R.id.etFecha_nacimiento);

        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUp(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =  firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.i("onAuthStateChange: ", "user loggeado con: "+user.getUid());
                    grabarUsuario(user.getUid(), etNombre.getText().toString(), etDni.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.i("NoSingup", "fuck");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    private void singUp(String email, String password){
        if(!validateForm()){
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuario registrado con exito",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Fallo en el registro del usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void grabarUsuario(String id, String nombre, String dni){
        mDatabase.child("users").child(id).setValue(new Usuario(nombre, dni)).addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Log.i("error grabarUsuario: ", "liada meu");
                        }
                    }
                });
    }
}
