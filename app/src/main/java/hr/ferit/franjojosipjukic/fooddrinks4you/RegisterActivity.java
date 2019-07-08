package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private FirebaseAuth firebaseAuth;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews();
    }

    private void initViews() {
        this.btnRegister = findViewById(R.id.btnNewRegister);
        this.etEmail = findViewById(R.id.etNewEmail);
        this.etPassword = findViewById(R.id.etNewPassword);
        firebaseAuth.getInstance();
    }

    public void onClickNewUser(View view) {
        if(TextUtils.isEmpty(etPassword.getText().toString()) || TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(this, getString(R.string.input_data), Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,getString(R.string.successful_register),Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(RegisterActivity.this, getString(R.string.cant_register), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void goBack(View view) {
        finish();
    }

    public void onClickGoToLogin(View view) {
        finish();
    }
}
