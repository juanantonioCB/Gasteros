package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.ExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.presenter.ExpensesAdapter;
import com.juanantonio.gasteros.presenter.ExpensesPresenter;
import com.juanantonio.gasteros.presenter.ListExpensesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity implements ExpensesInterface.View, ExpensesAdapter.OnExpenseListener {

    ExpensesInterface.Presenter presenter;
    String idList;
    private TextView title;
    private RecyclerView rv;
    private List<Expenses> expenses;
    private FloatingActionButton addExpenseButton;
    private ExpensesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.idList = getIntent().getStringExtra("id");
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
        this.rv = findViewById(R.id.ExpensesRecyclerView);
        this.rv.setHasFixedSize(true);
        this.rv.setLayoutManager(new LinearLayoutManager(this));
        presenter.changeTitle(idList);
        presenter.loadExpenses(idList);
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
    public void openCreateExpense(String idList) {
        Intent i = new Intent(this, CreateExpenseActivity.class);
        i.putExtra("id", idList);
        startActivity(i);
    }

    @Override
    public void onExpenseClick(int position) {
        System.out.println("CLICKKK " + position);
    }
}
