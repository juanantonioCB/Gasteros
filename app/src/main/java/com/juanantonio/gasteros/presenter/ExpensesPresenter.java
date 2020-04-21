package com.juanantonio.gasteros.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.juanantonio.gasteros.interfaces.ExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;

public class ExpensesPresenter implements ExpensesInterface.Presenter {

    ExpensesInterface.View view;
    private DatabaseReference dr;
    private ListExpenses list;

    public ExpensesPresenter(ExpensesInterface.View view, String id) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
        this.loadExpense(id);
        System.out.println(this.list);
    }

    @Override
    public void changeTitle(String id) {
        Query q = this.dr.child("Listas").orderByChild("id").equalTo(id);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot l : dataSnapshot.getChildren()) {
                        ListExpenses le = l.getValue(ListExpenses.class);
                        System.out.println("----------------"+le);
                        view.changeTitle(le.getName());

                     //   setExpense(e);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    //    view.changeTitle(expenses.getName());
    }

    @Override
    public void loadExpense(String id) {

        Query q = this.dr.child("Listas").orderByChild("id").equalTo(id);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot l : dataSnapshot.getChildren()) {
                        System.out.println(l.getValue(Expenses.class));
                        setListExpense(l.getValue(ListExpenses.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setListExpense(ListExpenses l) {
        this.list = l;
        System.out.println(">>>>>>>>>>>>>><"+this.list);
    }

    @Override
    public void loadList() {
     //   this.view.loadList(expenses.ge);
    }
}
