package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.RegisterInterface;
import com.juanantonio.gasteros.presenter.RegisterPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View {

    RegisterInterface.Presenter presenter;
    Button registerButton;
    EditText name, email, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.presenter = new RegisterPresenter(this);
        this.registerButton = findViewById(R.id.registerButton);
        this.name = findViewById(R.id.NameEditTextRegister);
        this.email = findViewById(R.id.emailEditTextRegister);
        this.pass1 = findViewById(R.id.contraseñaEditTextRegister);
        this.pass2 = findViewById(R.id.ContraseñaConfirmarEditTextRegister);

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createUser();
            }
        });
    }

    @Override
    public void createUser() {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email.getText().toString());
        if (pass1.getText().toString().equals(pass2.getText().toString()) && mather.find() && name.getText().toString().length() > 0
                && pass1.getText().toString().length() < 6 && pass2.getText().toString().length() < 6) {
            presenter.registerUser(name.getText().toString(), email.getText().toString(), pass1.getText().toString());
        } else {
            if (!pass1.getText().toString().equals(pass2.getText().toString())) {
                pass2.setError("La contraseña no coincide");
            }
            if (!mather.find()) {
                email.setError("El email es inválido");
            }
            if (name.getText().toString().length() == 0) {
                name.setError("Debes especificar un nombre");
            }
            if (pass1.getText().toString().length() < 6 && pass2.getText().toString().length() < 6) {
                pass1.setError("La contraseña debe tener como mínimo 6 caracteres");
            }
        }
    }
}
