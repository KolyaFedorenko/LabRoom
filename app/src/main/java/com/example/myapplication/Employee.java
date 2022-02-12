package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey
    public long id;
    public String name;
    public String position;
    public float salary;
    public boolean isFired = false;
    public String receiptDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public float getSalary() {
        return salary;
    }

    public boolean isFired() {
        return isFired;
    }

    public String getReceiptDate() {
        return receiptDate;
    }
}
