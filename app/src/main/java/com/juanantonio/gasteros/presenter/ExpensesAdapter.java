package com.juanantonio.gasteros.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.model.Expenses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    private List<Expenses> expensesList;
    private Context context;
    private OnExpenseListener onExpenseListener;

    public ExpensesAdapter(ArrayList<Expenses> expenses, Context context, OnExpenseListener onExpenseListener) {
        this.expensesList = expenses;
        this.context = context;
        this.onExpenseListener = onExpenseListener;
    }

    @NonNull
    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(v, onExpenseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ViewHolder holder, int position) {
        Expenses expenses = expensesList.get(position);
        holder.title.setText(expenses.getName());
        holder.amount.setText(String.valueOf(expenses.getAmount()));
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(expenses.getDate());
        SimpleDateFormat f = new SimpleDateFormat("dd/M/y");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        f.format(c.getTime());
        holder.date.setText(f.format(c.getTime()) + " | " + h.format(c.getTime()));
        holder.name.setText(expenses.getUserId());
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, amount, date, name;
        OnExpenseListener onExpenseListener;

        public ViewHolder(@NonNull View itemView, OnExpenseListener onExpenseListener) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titleExpenseRecyclerView);
            this.amount = itemView.findViewById(R.id.amountExpenseRecyclerView);
            this.date = itemView.findViewById(R.id.dateExpenseRecyclerView);
            this.name = itemView.findViewById(R.id.nameExpenseRecyclerView);
            this.onExpenseListener = onExpenseListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onExpenseListener.onExpenseClick(getAdapterPosition());
        }
    }

    public interface OnExpenseListener {
        void onExpenseClick(int position);
    }
}
