package com.juanantonio.gasteros.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.juanantonio.gasteros.interfaces.ListExpensesInterface;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.ArrayList;
import java.util.List;

public class ListExpensesPresenter implements ListExpensesInterface.Presenter {

    private ListExpensesInterface.View view;
    private FirebaseAuth mAuth;
    private DatabaseReference dr;
    private ArrayList<ListExpenses> listExpenses = new ArrayList<>();

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

        list.setId(dr.child("Listas").push().getKey());

        this.dr.child("Listas").push().setValue(list);
        System.out.println("okkkkk");
        view.showToast("Lista añadida correctamente");

        view.showToast("Lista añadida correctamente");
    }

    @Override
    public void loadList() {
        mAuth = FirebaseAuth.getInstance();
        String ownerUid = mAuth.getCurrentUser().getUid();
        Query q = dr.child("Listas").orderByChild("ownerId").equalTo(ownerUid);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listExpenses.removeAll(listExpenses);
                if (dataSnapshot.exists()) {
                    for (DataSnapshot lista : dataSnapshot.getChildren()) {
                        ListExpenses le = lista.getValue(ListExpenses.class);
                        listExpenses.add(le);
                    }
                }
                view.loadList(listExpenses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        System.out.println(listExpenses);

    }

    @Override
    public void removeList(String id) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Listas").orderByChild("id").equalTo(id);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot listSnapshot : dataSnapshot.getChildren()) {
                    listSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", "onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void openListExpenses(String id, String ownerId, String companyId) {
        view.openListExpenses(id, ownerId, companyId);
    }


}
