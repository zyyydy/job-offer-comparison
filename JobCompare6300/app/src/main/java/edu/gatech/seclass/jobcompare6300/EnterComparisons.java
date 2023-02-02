package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterComparisons extends AppCompatActivity {

    private Spinner remote;
    private Spinner salary;
    private Spinner bonus;
    private Spinner benefit;
    private Spinner leave;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_comparison);
        this.setComparionView();
    }

    private void setDropdownOptions(Spinner dropdown, int options) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
    }

    private void setComparionView() {
        this.remote = (Spinner)findViewById(R.id.enterRemoteWeight);
        this.salary = (Spinner)findViewById(R.id.enterSalaryWeight);
        this.bonus = (Spinner)findViewById(R.id.enterBonusWeight);
        this.benefit = (Spinner)findViewById(R.id.enterBenefitWeight);
        this.leave = (Spinner)findViewById(R.id.enterLeaveWeight);

        setDropdownOptions(this.remote, R.array.weight_selection_array);
        setDropdownOptions(this.salary, R.array.weight_selection_array);
        setDropdownOptions(this.bonus, R.array.weight_selection_array);
        setDropdownOptions(this.benefit, R.array.weight_selection_array);
        setDropdownOptions(this.leave, R.array.weight_selection_array);

        this.db = openOrCreateDatabase("jobcompare", MODE_PRIVATE, null);
        String sql = "SELECT * from `comparisons` LIMIT 1;";
        Cursor resultSet = db.rawQuery(sql,null);
        resultSet.moveToFirst();
        Comparison comp = new Comparison(resultSet);
        this.remote.setSelection(comp.getRemote()-1);
        this.salary.setSelection(comp.getSalary()-1);
        this.bonus.setSelection(comp.getBonus()-1);
        this.benefit.setSelection(comp.getBenefit()-1);
        this.leave.setSelection(comp.getLeave()-1);
    }

    private Comparison validateComparisons() {
        Integer remoteWeight;
        try {
            remoteWeight = Integer.parseInt(remote.getSelectedItem().toString());
        } catch (Exception e) {
            remoteWeight = 0;
        }
        Integer salaryWeight;
        try {
            salaryWeight = Integer.parseInt(salary.getSelectedItem().toString());
        } catch (Exception e) {
            salaryWeight = 0;
        }
        Integer bonusWeight;
        try {
            bonusWeight = Integer.parseInt(bonus.getSelectedItem().toString());
        } catch (Exception e) {
            bonusWeight = 0;
        }
        Integer benefitWeight;
        try {
            benefitWeight = Integer.parseInt(benefit.getSelectedItem().toString());
        } catch (Exception e) {
            benefitWeight = 0;
        }
        Integer leaveWeight;
        try {
            leaveWeight = Integer.parseInt(leave.getSelectedItem().toString());
        } catch (Exception e) {
            leaveWeight = 0;
        }
        return new Comparison(remoteWeight, salaryWeight, bonusWeight, benefitWeight, leaveWeight);
    }

    private void saveWeights(View view) {
        Comparison comp = this.validateComparisons();
        String sql = "UPDATE `comparisons` SET " +
                "`remote`="+comp.getRemote()+", `salary`="+comp.getSalary()+", `bonus`="+comp.getBonus()+", `benefit`="+comp.getBenefit()+", `leave`=="+comp.getLeave()+";";
        db.execSQL(sql);
        Toast.makeText(getApplicationContext(), "Comparison weights updated", Toast.LENGTH_SHORT).show();
    }

    private void cancel(View view) {
        Intent intent = new Intent(EnterComparisons.this, MainActivity.class);
        startActivity(intent);
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.updateWeights:
                this.saveWeights(view);
                break;
            case R.id.cancelWeights:
                this.cancel(view);
                break;
        }
    }

}
