package com.juanantonio.gasteros.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juanantonio.gasteros.interfaces.CreateExpenseInterface;
import com.juanantonio.gasteros.model.Expenses;

public class CreateExpensePresenter implements CreateExpenseInterface.Presenter {
    CreateExpenseInterface.View view;
    private DatabaseReference dr;

    public CreateExpensePresenter(CreateExpenseInterface.View view) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void createExpense() {
        view.createExpense();
    }

    @Override
    public void saveExpense(Expenses e) {
        e.setIdExpense(this.dr.child("Gastos").push().getKey());
        this.dr.child("Gastos").push().setValue(e);
        comeBack();

    }

    @Override
    public void comeBack() {
        view.comeBack();
    }
}
