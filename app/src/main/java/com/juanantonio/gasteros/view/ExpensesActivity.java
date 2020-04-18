package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.ExpensesInterface;
import com.juanantonio.gasteros.presenter.ListExpensesAdapter;

public class ExpensesActivity extends AppCompatActivity implements ExpensesInterface.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
    }
}
