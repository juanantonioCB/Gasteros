package com.juanantonio.gasteros.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juanantonio.gasteros.interfaces.ListExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.ArrayList;

public class ListExpensesPresenter implements ListExpensesInterface.Presenter {

    ListExpensesInterface.View view;
    private FirebaseAuth mAuth;
    private DatabaseReference dr;

    public ListExpensesPresenter(ListExpensesInterface.View view) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void addNewList(String name) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        ListExpenses list = new ListExpenses();
        list.setName(name);
        list.setOwnerId(user.getUid());

        if (this.dr.child("Listas").push().setValue(list).isComplete()) {
            System.out.println("okkkkk");
            view.showToast("Lista añadida correctamente");
        }

        view.showToast("Lista añadida correctamente");

    }


}
