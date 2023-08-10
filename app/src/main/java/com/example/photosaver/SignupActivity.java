package com.example.photosaver;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button register;
    TextView connecter;
    EditText name,email,password,cpassword;
    FirebaseDatabase database;
    DatabaseReference UsersRef;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        UsersRef = database.getReference("users");
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.signup_username);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        cpassword=findViewById(R.id.signup_conf_password);
        register=findViewById(R.id.signup_button);
        mAuth = FirebaseAuth.getInstance();
        connecter=findViewById(R.id.loginRedirectText);
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(myIntent);
            }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String nameText = name.getText().toString();
                String passText = password.getText().toString();

                if (emailText.equals("") || passText.equals(""))
                    Toast.makeText(SignupActivity.this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
                else {
                    mAuth.createUserWithEmailAndPassword(emailText, passText)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {

                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    if (firebaseUser != null) {
                                        String userId = firebaseUser.getUid();
                                        Users users = new Users(nameText, emailText);
                                        UsersRef.child(userId).setValue(users)
                                                .addOnSuccessListener(aVoid -> {
                                                    // Enregistrement réussi dans Firebase Realtime Database
                                                    // Enregistrement de l'indicateur d'inscription dans les SharedPreferences
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                });
                                    }
                                    Toast.makeText(SignupActivity.this, "Inscription réussie.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                }

        });
    }


}