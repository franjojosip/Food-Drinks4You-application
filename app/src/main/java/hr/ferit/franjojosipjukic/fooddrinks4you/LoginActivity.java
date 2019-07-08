package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail;
    private EditText etPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this,MenuActivity.class));
        }
        initViews();
    }

    private void initViews() {
        firebaseAuth = FirebaseAuth.getInstance();
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnRegister = findViewById(R.id.btnRegister);
        this.etEmail = findViewById(R.id.etEnterEmail);
        this.etPassword = findViewById(R.id.etEnterPassword);
    }

    public void onClickLogin(View view) {
        if(TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(this, "Please input your data", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            goToMenuActivity();
                        }
                        else Toast.makeText(LoginActivity.this,"Wrong username or password.",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void goToMenuActivity() {
        startActivity(new Intent(this,MenuActivity.class));

    }
    public void goBack(View view) {
        finish();
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
        ;
    }
}
