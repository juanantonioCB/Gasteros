package com.juanantonio.gasteros.presenter;

import com.juanantonio.gasteros.interfaces.ListExpensesInterface;

public class ListExpensesPresenter implements ListExpensesInterface.Presenter {

    ListExpensesInterface.View view;

    public ListExpensesPresenter(ListExpensesInterface.View view) {
        this.view = view;
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void addNewList(String name) {

    }




}
