package com.juanantonio.gasteros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juanantonio.gasteros.GlobalApplication;
import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.ExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.presenter.ExpensesAdapter;
import com.juanantonio.gasteros.presenter.ExpensesPresenter;
import com.juanantonio.gasteros.presenter.ListExpensesAdapter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity implements ExpensesInterface.View, ExpensesAdapter.OnExpenseListener {

    ExpensesInterface.Presenter presenter;
    String idList;
    String ownerId;
    String companyId;
    private TextView title, name, owner;
    private RecyclerView rv;
    private List<Expenses> expenses;
    private FloatingActionButton addExpenseButton;
    private ExpensesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.idList = getIntent().getStringExtra("id");
        this.ownerId = getIntent().getStringExtra("ownerId");
        this.companyId = getIntent().getStringExtra("companyId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        presenter = new ExpensesPresenter(this);
        this.addExpenseButton = findViewById(R.id.addExpenseButton);
        this.addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openCreateExpense(idList);
            }
        });
        this.title = findViewById(R.id.titleExpense);
        this.name = findViewById(R.id.ownerTextView);
        this.owner = findViewById(R.id.companyTextView);
        this.rv = findViewById(R.id.ExpensesRecyclerView);
        this.rv.setHasFixedSize(true);
        this.rv.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String id = expenses.get(viewHolder.getAdapterPosition()).getIdExpense();
                presenter.removeExpense(id);
                adapter.removeAt(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        presenter.changeTitle(idList);
        presenter.loadExpenses(idList);
        presenter.loadNames(ownerId, companyId);
    }

    @Override
    public void changeTitle(String title) {
        this.title.setText(title);

    }

    @Override
    public void loadExpenses(List<Expenses> expenses) {
        if (expenses == null) {
            expenses = new ArrayList<>();
        }
        this.expenses = expenses;
        this.adapter = new ExpensesAdapter((ArrayList<Expenses>) expenses, this, this);
        rv.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void loadExpense(String idExpense) {
        Intent i = new Intent(this, CreateExpenseActivity.class);
        i.putExtra("idExpense", idExpense);
        i.putExtra("id", idList);
        startActivity(i);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(GlobalApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNames(String owner, String company) {
        this.name.setText("Propietario: " + owner);
        this.owner.setText("Acompañante: " + company);
    }

    @Override
    public void openCreateExpense(String idList) {
        Intent i = new Intent(this, CreateExpenseActivity.class);
        i.putExtra("id", idList);
        startActivity(i);
    }

    @Override
    public void onExpenseClick(int position) {
        System.out.println("CLICKKK " + position);
        presenter.loadExpense(expenses.get(position).getIdExpense());
    }
}
