package app.atry.com.sikasemx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;


import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;


import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private FirebaseAuth mAuth;
    private TextView email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.email_sign_in_button);
        register = (Button) findViewById(R.id.email_sign_up_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
//        mAuth = FirebaseAuth.getInstance();
    }

    //method to create a new user
    public void createNewUser(){
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        String emailstr = password.getText().toString();
        String passwordstr  = email.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailstr, passwordstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Login.this,"Organization has been created",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.email_sign_in_button){
            //Login Authentication method
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.email_sign_up_button){
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user){
    }
    private static final String TAG  = "Login";


    public void login(View view){
        email = (EditText) findViewById(R.id.email);
        password = findViewById(R.id.password);
        String emailstr = email.getText().toString();
        String passwordstr  = password.getText().toString();
        mAuth.signInWithEmailAndPassword(emailstr, passwordstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signup(View view){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

  public void getCurrentUser(){
      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      if (user != null) {
          // Name, email address, and profile photo Url
          String name = user.getDisplayName();
          String email = user.getEmail();
          Uri photoUrl = user.getPhotoUrl();

          // Check if user's email is verified
          boolean emailVerified = user.isEmailVerified();

          // The user's ID, unique to the Firebase project. Do NOT use this value to
          // authenticate with your backend server, if you have one. Use
          // FirebaseUser.getToken() instead.
          String uid = user.getUid();
      }
  }

}

