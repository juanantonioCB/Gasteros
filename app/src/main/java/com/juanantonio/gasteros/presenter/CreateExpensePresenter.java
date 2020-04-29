package com.juanantonio.gasteros.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.juanantonio.gasteros.interfaces.CreateExpenseInterface;
import com.juanantonio.gasteros.model.Expenses;

public class CreateExpensePresenter implements CreateExpenseInterface.Presenter {
    CreateExpenseInterface.View view;
    private DatabaseReference dr;
    private Expenses expense;

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
        String key = this.dr.child("Gastos").push().getKey();
        e.setIdExpense(key);
        this.dr.child("Gastos").child(key).setValue(e);
        comeBack();
    }

    @Override
    public void comeBack() {
        view.comeBack();
    }

    @Override
    public void loadExpense(String id) {

        Query q = this.dr.child("Gastos").orderByChild("idExpense").equalTo(id);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot e : dataSnapshot.getChildren()) {
                        expense = e.getValue(Expenses.class);
                    }
                }
                view.loadExpense(expense);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void loadHour() {
        view.loadHour();
    }

    @Override
    public void loadDate() {
        view.loadDate();
    }

    @Override
    public void nowDate() {
        view.nowDate();
    }

    @Override
    public void updateExpense(Expenses e) {
        this.dr.child("Gastos").child(e.getIdExpense()).setValue(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    view.comeBack();
                }
            }

        });

    }
}
