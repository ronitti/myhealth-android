package myhealth.ufscar.br.myhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.utils.SecurityUtils;
import myhealth.ufscar.br.myhealth.tasks.UserLoginTask;

public class LoginActivity extends AppCompatActivity {

    private Button btnSignup;
    private Button btnSignin;
    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.text_email);
        txtPassword = findViewById(R.id.text_password);

        btnSignup = findViewById(R.id.signup_action);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.signUp();
            }
        });

        btnSignin = findViewById(R.id.signin_action);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.signIn();
            }
        });
    }

    private void signIn(){
        new UserLoginTask(LoginActivity.this).execute(txtEmail.getText().toString(), SecurityUtils.hashString(txtPassword.getText().toString()));

    }

    private void signUp(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
