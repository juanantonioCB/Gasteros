package com.juanantonio.gasteros.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.juanantonio.gasteros.interfaces.RegisterInterface;
import com.juanantonio.gasteros.model.User;


public class RegisterPresenter implements RegisterInterface.Presenter {
    private RegisterInterface.View view;
    private FirebaseAuth mAuth;
    private DatabaseReference dr;

    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
        this.dr = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public void createUser() {
        view.createUser();
    }

    @Override
    public void registerUser(final String name, final String email, final String pass) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User();
                    String id = mAuth.getCurrentUser().getUid();
                    user.setUid(id);
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(pass);
                    dr.child("Users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            view.showToast("Usuario creado correctamente");
                            view.exit();
                        }
                    });
                } else {
                    view.showToast("Este usuario ya existe");
                }
            }
        });
    }
}
