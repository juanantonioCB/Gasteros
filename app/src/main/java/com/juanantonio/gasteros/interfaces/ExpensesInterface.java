package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.List;

public interface ExpensesInterface {
    public interface View {
        void changeTitle(String title);

        void openCreateExpense(String idList);
        void loadExpenses(List<Expenses> expenses);
    }

    public interface Presenter {
        void changeTitle(String id);
        void openCreateExpense(String idList);
        void loadExpenses(String idList);
    }
}
