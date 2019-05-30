package myhealth.ufscar.br.myhealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.utils.EditTextUtils;
import myhealth.ufscar.br.myhealth.utils.SecurityUtils;
import myhealth.ufscar.br.myhealth.tasks.UserLoginTask;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }

    private void initializeComponents(){
        txtEmail = findViewById(R.id.text_email);
        txtPassword = findViewById(R.id.text_password);

        Button btnSignup = findViewById(R.id.signup_action);
        Button btnSignin = findViewById(R.id.signin_action);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.signUp();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.signIn();
            }
        });
    }

    private void signIn(){
        if(!EditTextUtils.isValidEmail(txtEmail.getEditableText())){
            txtEmail.setError(getResources().getString(R.string.hint_error_invalid_email));
        }else if(!EditTextUtils.isValidPassword(txtPassword.getEditableText())){
            txtPassword.setError(getResources().getString(R.string.hint_error_invalid_password));
        } else {
            String email = txtEmail.getEditableText().toString();
            String password = SecurityUtils.hashString(txtPassword.getEditableText().toString());
            new UserLoginTask(LoginActivity.this).execute(email, password);
        }
    }

    private void signUp(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        //finish();
    }
}
