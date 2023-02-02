package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompareJobs extends AppCompatActivity {

    private SQLiteDatabase db;
    private static final String TAG = "CompareJobs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);
        Cursor resultSet = this.getJobsToCompare();
        displayJobs(resultSet);
    }

    private Cursor getJobsToCompare() {
        Bundle b = getIntent().getExtras();
        String firstJobIdStr = "1";
        String secondJobIdStr = "2";
        if(b != null) {
            firstJobIdStr = b.getString("firstJob");
            secondJobIdStr = b.getString("secondJob");
        } else {
            Log.e(TAG, "Missing firstJobIdStr and secondJobIdStr");
        }
        this.db = openOrCreateDatabase("jobcompare", MODE_PRIVATE, null);
        String sql = "SELECT * from `job_details` WHERE `job_id` IN ("+firstJobIdStr+", "+secondJobIdStr+") LIMIT 2;";
        return db.rawQuery(sql,null);
    }

    private void displayJobs(Cursor resultSet) {
        try {
            resultSet.moveToFirst();
            Integer firstJobId = Integer.parseInt(resultSet.getString(0));
            JobDetails firstJob = new JobDetails(resultSet);
            TextView firstTitle = (TextView)findViewById(R.id.firstJobTitle);
            firstTitle.setText(firstJob.getTitle());
            TextView firstCompany = (TextView)findViewById(R.id.firstJobCompany);
            firstCompany.setText(firstJob.getCompany());
            TextView firstLocation = (TextView)findViewById(R.id.firstJobLocation);
            firstLocation.setText(firstJob.getCity() + ", " + firstJob.getState());
            TextView firstRemote = (TextView)findViewById(R.id.firstJobRemote);
            firstRemote.setText(firstJob.getRemote().toString());
            TextView firstSalary = (TextView)findViewById(R.id.firstJobSalary);
            firstSalary.setText(firstJob.getSalary().toString());
            TextView firstBonus = (TextView)findViewById(R.id.firstJobBonus);
            firstBonus.setText(firstJob.getBonus().toString());
            TextView firstBenefits = (TextView)findViewById(R.id.firstJobBenefits);
            firstBenefits.setText(firstJob.getBenefits().toString());
            TextView firstLeave = (TextView)findViewById(R.id.firstJobLeave);
            firstLeave.setText(firstJob.getLeave().toString());

            resultSet.moveToNext();
            Integer secondJobId = Integer.parseInt(resultSet.getString(0));
            JobDetails secondJob = new JobDetails(resultSet);
            TextView secondTitle = (TextView)findViewById(R.id.secondJobTitle);
            secondTitle.setText(secondJob.getTitle());
            TextView secondCompany = (TextView)findViewById(R.id.secondJobCompany);
            secondCompany.setText(secondJob.getCompany());
            TextView secondLocation = (TextView)findViewById(R.id.secondJobLocation);
            secondLocation.setText(secondJob.getCity() + ", " + secondJob.getState());
            TextView secondRemote = (TextView)findViewById(R.id.secondJobRemote);
            secondRemote.setText(secondJob.getRemote().toString());
            TextView secondSalary = (TextView)findViewById(R.id.secondJobSalary);
            secondSalary.setText(secondJob.getSalary().toString());
            TextView secondBonus = (TextView)findViewById(R.id.secondJobBonus);
            secondBonus.setText(secondJob.getBonus().toString());
            TextView secondBenefits = (TextView)findViewById(R.id.secondJobBenefits);
            secondBenefits.setText(secondJob.getBenefits().toString());
            TextView secondLeave = (TextView)findViewById(R.id.secondJobLeave);
            secondLeave.setText(secondJob.getLeave().toString());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void returnToList(View view) {
        Intent intent = new Intent(CompareJobs.this, Rankings.class);
        startActivity(intent);
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.returnToList:
                this.returnToList(view);
                break;
        }
    }
}
