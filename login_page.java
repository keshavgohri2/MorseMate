package com.myrestaurant.morsemate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.myrestaurant.morsemate.R;

public class login_page extends AppCompatActivity {
    EditText editPhoneNumber,editEmail,editPassword;
    ImageView imgHide;
    Button btnLogin;
    TextView txtSignup,txtForgotPassword;
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private boolean passwordVisible = false;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        imgHide = findViewById(R.id.img_hide);
        btnLogin = findViewById(R.id.btn_login);
        editPhoneNumber = findViewById(R.id.edit_PhoneNumber);
        txtSignup = findViewById(R.id.txt_signup);
        txtForgotPassword = findViewById(R.id.txt_forgot_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validate = true;
                String Ephonenumber = editPhoneNumber.getText().toString();
                String Eemail = editEmail.getText().toString();
                String Epassword = editPassword.getText().toString();
                if (Eemail.isEmpty()) {
                    editEmail.setError("Email Is Required");
                    validate = false;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString()).matches()){
                    editEmail.setError("Enter Valid Email");
                    validate = false;
                }
                else if (Epassword.isEmpty()) {
                    editPassword.setError("Password Is Required");
                    validate = false;
                }
                else if (!Epassword.matches(PASSWORD_PATTERN)) {
                    editPassword.setError("At least 8 characters long\n" +
                            "Contains at least one digit (0-9)\n" +
                            "Contains at least one lowercase letter (a-z)\n" +
                            "Contains at least one uppercase letter (A-Z)\n" +
                            "Contains at least one special character (@#$%^&+=!)\n" +
                            "Does not contain spaces");
                    validate = false;
                }
                if (validate){
                    auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(Eemail,Epassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if (auth.getCurrentUser().isEmailVerified()){
                                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                                }else{
                                    Toast.makeText(login_page.this, "Email Is Not Verified", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(login_page.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), sign_up_page.class));
            }
        });
        imgHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordVisible = !passwordVisible;
                if (passwordVisible){
                    editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgHide.setImageResource(R.drawable.show);
                }
                else
                {
                    editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgHide.setImageResource(R.drawable.hide_password);
                }
                editPassword.setSelection(editPassword.getText().length());
            }
        });
    }
}