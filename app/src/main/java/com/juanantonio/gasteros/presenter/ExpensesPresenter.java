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

import java.util.ArrayList;
import java.util.List;

public class ExpensesPresenter implements ExpensesInterface.Presenter {

    ExpensesInterface.View view;
    private DatabaseReference dr;
    private List<Expenses> list;

    public ExpensesPresenter(ExpensesInterface.View view) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
        this.list = new ArrayList<>();
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
                        System.out.println("----------------" + le);
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
    public void openCreateExpense(String idList) {
        view.openCreateExpense(idList);
    }

    @Override
    public void loadExpenses(String idList) {
        Query q = dr.child("Gastos").orderByChild("listId").equalTo(idList);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.removeAll(list);
                if (dataSnapshot.exists()) {
                    for (DataSnapshot lista : dataSnapshot.getChildren()) {
                        Expenses e = lista.getValue(Expenses.class);
                        list.add(e);
                    }
                }
                view.loadExpenses(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
