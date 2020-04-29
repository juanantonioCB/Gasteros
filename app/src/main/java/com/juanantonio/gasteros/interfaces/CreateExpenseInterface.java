package com.juanantonio.gasteros.interfaces;

import com.juanantonio.gasteros.model.Expenses;

public interface CreateExpenseInterface {
    public interface Presenter {
        void createExpense();
        void saveExpense(Expenses e);
        void comeBack();
        void loadExpense(String id);
        void loadHour();
        void loadDate();
        void nowDate();
        void updateExpense(Expenses e);
    }

    public interface View {
        void createExpense();
        void comeBack();
        void loadExpense(Expenses expense);
        void loadHour();
        void loadDate();
        void nowDate();
        void updateExpense();
    }
}
