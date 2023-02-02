package edu.gatech.seclass.jobcompare6300;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EnterJobDetails extends AppCompatActivity {

    private EditText title;
    private EditText company;
    private EditText city;
    private EditText state;
    private EditText costOfLiving;
    private Spinner remote;
    private EditText salary;
    private EditText bonus;
    private EditText benefits;
    private EditText leave;
    private SQLiteDatabase db;
    private String currentOrOffer;
    private Boolean currentJobExists = false;
    private Integer currentJobId = null;
    private static final String TAG = "EnterJobDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_job_details);
        this.setupLayout();
        String layoutTitle = this.setCurrentOrOffer();
        TextView appTitle = (TextView)findViewById(R.id.enterJobDetailsTitle);
        appTitle.setText(layoutTitle);
        JobDetails currentJob = this.getCurrentJob();
        this.fillInLayout(currentJob);
    }

    private void setupLayout() {
        this.title = (EditText)findViewById(R.id.enterJobTitle);
        this.company = (EditText)findViewById(R.id.enterCompany);
        this.city = (EditText)findViewById(R.id.enterCity);
        this.state = (EditText)findViewById(R.id.enterState);
        this.costOfLiving = (EditText)findViewById(R.id.enterCol);
        this.remote = (Spinner)findViewById(R.id.enterRemote);
        this.salary = (EditText)findViewById(R.id.enterSalary);
        this.bonus = (EditText)findViewById(R.id.enterBonus);
        this.benefits = (EditText)findViewById(R.id.enterBenefits);
        this.leave = (EditText)findViewById(R.id.enterLeavetime);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.remote_selection_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.remote.setAdapter(adapter);
    }

    private String setCurrentOrOffer() {
        Bundle b = getIntent().getExtras();
        if(b != null) {
            this.currentOrOffer = b.getString("currentOrOffer");
        } else {
            Log.e(TAG, "Missing currentOrOffer");
            this.currentOrOffer = "offer"; // default to offer
        }
        if (currentOrOffer.equals("offer")) {
            return this.getResources().getString(R.string.job_offer_app_title);
        } else if (currentOrOffer.equals("current")) {
            return this.getResources().getString(R.string.current_job_app_title);
        }
        Log.e(TAG, "Unknown error");
        return null;
    }

    private void fillInLayout(JobDetails currentJob) {
        if (currentOrOffer.equals("offer")) {
            if (this.currentJobExists) {
                Button compareJobsButton = (Button)findViewById(R.id.compareJobsButton);
                compareJobsButton.setEnabled(true);
            }
        } else if (currentOrOffer.equals("current")) {
            Button hideButton = (Button) findViewById(R.id.addOfferButton);
            hideButton.setVisibility(View.GONE);
            hideButton = (Button) findViewById(R.id.compareJobsButton);
            hideButton.setVisibility(View.GONE);
            if (currentJobExists) {
                this.title.setText(currentJob.getTitle());
                this.company.setText(currentJob.getCompany());
                this.city.setText(currentJob.getCity());
                this.state.setText(currentJob.getState());
                this.costOfLiving.setText(currentJob.getCostOfLiving().toString());
                this.remote.setSelection(currentJob.getRemote());
                this.salary.setText(currentJob.getSalary().toString());
                this.bonus.setText(currentJob.getBonus().toString());
                this.benefits.setText(currentJob.getBenefits().toString());
                this.leave.setText(currentJob.getLeave().toString());
            }
        } else {
            Log.v(TAG, "Invalid currentOrOffer");
        }
    }

    private JobDetails getCurrentJob() {
        this.db = openOrCreateDatabase("jobcompare", MODE_PRIVATE, null);
        String sql = "SELECT * from `job_details` WHERE `current`=1;";
        Cursor resultSet = db.rawQuery(sql,null);
        JobDetails currentJob = null;
        if (resultSet.moveToFirst()) {
            this.currentJobExists = true;
            this.currentJobId = Integer.parseInt(resultSet.getString(0));
            currentJob = new JobDetails(resultSet);
        } else {
            Log.v(TAG, "No current job in SQLite");
        }
        return currentJob;
    }

    private Boolean validRange(Integer check, Integer min, Integer max) {
        if (min == null) {
            return check < max;
        } else if (max == null) {
            return check > min;
        } else {
            return ((check > min) && (check < max));
        }
    }

    private JobDetails validateJobDetails() {
        Boolean error = false;
        String jobTitle = title.getText().toString();
        if (jobTitle.equals("")) {
            error = true;
            title.setError("You must enter a job title");
        }
        String jobCompany = company.getText().toString();
        if (jobCompany.equals("")) {
            error = true;
            company.setError("You must enter a company");
        }
        String jobCity = city.getText().toString();
        if (jobCity.equals("")) {
            error = true;
            city.setError("You must enter a city");
        }
        String jobState = state.getText().toString();
        if (jobState.equals("")) {
            error = true;
            state.setError("You must enter a state");
        }
        Integer jobCostOfLiving = 0;
        try {
            jobCostOfLiving = Integer.parseInt(costOfLiving.getText().toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString()); // continue
        } finally {
            if (!this.validRange(jobCostOfLiving, 50, 300)) {
                error = true;
                costOfLiving.setError("Invalid cost of living index (50-300)");
            }
        }
        Integer jobRemote = 0;
        try {
            jobRemote = Integer.parseInt(remote.getSelectedItem().toString());
        } finally {
            Log.v(TAG, "Spinner should prevent invalid user input"); // continue
        }
        Integer jobSalary = 0;
        try {
            jobSalary = Integer.parseInt(salary.getText().toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString()); // continue
        } finally {
            if (!this.validRange(jobSalary, 14500, null)) {
                error = true;
                salary.setError("Invalid annual salary (minimum wage: 14500)");
            }
        }
        Integer jobBonus = 0;
        try {
            jobBonus = Integer.parseInt(bonus.getText().toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString()); // continue
        } finally {
            if (!this.validRange(jobBonus, 0, null)) {
                error = true;
                bonus.setError("Invalid annual bonus (must be positive)");
            }
        }
        Integer jobBenefits = 0;
        try {
            jobBenefits = Integer.parseInt(benefits.getText().toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString()); // continue
        } finally {
            if (!this.validRange(jobBenefits, 0, 100)) {
                error = true;
                benefits.setError("Invalid benefits (0-100%)");
            }
        }
        Integer jobLeave = 0;
        try {
            jobLeave = Integer.parseInt(leave.getText().toString());
        } catch (Exception e) {
            Log.v(TAG, e.toString()); // continue
        } finally {
            if (!this.validRange(jobLeave, 0, 365)) {
                error = true;
                leave.setError("Invalid leave time (0-365 days)");
            }
        }

        if (error) {
            return null;
        } else {
            return new JobDetails(
                    jobTitle,
                    jobCompany,
                    jobCity,
                    jobState,
                    jobCostOfLiving,
                    jobRemote,
                    jobSalary,
                    jobBonus,
                    jobBenefits,
                    jobLeave
            );
        }
    }

    private Integer saveJobDetails(View view) {
        JobDetails job = this.validateJobDetails();
        if (job != null) {
            String sql;
            if (this.currentOrOffer.equals("current")) {
                if (this.currentJobExists) {
                    sql = "UPDATE `job_details` SET `title`='"+job.getTitle()+"', `company`='"+job.getCompany()+"', " +
                            "`city`='"+job.getCity()+"', `state`='"+job.getState()+"', `cost_of_living`="+job.getCostOfLiving()+", `remote`="+job.getRemote()+", `salary`="+job.getSalary()+", `bonus`="+job.getBonus()+", `benefits`="+job.getBenefits()+", `leave`="+job.getLeave() +
                            " WHERE `job_id`="+this.currentJobId+" and `current`=1;";
                    try {
                        db.execSQL(sql);
                        return this.currentJobId;
                    } catch (Exception e) { // SQL error
                        Log.e(TAG, e.toString());
                        return null;
                    }
                } else {
                    sql = "INSERT INTO `job_details` (`current`, `title`, `company`, `city`, `state`, `cost_of_living`, `remote`, `salary`, `bonus`, `benefits`, `leave`) " +
                            "VALUES (1, '"+job.getTitle()+"', '"+job.getCompany()+"', '"+job.getCity()+"', '"+job.getState()+"', "+job.getCostOfLiving()+", "+job.getRemote()+", "+job.getSalary()+", "+job.getBonus()+", "+job.getBenefits()+", "+job.getLeave()+");";
                    try {
                        db.execSQL(sql);
                        sql = "SELECT last_insert_rowid();";
                        Cursor resultSet = db.rawQuery(sql,null);
                        resultSet.moveToFirst();
                        return Integer.parseInt(resultSet.getString(0));
                    } catch (Exception e) { // SQL error
                        Log.e(TAG, e.toString());
                        return null;
                    }
                }
            } else {
                sql = "INSERT INTO `job_details` (`current`, `title`, `company`, `city`, `state`, `cost_of_living`, `remote`, `salary`, `bonus`, `benefits`, `leave`) " +
                        "VALUES (0, '"+job.getTitle()+"', '"+job.getCompany()+"', '"+job.getCity()+"', '"+job.getState()+"', "+job.getCostOfLiving()+", "+job.getRemote()+", "+job.getSalary()+", "+job.getBonus()+", "+job.getBenefits()+", "+job.getLeave()+");";
                try {
                    db.execSQL(sql);
                    sql = "SELECT last_insert_rowid();";
                    Cursor resultSet = db.rawQuery(sql,null);
                    resultSet.moveToFirst();
                    return Integer.parseInt(resultSet.getString(0));
                } catch (Exception e) { // SQL error
                    Log.e(TAG, e.toString());
                    return null;
                }
            }
        } // else if (job == null)
        return null;
    }

    private void cancel(View view) {
        Intent intent = new Intent(EnterJobDetails.this, MainActivity.class);
        startActivity(intent);
    }

    public void handleClick(View view) {
        Intent intent = null;
        Integer newOfferId;
        switch (view.getId()) {
            case R.id.saveButton:
                newOfferId = this.saveJobDetails(view);
                if (this.currentOrOffer.equals("current") && newOfferId != null) {
                    Toast.makeText(getApplicationContext(), "Current job updated", Toast.LENGTH_SHORT).show();
                    intent = new Intent(EnterJobDetails.this, MainActivity.class);
                    startActivity(intent);
                } else if (newOfferId != null) {
                    intent = new Intent(EnterJobDetails.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.cancelButton:
                this.cancel(view);
                break;
            case R.id.addOfferButton:
                newOfferId = this.saveJobDetails(view);
                if (newOfferId != null) {
                    intent = new Intent(EnterJobDetails.this, EnterJobDetails.class);
                    startActivity(intent);
                }
                break;
            case R.id.compareJobsButton:
                newOfferId = this.saveJobDetails(view);
                if (newOfferId != null) {
                    Bundle b = new Bundle();
                    b.putString("firstJob", String.valueOf(this.currentJobId));
                    b.putString("secondJob", String.valueOf(newOfferId));
                    intent = new Intent(EnterJobDetails.this, CompareJobs.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                break;
        }
    }

}
