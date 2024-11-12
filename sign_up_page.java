package com.myrestaurant.morsemate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.health.connect.datatypes.NutritionRecord;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;
import java.util.regex.Pattern;

public class sign_up_page extends AppCompatActivity {
    EditText editName,editEmail,editPassword,editPhoneNumber;
    CheckBox chAgreeToTerms;
    Button btnRegister,btnCancel;
    TextView txtLogin,txtTerms,txt_login;
    ImageView img_hide1;
    private boolean passwordVisible = false;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editPhoneNumber = findViewById(R.id.edit_PhoneNumber);
        chAgreeToTerms = findViewById(R.id.checkbox_agree);
        btnRegister = findViewById(R.id.btn_register);
        btnCancel = findViewById(R.id.btn_cancel);
        txtTerms = findViewById(R.id.txt_terms);
        txt_login = findViewById(R.id.txt_login);
        img_hide1 = findViewById(R.id.img_hide);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ename  = editName.getText().toString();
                String Eemail = editEmail.getText().toString();
                String Epassword = editPassword.getText().toString();
                String EphoneNumber = editPhoneNumber.getText().toString();
                boolean validate = true;
                if (Ename.isEmpty()){
                    editName.setError("Name Is Required");
                    validate = false;
                }
                else if (Eemail.isEmpty()){
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
                } else if (EphoneNumber.isEmpty()) {
                    editPhoneNumber.setError("Confirm Is Required");
                    validate = false;
                } else if (!chAgreeToTerms.isChecked()){
                    Toast.makeText(sign_up_page.this, "You Have Not Accept Our Terms And Condition", Toast.LENGTH_SHORT).show();
                    validate = false;
                }
                if (validate) {
                    auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(Eemail,Epassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if (task.isSuccessful()){
                                reference = FirebaseDatabase.getInstance().getReference();
                                reference.child("Users").child(EphoneNumber).setValue(new HelperClass(Ename,Eemail,Epassword));
                                Toast.makeText(sign_up_page.this, "Account Created Successfully,Check Email To verify", Toast.LENGTH_SHORT).show();
                                auth.getCurrentUser().sendEmailVerification();
                                auth.signOut();
                                finish();
                            }else{
                                Toast.makeText(sign_up_page.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), welcome_page.class));
            }
        });
        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), sign_up_page.class));
            }
        });
        img_hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    img_hide1.setImageResource(R.drawable.hide_password);
                }else {
                    editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_hide1.setImageResource(R.drawable.show);
                }
                editPassword.setSelection(editPassword.getText().length());
            }
        });
        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), termsAndCondtionsPage.class));
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_page.class));
            }
        });

    }
}