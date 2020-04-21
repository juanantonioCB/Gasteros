package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.List;

public interface ExpensesInterface {
    public interface View {
        void changeTitle(String title);
        void loadList(List<Expenses> expenses);
    }

    public interface Presenter {
        void changeTitle(String id);
        void loadExpense(String id);
        void setListExpense(ListExpenses l);
        void loadList();
    }
}
