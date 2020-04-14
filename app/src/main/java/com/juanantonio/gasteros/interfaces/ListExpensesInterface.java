package com.juanantonio.gasteros.interfaces;

public interface ListExpensesInterface {
    public interface View {
        void openDialog();
    }

    public interface Presenter {
        void openDialog();
        void addNewList(String name);


    }
}
