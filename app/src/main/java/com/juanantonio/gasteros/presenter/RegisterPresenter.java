package com.juanantonio.gasteros.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juanantonio.gasteros.interfaces.RegisterInterface;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class RegisterPresenter implements RegisterInterface.Presenter {
private Context mContext;

    public RegisterPresenter(Context mContext) {
        this.mContext = mContext;

    }


}
