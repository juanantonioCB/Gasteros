package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juanantonio.gasteros.GlobalApplication;
import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.ListExpensesInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;
import com.juanantonio.gasteros.presenter.ListExpensesAdapter;
import com.juanantonio.gasteros.presenter.ListExpensesPresenter;

import java.util.ArrayList;
import java.util.List;

public class ListExpensesActivity extends AppCompatActivity implements ListExpensesInterface.View, ListExpensesAdapter.OnListExpenseListener {

    public ListExpensesInterface.Presenter presenter;
    public FloatingActionButton addListButton;
    public RecyclerView rv;
    public ListExpensesAdapter adapter;
    private ArrayList<ListExpenses> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenses);
        presenter = new ListExpensesPresenter(this);
        this.rv = findViewById(R.id.listRecyclerView);
        this.rv.setHasFixedSize(true);
        this.rv.setLayoutManager(new LinearLayoutManager(this));

        addListButton = findViewById(R.id.addListButton);
        addListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openDialog();
            }
        });
        presenter.loadList();
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                String id = list.get(viewHolder.getAdapterPosition()).getId();
                presenter.removeList(id);
                adapter.removeAt(viewHolder.getAdapterPosition());
                //presenter.getPersons();
                Toast.makeText(getApplicationContext(), "Borrado Correctamente", Toast.LENGTH_SHORT).show();
                Log.d("Borrado", String.valueOf(viewHolder.getAdapterPosition()));
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                return true;
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
        //presenter.getPersons();

    }


    public void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nombre de la lista");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.addNewList(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadList(List<ListExpenses> listExpenses) {
        if (listExpenses == null) {
            listExpenses = new ArrayList<>();
        }
        this.list = (ArrayList<ListExpenses>) listExpenses;
        adapter = new ListExpensesAdapter((ArrayList<ListExpenses>) listExpenses, this, this);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void openListExpenses(String id) {
        Intent i = new Intent(this, ExpensesActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }


    @Override
    public void onListExpenseClick(int position) {
        presenter.openListExpenses(this.list.get(position).getId());
    }
}
