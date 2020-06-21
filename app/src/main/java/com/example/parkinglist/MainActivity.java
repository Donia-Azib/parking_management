package com.example.parkinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView car_list;
    private FloatingActionButton btn_add;
    private AdapterClass adapterClass;
    private CarDataBaseSqLite db;
    private ArrayList<Car> car_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car_list = findViewById(R.id.car_list);
        btn_add = findViewById(R.id.btn_add_car);

        db = new CarDataBaseSqLite(this);
        car_array = db.GetAllStoredCar();

        adapterClass = new AdapterClass(this,car_array);
        car_list.setAdapter(adapterClass);


        car_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car_item = (Car) parent.getItemAtPosition(position);

                startActivity(new Intent(MainActivity.this,UpdateCarDescriptionActivity.class)
                .putExtra("car_id",car_item.getId()));
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddCarActivity.class));
            }
        });

        car_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Car car_item = (Car) parent.getItemAtPosition(position);
                db.DeleteCarFromCarTable(car_item.getId());
                car_array.remove(position);
                adapterClass.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "car deleted with success ... !", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapterClass!=null)
            adapterClass.notifyDataSetChanged();
    }
}
