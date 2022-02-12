package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import android.app.Application;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button saveBtn;
    Button clearBtn;
    EditText eId;
    EditText eName;
    EditText ePosition;
    EditText eSalary;
    Switch eFired;
    EditText eReceiptDate;
    RecyclerView eList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        final EmployeeDao employeeDao = db.employeeDao();

        saveBtn = (Button) findViewById(R.id.saveButton);
        clearBtn = (Button) findViewById(R.id.clearButton);
        eId = (EditText) findViewById(R.id.employeeID);
        eName = (EditText) findViewById(R.id.employeeName);
        ePosition = (EditText) findViewById(R.id.employeePosition);
        eSalary = (EditText) findViewById(R.id.employeeSalary);
        eFired = (Switch) findViewById(R.id.employeeFired);
        eReceiptDate = (EditText) findViewById(R.id.employeeReceiptDate);
        eList = (RecyclerView) findViewById(R.id.employeeList);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empId = eId.getText().toString();
                String empName = eName.getText().toString();
                String empPos = ePosition.getText().toString();
                String empSalary = eSalary.getText().toString();
                String empReceiptDate = eReceiptDate.getText().toString();
                boolean empFired;
                if(eFired.isChecked()){
                    empFired = true;
                }
                else {
                    empFired = false;
                }

                try {
                    Employee employee = new Employee();
                    employee.id = Long.parseLong(empId);
                    employee.name = empName;
                    employee.position = empPos;
                    employee.salary = Float.parseFloat(empSalary);
                    employee.isFired = empFired;
                    employee.receiptDate = empReceiptDate;

                    employeeDao.insert(employee);
                    List<Employee> employees = employeeDao.getAll();
                    EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this, employees);
                    eList.setAdapter(adapter);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Введите верные данные", Toast.LENGTH_LONG).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employeeDao.clear();
                List<Employee> employees = employeeDao.getAll();
                EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this, employees);
                eList.setAdapter(adapter);
            }
        });
    }

}