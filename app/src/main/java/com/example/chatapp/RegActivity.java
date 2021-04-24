package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegActivity extends AppCompatActivity {

    TextView email, pass;
    Button next;

     private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Pass);

        String strMail = email.getText().toString();
        String strPass = pass.getText().toString();


        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(strMail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(RegActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            boolean emailVerifide = user.isEmailVerified();

            String uid = user.getUid();

            next.setOnClickListener(v -> {
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }



    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            Intent intent = new Intent(RegActivity.this, MainActivity.class);
            startActivity(intent);
    }

}


}