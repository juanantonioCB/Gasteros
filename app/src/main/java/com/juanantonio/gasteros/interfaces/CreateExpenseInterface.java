package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.Expenses;

public interface CreateExpenseInterface {
    public interface Presenter {
        void createExpense();
        void saveExpense(Expenses e);
        void comeBack();
    }

    public interface View {
        void createExpense();
        void comeBack();
    }
}
