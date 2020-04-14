package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.LoginInterface;
import com.juanantonio.gasteros.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View, View.OnClickListener {
    private LoginInterface.Presenter presenter = new LoginPresenter(this);
    private Button registerButton, loginButton;
    private EditText email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.emailEditTextLogin);
        pass = findViewById(R.id.contraseñaEditTextLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(presenter);
                presenter.openRegister();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(email.getText().toString().trim());
                System.out.println(pass.getText().toString().trim());

                presenter.loginUser(email.getText().toString().trim(), pass.getText().toString());
            }
        });
    }

    @Override
    public void openRegister() {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }

    @Override
    public void openApp() {
        Intent i =new Intent(getApplicationContext(),ListExpensesActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                System.out.println("login button");
                presenter.loginUser(this.email.getText().toString().trim(), this.pass.getText().toString());
                break;
        }
    }
}
