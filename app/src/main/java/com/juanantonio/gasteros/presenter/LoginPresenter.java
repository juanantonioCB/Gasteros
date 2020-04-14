package com.juanantonio.gasteros.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juanantonio.gasteros.GlobalApplication;
import com.juanantonio.gasteros.interfaces.LoginInterface;
import com.juanantonio.gasteros.view.LoginActivity;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginInterface.Presenter {
    private LoginInterface.View view;
    private FirebaseAuth mAuth;
    private Context mContext;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        this.mContext = GlobalApplication.getAppContext();

    }


    @Override
    public void openRegister() {
        System.out.println("aaaaa");
        view.openRegister();
    }


    public void loginUser(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            openApp();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            System.out.println("errorrrrr");
                            Toast.makeText(mContext, "Email o contraseña incorrectas", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    public void openApp() {
        view.openApp();
    }


}
