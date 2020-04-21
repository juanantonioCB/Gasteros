package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.CreateExpenseInterface;

public class CreateExpenseActivity extends AppCompatActivity implements CreateExpenseInterface.View {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        this.id = getIntent().getStringExtra("id");
    }
}
