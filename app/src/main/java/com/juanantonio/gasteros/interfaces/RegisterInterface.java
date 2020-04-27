package com.juanantonio.gasteros.interfaces;

public interface RegisterInterface {
    public interface View {
        void createUser();
    }

    public interface Presenter {
        void createUser();
        void registerUser(String name, String email, String pass);
    }
}
