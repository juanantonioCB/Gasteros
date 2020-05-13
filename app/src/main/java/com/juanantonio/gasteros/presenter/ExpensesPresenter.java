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
import com.juanantonio.gasteros.model.User;

import java.util.ArrayList;
import java.util.List;

public class ExpensesPresenter implements ExpensesInterface.Presenter {

    ExpensesInterface.View view;
    private DatabaseReference dr;
    private List<Expenses> list;
    private String nameOwner;
    private String nameCompany;

    public ExpensesPresenter(ExpensesInterface.View view) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
        this.list = new ArrayList<>();
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
                        System.out.println("id expense " + e.getIdExpense());
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

    @Override
    public String getName(String Uid) {
        final String[] name = {null};
        Query q = dr.child("Users").orderByChild("uid").equalTo(Uid);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        name[0] = user.getValue(User.class).getName();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return name[0];
    }

    @Override
    public void loadExpense(String idExpense) {
        view.loadExpense(idExpense);
    }

    @Override
    public void removeExpense(String id) {
        Query q = this.dr.child("Gastos").orderByChild("idExpense").equalTo(id);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().removeValue();
                view.showToast("Borrado correctamente");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.showToast("Ha ocurrido un error");
            }
        });
    }

    @Override
    public void loadNames(final String ownerId, final String companyId) {
        System.out.println("ownerid " + ownerId);

        Query q = this.dr.child("Users").orderByChild("uid");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        if (user.getValue(User.class).getUid().equals(ownerId)) {
                            nameOwner = user.getValue(User.class).getName();
                        }
                        if (user.getValue(User.class).getUid().equals(companyId)) {
                            nameCompany = user.getValue(User.class).getName();
                        }
                    }
                }
                view.showNames(nameOwner, nameCompany);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
