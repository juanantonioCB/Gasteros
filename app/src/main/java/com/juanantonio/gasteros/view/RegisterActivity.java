package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.RegisterInterface;
import com.juanantonio.gasteros.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View {

    RegisterInterface.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.presenter=new RegisterPresenter(this);

    }
}
