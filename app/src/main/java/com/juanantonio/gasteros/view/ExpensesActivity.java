package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.ExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.presenter.ExpensesAdapter;
import com.juanantonio.gasteros.presenter.ExpensesPresenter;
import com.juanantonio.gasteros.presenter.ListExpensesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity implements ExpensesInterface.View, ExpensesAdapter.OnExpenseListener{

    ExpensesInterface.Presenter presenter;
    String id;
    private TextView title;
    private RecyclerView rv;
    private List<Expenses> expenses;
    ExpensesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.id = getIntent().getStringExtra("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        presenter = new ExpensesPresenter(this, id);
        this.title = findViewById(R.id.titleExpense);
        this.rv = findViewById(R.id.ExpensesRecyclerView);
        presenter.changeTitle(id);
    }

    @Override
    public void changeTitle(String title) {
        this.title.setText(title);

    }

    @Override
    public void loadList(List<Expenses> expenses) {
        if (expenses == null) {
            expenses = new ArrayList<>();
        }
        this.expenses=expenses;
        this.adapter=new ExpensesAdapter((ArrayList<Expenses>) expenses,this,this);
        rv.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onExpenseClick(int position) {
        System.out.println("CLICKKK "+position);
    }
}
