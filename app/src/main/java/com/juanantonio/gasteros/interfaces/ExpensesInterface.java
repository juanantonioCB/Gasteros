package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.model.ListExpenses;

import java.util.List;

public interface ExpensesInterface {
    public interface View {
        void changeTitle(String title);
        void openCreateExpense(String idList);
        void loadExpenses(List<Expenses> expenses);
        void loadExpense(String idExpense);
        void showToast(String msg);
        void showNames(String owner, String company);
    }

    public interface Presenter {
        void changeTitle(String id);
        void openCreateExpense(String idList);
        void loadExpenses(String idList);
        String getName(String Uid);
        void loadExpense(String idExpense);
        void removeExpense(String id);
        void loadNames(String ownerId, String companyId);
    }
}
