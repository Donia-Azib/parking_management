package com.example.parkinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class UpdateCarDescriptionActivity extends AppCompatActivity {
    private Button update_btn;
    private TextInputLayout edit_name,edit_matricule,edit_time;
    private RadioGroup radioGroup;
    private RadioButton yes,no,radioBtn;
    private CarDataBaseSqLite db ;
    private int car_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car_description);


        update_btn = findViewById(R.id.update_btn);
        edit_matricule = findViewById(R.id.edit_car_matricule);
        edit_name = findViewById(R.id.edit_car_name);
        edit_time = findViewById(R.id.edit_car_time);
        radioGroup = findViewById(R.id.radioGroup);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        car_id = getIntent().getIntExtra("car_id",0);
        db = new CarDataBaseSqLite(this);
//        --COMPLETE FIELDS
        Car old_car_desc = db.getItemByIdFromCarTable(car_id);
        edit_matricule.getEditText().setText(old_car_desc.getMatricule());
        edit_name.getEditText().setText(old_car_desc.getName());
        edit_time.getEditText().setText(old_car_desc.getTime());
        if (old_car_desc.isPayee())
            yes.setChecked(true);
         else
            no.setChecked(true);


        edit_time.getEditText().setFocusable(false);
        edit_time.setFocusable(false);


        edit_time.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                TimePickerDialog picker = new TimePickerDialog(UpdateCarDescriptionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edit_time.getEditText().setText(hourOfDay+" : "+minute);
                    }
                },hour,minutes,true);
                picker.show();
            }
        });



//         --UPDATE TEH OLD DESC
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty())
                    Toast.makeText(UpdateCarDescriptionActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                else
                {
//                    --add in db
                    radioBtn = findViewById(radioGroup.getCheckedRadioButtonId());
                    db.UpdateCarFromCarTable(new Car(car_id,edit_name.getEditText().getText().toString(),
                            edit_matricule.getEditText().getText().toString(),
                            edit_time.getEditText().getText().toString(),
                            radioBtn.getText().toString().equals("Oui")));
                    Toast.makeText(UpdateCarDescriptionActivity.this, " car Updated with success ... !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateCarDescriptionActivity.this,MainActivity.class));
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
