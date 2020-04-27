package com.juanantonio.gasteros.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juanantonio.gasteros.GlobalApplication;
import com.juanantonio.gasteros.interfaces.RegisterInterface;
import com.juanantonio.gasteros.view.RegisterActivity;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterPresenter implements RegisterInterface.Presenter {
    private RegisterInterface.View view;
    private FirebaseAuth mAuth;
    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
    }


    @Override
    public void createUser() {
        view.createUser();
    }

    @Override
    public void registerUser(String name, String email, String pass) {
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(GlobalApplication.getAppContext().this, "Authentication failed.", Toast.LENGTH_SHORT);

                        }

                    }
                });
    }
}
