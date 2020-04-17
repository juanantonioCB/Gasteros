package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.ListExpenses;

import java.util.List;

public interface ListExpensesInterface {
    public interface View {
        void openDialog();
        void showToast(String message);
        void loadList(List<ListExpenses> listExpenses);

    }

    public interface Presenter {
        void openDialog();
        void addNewList(String name);
        void loadList();
        void removeList(String id);
    }
}
