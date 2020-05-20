package np.edu.e_pbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText signUp_email, signUp_pass, signUp_confirmPass, fName, lName;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp_email = findViewById(R.id.signUp_email_id);
        signUp_pass = findViewById(R.id.signUp_pass_id);
        signUp_confirmPass = findViewById(R.id.signUp_confirmPass_id);
        fName = findViewById(R.id.fName_id);
        lName = findViewById(R.id.lName_id);

    }

    public void signUp(View view) {
        //by clicking signup bottom in login.xml singUp method form
        String email = signUp_email.getText().toString().trim();
        String pass = signUp_pass.getText().toString().trim();
        String confirm_pass = signUp_confirmPass.getText().toString().trim();

        // initialize  firebase auth
        mAuth = FirebaseAuth.getInstance();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email Field Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Password Field Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(confirm_pass)){
            Toast.makeText(this,"Confirm Password Field Empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pass.length()<6){
            Toast.makeText(this, "Password too short",Toast.LENGTH_SHORT).show();
        }
        else if (pass.equals(confirm_pass)){
            createAccount(email, pass);
        }
        else{
            Toast.makeText(this, "Password do not meet",Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, vayo vane yo if condition execute hunxa
                            startActivity(new Intent(Register.this, Login.class));
                            Toast.makeText(Register.this,"SingUp Success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "SingUp Failed, Please try again", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    public void Login(View view) {
        startActivity(new Intent(Register.this, Login.class));

    }
}
