package com.example.parkinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class CarDataBaseSqLite extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public CarDataBaseSqLite(@Nullable Context context) {
        super(context, "car_db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        1 : true ==> 0 : false
        String onCreateTabSql = "CREATE TABLE car (id INTEGER PRIMARY KEY , name VARCHAR(20) , matricule VARCHAR(20), time VARCHAR(20) , payee INTEGER )";
        db.execSQL(onCreateTabSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String onUpgradeTableSql = "DROP TABLE IF EXISTS car ";
        db.execSQL(onUpgradeTableSql);
        onCreate(db);

    }


    public void AddCarToCarTable(Car car)
    {
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",car.getName());
        values.put("matricule",car.getMatricule());
        values.put("time",car.getTime());
        values.put("payee",car.isPayee()? 1 : 0);
        db.insert("car",null,values);
    }

    public void DeleteCarFromCarTable(int car_id)
    {
        SQLiteDatabase db  = getWritableDatabase();
        db.delete("car","id = ?",new String[]{String.valueOf(car_id)});
    }

    public void UpdateCarFromCarTable(Car car)
    {
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",car.getName());
        values.put("matricule",car.getMatricule());
        values.put("time",car.getTime());
        values.put("payee",car.isPayee()? 1 : 0);
        db.update("car",values,"id=?",new String[]{String.valueOf(car.getId())});
    }

    public ArrayList<Car> GetAllStoredCar()
    {
        SQLiteDatabase db  = getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();
        Car oneCar;
        String selectAllSql = "SELECT * FROM car";
        Cursor cursor = db.rawQuery(selectAllSql,null);
        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String matricule = cursor.getString(2);
                String time = cursor.getString(3);
                int payee = cursor.getInt(4);
                oneCar = new Car(id,name,matricule,time, payee == 1);
                cars.add(oneCar);
            }while (cursor.moveToNext());
        }

        return cars;
    }

    public Car getItemByIdFromCarTable(int id)
    {
        SQLiteDatabase db  = getReadableDatabase();
        Car oneCar=null;
        String selectAllSql = "SELECT * FROM car WHERE id = "+id;
        Cursor cursor = db.rawQuery(selectAllSql,null);
        if(cursor.moveToFirst())
        {
            String name = cursor.getString(1);
            String matricule = cursor.getString(2);
            String time = cursor.getString(3);
            int payee = cursor.getInt(4);
            oneCar = new Car(id,name,matricule,time, payee == 1);

        }
        return oneCar;
    }


}
