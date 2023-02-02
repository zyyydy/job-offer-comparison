package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rankings extends AppCompatActivity {

    private SQLiteDatabase db;
    private Integer firstJob;
    private Integer secondJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rankings_container);
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_rankings_container);
        this.db = openOrCreateDatabase("jobcompare", MODE_PRIVATE, null);
        Comparison comp = this.getCurrentComparison();
        ArrayList<JobDetails> jobList = this.getAllJobs(comp);
        this.createColumnHeaders(mainLayout);
        this.displayAllJobs(jobList, comp, mainLayout);
    }

    private Comparison getCurrentComparison() {
        String sql = "SELECT * from `comparisons` LIMIT 1;";
        Cursor comparisonSet = db.rawQuery(sql,null);
        comparisonSet.moveToFirst();
        return new Comparison(comparisonSet);
    }

    private ArrayList<JobDetails> getAllJobs(Comparison comp) {
        /* We assume that no normal person would have more than 100 job offers at a time */
        String sql = "SELECT * from `job_details` LIMIT 100;";
        Cursor resultSet = db.rawQuery(sql,null);
        ArrayList<JobDetails> jobList = new ArrayList<JobDetails>();

        try {
            while (resultSet.moveToNext()) {
                Integer jobId = Integer.parseInt(resultSet.getString(0));
                Integer currentJob = Integer.parseInt(resultSet.getString(1));
                JobDetails job = new JobDetails(resultSet);
                job.setJobId(jobId);
                job.setCurrent(currentJob);
                Integer index = jobList.size();
                for(int i = 0; i < jobList.size(); i++) {
                    Double currentRank = comp.calculateRank(job);
                    if (comp.calculateRank(jobList.get(i)) > currentRank) {
                        index = i;
                        break;
                    }
                }
                jobList.add(index, job);
            }
        } finally {
            resultSet.close();
        }

        return jobList;
    }

    private void createColumnHeaders(LinearLayout mainLayout) {
        View rankingButtons = getLayoutInflater().inflate(R.layout.ranking_buttons, mainLayout,false);
        mainLayout.addView(rankingButtons);
        View view = getLayoutInflater().inflate(R.layout.job_view, mainLayout,false);
        mainLayout.addView(view);
        TextView title1 = (TextView)view.findViewById(R.id.jobTitleRank);
        title1.setText("Job Title");
        TextView title2 = (TextView)view.findViewById(R.id.companyRank);
        title2.setText("Company");
        TextView title3 = (TextView)view.findViewById(R.id.rankValue);
        title3.setText("Rank");
        TextView title4 = (TextView)view.findViewById(R.id.compareRankSelector);
        title4.setText("id");
        title4.setEnabled(false);
    }

    private void displayAllJobs(ArrayList<JobDetails> jobList, Comparison comp, LinearLayout mainLayout) {
        View view;
        for(int i = jobList.size()-1; i >= 0; i--) {
            view = getLayoutInflater().inflate(R.layout.job_view, mainLayout,false);
            mainLayout.addView(view);
            JobDetails job = jobList.get(i);
            if (job.getCurrent() == 1) {
                view.setBackgroundColor(0x5500FFFF);
            }
            Double rank = comp.calculateRank(job);
            TextView title = (TextView)view.findViewById(R.id.jobTitleRank);
            title.setText(job.getTitle());
            TextView company = (TextView)view.findViewById(R.id.companyRank);
            company.setText(job.getCompany());
            TextView value = (TextView)view.findViewById(R.id.rankValue);
            value.setText(String.format("%.1f", rank));
            TextView selector = (TextView)view.findViewById(R.id.compareRankSelector);
            selector.setText(job.getJobId().toString());
        }
    }

    private void compareJobs(View view) {
        Bundle b = new Bundle();
        b.putString("firstJob", String.valueOf(this.firstJob));
        b.putString("secondJob", String.valueOf(this.secondJob));
        Intent intent = new Intent(Rankings.this, CompareJobs.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void returnToMenu(View view) {
        Intent intent = new Intent(Rankings.this, MainActivity.class);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        switch(view.getId()) {
            case R.id.compareRankSelector:
                Integer jobId = Integer.parseInt(((CheckBox)view).getText().toString());
                if (checked) { // selected
                    if (this.firstJob == null) {
                        this.firstJob = jobId;
                    } else if (this.secondJob == null) {
                        this.secondJob = jobId;
                    } else {
                        ((CheckBox)view).setChecked(false);
                        Toast.makeText(getApplicationContext(), "You can only select two jobs", Toast.LENGTH_SHORT).show();
                    }
                } else { // unselected
                    if (this.firstJob == jobId) {
                        this.firstJob = null;
                    }
                    if (this.secondJob == jobId) {
                        this.secondJob = null;
                    }
                }
        }
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.compareRankButton:
                if (this.firstJob != null && this.secondJob != null) {
                    this.compareJobs(view);
                } else {
                    Toast.makeText(getApplicationContext(), "You must select two jobs to compare them", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnToMenu:
                this.returnToMenu(view);
                break;
        }
    }

}
