package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.CreateExpenseInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.presenter.CreateExpensePresenter;

import java.util.Date;

public class CreateExpenseActivity extends AppCompatActivity implements CreateExpenseInterface.View {

    private String idList;
    private String idUser;
    private FirebaseAuth mAuth;
    private EditText titleEditText, amountEditText, dateEditText;
    private Button nowButton, saveButton;
    private CreateExpenseInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        this.presenter = new CreateExpensePresenter(this);
        this.idList = getIntent().getStringExtra("idList");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        this.idUser = user.getUid();
        this.titleEditText = findViewById(R.id.titleCreateExpenseEditText);
        this.amountEditText = findViewById(R.id.amountCreateExpenseEditText);
        this.dateEditText = findViewById(R.id.dateHourCreateExpenseEditText);
        this.nowButton = findViewById(R.id.nowButtonCreateExpenseEditText);
        this.saveButton = findViewById(R.id.saveButtonCreateExpenseEditText);
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createExpense();
            }
        });
    }

    @Override
    public void createExpense() {
        Expenses e = new Expenses();
        e.setName(this.titleEditText.getText().toString());
        e.setAmount(Float.parseFloat(this.amountEditText.getText().toString()));
        e.setDate(new Date(this.dateEditText.getText().toString()));
        e.setListId(this.idList);
        e.setUserId(this.idUser);
        presenter.saveExpense(e);
    }
}
