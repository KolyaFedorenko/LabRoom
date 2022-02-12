package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Employee> employees;

    EmployeeAdapter(Context context, List<Employee> employees){
        this.employees = employees;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        Employee employee = employees.get(position);

        long longId = employee.getId();
        String strId = Long.toString(longId);
        holder.outIdView.setText(strId);

        holder.outNameView.setText(employee.getName());

        holder.outPositionView.setText(employee.getPosition());

        float flSalary = employee.getSalary();
        String strSalary = Float.toString(flSalary);
        holder.outSalaryView.setText(strSalary);

        boolean blFired = employee.isFired();
        String strFired = Boolean.toString(blFired);
        holder.outFiredView.setText(strFired);

        holder.outReceiptView.setText(employee.getReceiptDate());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView outIdView;
        final TextView outNameView;
        final TextView outPositionView;
        final TextView outSalaryView;
        final TextView outFiredView;
        final TextView outReceiptView;
        ViewHolder(View view){
            super(view);
            outIdView = view.findViewById(R.id.outId);
            outNameView = view.findViewById(R.id.outName);
            outPositionView = view.findViewById(R.id.outPosition);
            outSalaryView = view.findViewById(R.id.outSalary);
            outFiredView = view.findViewById(R.id.outFired);
            outReceiptView = view.findViewById(R.id.outReceipt);
        }
    }
}

