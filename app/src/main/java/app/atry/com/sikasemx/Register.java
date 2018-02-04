package app.atry.com.sikasemx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.DatabaseReference;



public class Register extends AppCompatActivity implements View.OnClickListener{
//    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
//    private FirebaseDatabase database;
    private TextView email;
    private EditText password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.email_register_button);
        register.setOnClickListener(this);

    }

    private static final String TAG  = "Register";
    public void updateUI(FirebaseUser user){}

    //method to create a new user
    public void createNewUser (){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        String emailstr = email.getText().toString();
        String passwordstr  = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailstr, passwordstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public void createOrganizationClick(View view){
        createNewUser();
    }

    @Override
    public void onClick(View view) {
        //Method to register new user

        //On Success, open Parent Activity
    }


}

