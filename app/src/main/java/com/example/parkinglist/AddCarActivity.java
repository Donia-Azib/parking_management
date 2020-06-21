package com.example.parkinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddCarActivity extends AppCompatActivity {

    private Button add_btn;
    private TextInputLayout edit_name,edit_matricule,edit_time;
    private TimePickerDialog picker;
    private RadioGroup radioGroup;
    private RadioButton radioBtn;
    private CarDataBaseSqLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        add_btn = findViewById(R.id.add_btn);
        edit_matricule = findViewById(R.id.edit_car_matricule);
        edit_name = findViewById(R.id.edit_car_name);
        edit_time = findViewById(R.id.edit_car_time);
        radioGroup = findViewById(R.id.radioGroup);

        edit_time.getEditText().setFocusable(false);
        edit_time.setFocusable(false);


        edit_time.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(AddCarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edit_time.getEditText().setText(hourOfDay+" : "+minute);
                    }
                },hour,minutes,true);
                picker.show();
            }
        });

        db = new CarDataBaseSqLite(AddCarActivity.this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty())
                    Toast.makeText(AddCarActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                else
                {
//                    --add in db
                    radioBtn = findViewById(radioGroup.getCheckedRadioButtonId());


                    db.AddCarToCarTable(new Car(edit_name.getEditText().getText().toString(),
                            edit_matricule.getEditText().getText().toString(),
                            edit_time.getEditText().getText().toString(),
                            radioBtn.getText().toString().equals("Oui")));
                    Toast.makeText(AddCarActivity.this, " car add with success ... !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCarActivity.this,MainActivity.class));
                }
            }
        });

    }


    public boolean isEmpty()
    {
        return  TextUtils.isEmpty(edit_name.getEditText().getText()) ||
                TextUtils.isEmpty(edit_time.getEditText().getText()) ||
                TextUtils.isEmpty(edit_matricule.getEditText().getText()) ||
                radioGroup.getCheckedRadioButtonId() == -1;

    }


}
