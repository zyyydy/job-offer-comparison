package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.DatabaseUtils;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupDatabase();
        this.enableCompareJobsButton(2);
    }

    private void setupDatabase() {
        this.db = openOrCreateDatabase("jobcompare", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS `job_details`(`job_id` INTEGER PRIMARY KEY AUTOINCREMENT, `current` INTEGER, `title` VARCHAR, `company` VARCHAR," +
                "`city` VARCHAR, `state` VARCHAR, `cost_of_living` INTEGER, `remote` INTEGER, `salary` INTEGER, `bonus` INTEGER, `benefits` INTEGER, `leave` INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS `comparisons`(`id` INTEGER UNIQUE," +
                "`remote` INTEGER, `salary` INTEGER, `bonus` INTEGER, `benefit` INTEGER, `leave` INTEGER);";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO `comparisons` VALUES (1, 1, 1, 1, 1, 1)"; // default
        db.execSQL(sql);
    }

    private void enableCompareJobsButton(int min) {
        long jobCount = DatabaseUtils.queryNumEntries(db, "job_details");
        if (jobCount >= min) {
            Button compareJobsButton = (Button)findViewById(R.id.compareJobs);
            compareJobsButton.setEnabled(true);
        }
    }

    private void editCurrentJob(View view) {
        Bundle b = new Bundle();
        b.putString("currentOrOffer", "current");
        Intent intent = new Intent(MainActivity.this, EnterJobDetails.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void enterJobOffer(View view) {
        Bundle b = new Bundle();
        b.putString("currentOrOffer", "offer");
        Intent intent = new Intent(MainActivity.this, EnterJobDetails.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void comparisonSettings(View view) {
        Intent intent = new Intent(MainActivity.this, EnterComparisons.class);
        startActivity(intent);
    }

    private void showRankings(View view) {
        Intent intent = new Intent(MainActivity.this, Rankings.class);
        startActivity(intent);
    }

    private void resetApplication() {
        Button compareJobsButton = (Button)findViewById(R.id.compareJobs);
        compareJobsButton.setEnabled(false);
        String sql = "DROP TABLE IF EXISTS `job_details`;";
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS `comparisons`;";
        db.execSQL(sql);
        sql = "CREATE TABLE `job_details`(`job_id` INTEGER PRIMARY KEY AUTOINCREMENT, `current` INTEGER, `title` VARCHAR, `company` VARCHAR," +
                "`city` VARCHAR, `state` VARCHAR, `cost_of_living` INTEGER, `remote` INTEGER, `salary` INTEGER, `bonus` INTEGER, `benefits` INTEGER, `leave` INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE `comparisons`(`id` INTEGER PRIMARY KEY," +
                "`remote` INTEGER, `salary` INTEGER, `bonus` INTEGER, `benefit` INTEGER, `leave` INTEGER);";
        db.execSQL(sql);
        sql = "INSERT INTO `comparisons` VALUES (1, 1, 1, 1, 1, 1)"; // default
        db.execSQL(sql);
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.editCurrentJob:
                this.editCurrentJob(view);
                break;
            case R.id.enterJobOffer:
                this.enterJobOffer(view);
                break;
            case R.id.comparisonSettings:
                this.comparisonSettings(view);
                break;
            case R.id.compareJobs:
                this.showRankings(view);
                break;
            case R.id.resetApplication:
                this.resetApplication();
                break;
            default:
                Log.e(TAG, "Invalid view id");
        }
    }
}
