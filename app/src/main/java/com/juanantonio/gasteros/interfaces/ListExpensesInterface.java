package com.juanantonio.gasteros.interfaces;

public interface ListExpensesInterface {
    public interface View {
        void openDialog();
        void showToast(String message);
    }

    public interface Presenter {
        void openDialog();
        void addNewList(String name);


    }
}
