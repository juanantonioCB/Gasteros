package com.juanantonio.gasteros.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.ArrayList;
import java.util.List;

public class ListExpensesAdapter extends RecyclerView.Adapter<ListExpensesAdapter.ViewHolder> {
    private List<ListExpenses> listExpenses;
    private Context context;
    private OnListExpenseListener onListExpenseListener;

    public ListExpensesAdapter(ArrayList<ListExpenses> listExpenses, Context context, OnListExpenseListener onListExpenseListener) {
        this.listExpenses = listExpenses;
        this.context = context;
        this.onListExpenseListener = onListExpenseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_expenses, parent, false);
        return new ViewHolder(v, onListExpenseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListExpenses expense = listExpenses.get(position);
        holder.title.setText(expense.getName());
    }

    @Override
    public int getItemCount() {
        return listExpenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnListExpenseListener onListExpenseListener;
        public TextView title;

        public ViewHolder(@NonNull View itemView, OnListExpenseListener onListExpenseListener) {
            super(itemView);
            title = itemView.findViewById(R.id.titleListExpense);
            this.onListExpenseListener = onListExpenseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onListExpenseListener.onListExpenseClick(getAdapterPosition());
        }
    }

    public interface OnListExpenseListener {
        void onListExpenseClick(int position);
    }

    public void removeAt(int positon) {
        listExpenses.remove(positon);
        notifyItemRemoved(positon);
        notifyItemRangeChanged(positon, listExpenses.size());
    }
}
