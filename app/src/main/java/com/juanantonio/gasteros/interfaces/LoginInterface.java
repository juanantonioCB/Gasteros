package com.juanantonio.gasteros.interfaces;

public interface LoginInterface {

    interface View {
        void openRegister();
        void openApp();
    }

    interface Presenter {
        void openRegister();
        void loginUser(String email, String password);
        void openApp();
    }
}
