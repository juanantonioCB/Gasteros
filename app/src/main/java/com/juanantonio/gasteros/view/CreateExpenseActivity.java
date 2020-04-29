package com.juanantonio.gasteros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juanantonio.gasteros.R;
import com.juanantonio.gasteros.interfaces.CreateExpenseInterface;
import com.juanantonio.gasteros.model.Expenses;
import com.juanantonio.gasteros.presenter.CreateExpensePresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateExpenseActivity extends AppCompatActivity implements CreateExpenseInterface.View {

    private String idList;
    private String idUser;
    private FirebaseAuth mAuth;
    private EditText titleEditText, amountEditText, dateEditText, hourEditText;
    private Button nowButton, saveButton;
    private CreateExpenseInterface.Presenter presenter;
    private String idExpense;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        this.presenter = new CreateExpensePresenter(this);
        this.idList = getIntent().getStringExtra("id");
        this.idExpense = getIntent().getStringExtra("idExpense");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        this.titleTextView = findViewById(R.id.titleTextViewCreateExpense);
        this.idUser = user.getUid();
        this.titleEditText = findViewById(R.id.titleCreateExpenseEditText);
        this.nowButton = findViewById(R.id.nowButtonCreateExpenseEditText);
        this.nowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nowDate();
            }
        });
        this.amountEditText = findViewById(R.id.amountCreateExpenseEditText);
        this.dateEditText = findViewById(R.id.dateCreateExpenseEditText);
        this.dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadDate();
            }
        });
        this.nowButton = findViewById(R.id.nowButtonCreateExpenseEditText);
        this.saveButton = findViewById(R.id.saveButtonCreateExpenseEditText);
        this.hourEditText = findViewById(R.id.hourCreateExpenseEditText);
        this.hourEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadHour();
            }
        });
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idExpense == null) {
                    presenter.createExpense();
                } else {
                    createUpdateExpense();
                }
            }
        });
        if (this.idExpense != null) {
            presenter.loadExpense(this.idExpense);
        }

    }

    private void createUpdateExpense() {
        Expenses e = new Expenses();
        e.setIdExpense(idExpense);
        e.setName(this.titleEditText.getText().toString());
        e.setAmount(Float.parseFloat(this.amountEditText.getText().toString()));
        e.setListId(this.idList);
        e.setUserId(this.idUser);
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(this.dateEditText.getText().toString()));
            Calendar hora = Calendar.getInstance();
            hora.setTime(h.parse(this.hourEditText.getText().toString()));
            c.set(Calendar.HOUR, hora.get(Calendar.HOUR));
            c.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
            e.setDate(c.getTimeInMillis());
            presenter.updateExpense(e);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createExpense() {
        Expenses e = new Expenses();
        e.setName(this.titleEditText.getText().toString());
        e.setAmount(Float.parseFloat(this.amountEditText.getText().toString()));
        e.setListId(this.idList);
        e.setUserId(this.idUser);
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(this.dateEditText.getText().toString()));
            Calendar hora = Calendar.getInstance();
            hora.setTime(h.parse(this.hourEditText.getText().toString()));
            c.set(Calendar.HOUR, hora.get(Calendar.HOUR));
            c.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
            e.setDate(c.getTimeInMillis());
            presenter.saveExpense(e);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void comeBack() {
        finish();
    }

    @Override
    public void loadExpense(Expenses expense) {
        this.titleTextView.setText("Editar Gasto");
        this.titleEditText.setText(expense.getName());
        this.amountEditText.setText(String.valueOf(expense.getAmount()));
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(expense.getDate());
        SimpleDateFormat f = new SimpleDateFormat("dd/M/y");
        this.dateEditText.setText(f.format(c.getTime()));
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        this.hourEditText.setText(h.format(c.getTime()));
    }

    @Override
    public void loadHour() {
        Calendar c = Calendar.getInstance();
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada = (hourOfDay < 10) ? ("0" + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10) ? String.valueOf("0" + minute) : String.valueOf(minute);
                hourEditText.setText(horaFormateada + ":" + minutoFormateado);
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);

        recogerHora.show();
    }

    @Override
    public void loadDate() {
        Calendar c = Calendar.getInstance();
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10) ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10) ? "0" + String.valueOf(mesActual) : String.valueOf(mesActual);
                dateEditText.setText(diaFormateado + "/" + mesFormateado + "/" + year);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        recogerFecha.show();
    }

    @Override
    public void nowDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat d = new SimpleDateFormat("d/M/y");
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        this.dateEditText.setText(d.format(c.getTime()));
        this.hourEditText.setText(h.format(c.getTime()));
    }

    @Override
    public void updateExpense() {

    }

}
