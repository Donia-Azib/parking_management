package com.example.parkinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterClass extends ArrayAdapter<Car> {

    private Context ctx;
    private TextView car_name,car_matricule,car_time,car_payee;

    public AdapterClass(@NonNull Context context,@NonNull ArrayList<Car> objects) {
        super(context, R.layout.car_item, objects);
        ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item,parent,false);

        car_matricule = convertView.findViewById(R.id.car_matricule);
        car_name = convertView.findViewById(R.id.car_name);
        car_payee = convertView.findViewById(R.id.car_money);
        car_time = convertView.findViewById(R.id.car_time);
//        --lezm 7wija wadh7a tfaser biha lena
        Car OneCar = getItem(position);
        car_name.setText(OneCar.getName());
        car_matricule.setText(OneCar.getMatricule());
        car_time.setText(OneCar.getTime());
        car_payee.setText(""+OneCar.isPayee());


        return convertView;
    }
}
