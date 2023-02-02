package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.instanceOf;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 */


@RunWith(AndroidJUnit4.class)
public class SmallTestExamples {

    @Rule
    public ActivityTestRule<MainActivity> tActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    //    Validate the reset functionality for enter current job
    public void reset_EditCurrentJob_Test1() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.current_job_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText("")));
        onView(withId(R.id.enterCompany)).check(matches(withText("")));
        onView(withId(R.id.enterCity)).check(matches(withText("")));
        onView(withId(R.id.enterState)).check(matches(withText("")));
        onView(withId(R.id.enterCol)).check(matches(withText("")));
        onView(withId(R.id.enterSalary)).check(matches(withText("")));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText("0")));
        onView(withId(R.id.enterBonus)).check(matches(withText("")));
        onView(withId(R.id.enterBenefits)).check(matches(withText("")));
        onView(withId(R.id.enterLeavetime)).check(matches(withText("")));
    }

    @Test
    //    Validate the reset functionality for enter job offer
    public void reset_EnterJobOffer_Test2() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.job_offer_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText("")));
        onView(withId(R.id.enterCompany)).check(matches(withText("")));
        onView(withId(R.id.enterCity)).check(matches(withText("")));
        onView(withId(R.id.enterState)).check(matches(withText("")));
        onView(withId(R.id.enterCol)).check(matches(withText("")));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText("0")));
        onView(withId(R.id.enterSalary)).check(matches(withText("")));
        onView(withId(R.id.enterBonus)).check(matches(withText("")));
        onView(withId(R.id.enterBenefits)).check(matches(withText("")));
        onView(withId(R.id.enterLeavetime)).check(matches(withText("")));
    }

    @Test
    //    Validate the reset functionality for comparison setting
    public void reset_comparisonSettings_Test3() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.comparisonSettings)).perform(click());
        onView(withId(R.id.enterRemoteWeight)).check(matches(withSpinnerText("1")));
        onView(withId(R.id.enterSalaryWeight)).check(matches(withSpinnerText("1")));
        onView(withId(R.id.enterBonusWeight)).check(matches(withSpinnerText("1")));
        onView(withId(R.id.enterBenefitWeight)).check(matches(withSpinnerText("1")));
        onView(withId(R.id.enterLeaveWeight)).check(matches(withSpinnerText("1")));
    }

    @Test
    //    Validate user can enter current job and the info entered got saved
    public void enterCurrentJob_Test4() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        String jobTitle = "CurrentJob1", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 100, remoteDays = 1, salary = 75000, bonus = 10000, benefits = 5, leaveTime = 15;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.cancelButton)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // acceptable error
        }
        onView(withId(R.id.editCurrentJob)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.current_job_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText(jobTitle)));
        onView(withId(R.id.enterCompany)).check(matches(withText(jobCompany)));
        onView(withId(R.id.enterCity)).check(matches(withText(jobCity)));
        onView(withId(R.id.enterState)).check(matches(withText(jobState)));
        onView(withId(R.id.enterCol)).check(matches(withText(costOfliving.toString())));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText(remoteDays.toString())));
        onView(withId(R.id.enterSalary)).check(matches(withText(salary.toString())));
        onView(withId(R.id.enterBonus)).check(matches(withText(bonus.toString())));
        onView(withId(R.id.enterBenefits)).check(matches(withText(benefits.toString())));
        onView(withId(R.id.enterLeavetime)).check(matches(withText(leaveTime.toString())));
    }

    @Test
    //    Validate user can enter comparison setting and the info entered got saved
    public void comparisonSetting_Test5() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.comparisonSettings)).perform(click());
        Integer remoteWeight = 3, salaryWeight = 2, bonusWeight = 1, benefitWeight = 1, leaveWeight = 2;
        onView(withId(R.id.enterRemoteWeight)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteWeight.toString()))).perform(click());
        onView(withId(R.id.enterSalaryWeight)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(salaryWeight.toString()))).perform(click());
        onView(withId(R.id.enterBonusWeight)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(bonusWeight.toString()))).perform(click());
        onView(withId(R.id.enterBenefitWeight)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(benefitWeight.toString()))).perform(click());
        onView(withId(R.id.enterLeaveWeight)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(leaveWeight.toString()))).perform(click());
        onView(withId(R.id.updateWeights)).perform(click());
        onView(withId(R.id.cancelWeights)).perform(click());
        onView(withId(R.id.comparisonSettings)).perform(click());
        onView(withId(R.id.enterRemoteWeight)).check(matches(withSpinnerText(remoteWeight.toString())));
        onView(withId(R.id.enterSalaryWeight)).check(matches(withSpinnerText(salaryWeight.toString())));
        onView(withId(R.id.enterBonusWeight)).check(matches(withSpinnerText(bonusWeight.toString())));
        onView(withId(R.id.enterBenefitWeight)).check(matches(withSpinnerText(benefitWeight.toString())));
        onView(withId(R.id.enterLeaveWeight)).check(matches(withSpinnerText(leaveWeight.toString())));
    }

    @Test
    //    Validate current job info not saved if user clicks Cancel after entering the current job details
    public void currentJobCancel_Test6() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        String jobTitle = "CurrentJob2", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 100, remoteDays = 2, salary = 95000, bonus = 33333, benefits = 6, leaveTime = 20;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelButton)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.current_job_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText("")));
        onView(withId(R.id.enterCompany)).check(matches(withText("")));
        onView(withId(R.id.enterCity)).check(matches(withText("")));
        onView(withId(R.id.enterState)).check(matches(withText("")));
        onView(withId(R.id.enterCol)).check(matches(withText("")));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText("0")));
        onView(withId(R.id.enterSalary)).check(matches(withText("")));
        onView(withId(R.id.enterBonus)).check(matches(withText("")));
        onView(withId(R.id.enterBenefits)).check(matches(withText("")));
        onView(withId(R.id.enterLeavetime)).check(matches(withText("")));
    }

    @Test
    //    Validate job offer info not saved if user clicks Cancel after entering the current job details
    public void jobOfferCancel_Test7() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        String jobTitle = "JobOffer1", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 100, remoteDays = 2, salary = 95000, bonus = 33333, benefits = 6, leaveTime = 20;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cancelButton)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.job_offer_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText("")));
        onView(withId(R.id.enterCompany)).check(matches(withText("")));
        onView(withId(R.id.enterCity)).check(matches(withText("")));
        onView(withId(R.id.enterState)).check(matches(withText("")));
        onView(withId(R.id.enterCol)).check(matches(withText("")));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText("0")));
        onView(withId(R.id.enterSalary)).check(matches(withText("")));
        onView(withId(R.id.enterBonus)).check(matches(withText("")));
        onView(withId(R.id.enterBenefits)).check(matches(withText("")));
        onView(withId(R.id.enterLeavetime)).check(matches(withText("")));
    }

    @Test
    //    Validate user can enter multiple job offers
    public void multipleJobOffers_Test8() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        String jobTitle1 = "JobOffer1", jobCompany1 = "GT", jobCity1 = "Atlanta", jobState1 = "GA", jobTitle2 = "JobOffer2", jobCompany2 = "CA", jobCity2 = "SFO", jobState2 = "CA";
        Integer costOfliving1 = 100, remoteDays1 = 2, salary1 = 95000, bonus1 = 33333, benefits1 = 6, leaveTime1 = 20,costOfliving2 = 299, remoteDays2 = 3, salary2 = 205000, bonus2 = 40000, benefits2 = 8, leaveTime2 = 25;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle1));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany1));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity1));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState1));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving1.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays1.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary1.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus1.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits1.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime1.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle2));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany2));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity2));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState2));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving2.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays2.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary2.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus2.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits2.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime2.toString()));
        onView(withId(R.id.saveButton)).perform(click());
        /* Validate no errors are thrown in this process */
    }

    @Test
    //    Validate compare button is not enabled when no current job
    public void jobOfferButtons_Test9() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.addOfferButton)).check(matches(isDisplayed()));
        onView(withId(R.id.compareJobsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addOfferButton)).check(matches(isEnabled()));
        onView(withId(R.id.compareJobsButton)).check(matches(not(isEnabled())));
    }

    @Test
    //    Validate compare button is enabled in job offer when current job is entered
    public void jobOfferButtons_Test10() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        String jobTitle = "CurrentJob1", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 100, remoteDays = 0, salary = 75000, bonus = 10000, benefits = 5, leaveTime = 15;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.cancelButton)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // acceptable error
        }
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.addOfferButton)).check(matches(isDisplayed()));
        onView(withId(R.id.compareJobsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addOfferButton)).check(matches(isEnabled()));
        onView(withId(R.id.compareJobsButton)).check(matches(isEnabled()));
    }

    @Test
    //    Validate extra buttons are not displayed on current job view
    public void currentJobButtons_Test11() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        onView(withId(R.id.addOfferButton)).check(matches(not(isDisplayed())));
        onView(withId(R.id.compareJobsButton)).check(matches(not(isDisplayed())));
    }

    @Test
    //    Validate that invalid input is not saved
    public void invalidInputCheck_Test12() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        String jobTitle = "CurrentJob1", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 0, salary = 0, bonus = -1, benefits = 101, leaveTime = 366;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.cancelButton)).perform(click());
        } catch (Exception e) {
            /* If save succeeds (unexpected), this test will still fail the matches below */
            System.out.println(e);
        }
        onView(withId(R.id.editCurrentJob)).perform(click());
        onView(withId(R.id.enterJobDetailsTitle)).check(matches(withText(R.string.current_job_app_title)));
        onView(withId(R.id.enterJobTitle)).check(matches(withText("")));
        onView(withId(R.id.enterCompany)).check(matches(withText("")));
        onView(withId(R.id.enterCity)).check(matches(withText("")));
        onView(withId(R.id.enterState)).check(matches(withText("")));
        onView(withId(R.id.enterCol)).check(matches(withText("")));
        onView(withId(R.id.enterSalary)).check(matches(withText("")));
        onView(withId(R.id.enterRemote)).check(matches(withSpinnerText("0")));
        onView(withId(R.id.enterBonus)).check(matches(withText("")));
        onView(withId(R.id.enterBenefits)).check(matches(withText("")));
        onView(withId(R.id.enterLeavetime)).check(matches(withText("")));
    }

    @Test
    //    Validate user can enter job offer and current job to compare them
    public void compareJobs_Test13() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.editCurrentJob)).perform(click());
        String jobTitle1 = "JobOffer1", jobCompany1 = "GT", jobCity1 = "Atlanta", jobState1 = "GA", jobTitle2 = "JobOffer2", jobCompany2 = "CA", jobCity2 = "SFO", jobState2 = "CA";
        Integer costOfliving1 = 100, remoteDays1 = 2, salary1 = 95000, bonus1 = 33333, benefits1 = 6, leaveTime1 = 20, costOfliving2 = 299, remoteDays2 = 3, salary2 = 205000, bonus2 = 40000, benefits2 = 8, leaveTime2 = 25;
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle1));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany1));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity1));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState1));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving1.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays1.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary1.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus1.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits1.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime1.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.cancelButton)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // acceptable error
        }
        onView(withId(R.id.enterJobOffer)).perform(click());
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle2));
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany2));
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity2));
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState2));
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving2.toString()));
        onView(withId(R.id.enterRemote)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(remoteDays2.toString()))).perform(click());
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary2.toString()));
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus2.toString()));
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits2.toString()));
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime2.toString()));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.compareJobs)).check(matches(isEnabled()));
        onView(withId(R.id.compareJobs)).perform(click());
    }

    @Test
    //    Validate that inputs are required
    public void invalidInputCheck_Test14() {
        onView(withId(R.id.resetApplication)).perform(click());
        onView(withId(R.id.enterJobOffer)).perform(click());
        String jobTitle = "CurrentJob1", jobCompany = "GT", jobCity = "Atlanta", jobState = "GA";
        Integer costOfliving = 60, salary = 15000, bonus = 1000, benefits = 3, leaveTime = 15;
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterJobTitle)).perform(clearText(), replaceText(jobTitle));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterCompany)).perform(clearText(), replaceText(jobCompany));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterCity)).perform(clearText(), replaceText(jobCity));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterState)).perform(clearText(), replaceText(jobState));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterCol)).perform(clearText(), replaceText(costOfliving.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterSalary)).perform(clearText(), replaceText(salary.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterBonus)).perform(clearText(), replaceText(bonus.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterBenefits)).perform(clearText(), replaceText(benefits.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        try {
            onView(withId(R.id.enterJobOffer)).perform(click());
        } catch (Exception e) {
            System.out.println(e); // expected error
        }
        onView(withId(R.id.enterLeavetime)).perform(clearText(), replaceText(leaveTime.toString()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click()); // Should save successfully
        onView(withId(R.id.enterJobOffer)).perform(click());
        /* Validate no errors are thrown in this process */
    }

}
