package edu.gatech.seclass.jobcompare6300;

import android.database.Cursor;
import android.view.View;

public class JobDetails {

    private Integer jobId;
    private Integer current;
    private String title;
    private String company;
    private String city;
    private String state;
    private Integer costOfLiving;
    private Integer remote;
    private Integer salary;
    private Integer bonus;
    private Integer benefits;
    private Integer leave;

    public JobDetails(String title,
                        String company,
                        String city,
                        String state,
                        Integer costOfLiving,
                        Integer remote,
                        Integer salary,
                        Integer bonus,
                        Integer benefits,
                        Integer leave){
        // TODO filter user input
        title.replace("","");
        company.replace("","");
        city.replace("","");
        state.replace("","");
        this.setTitle(title);
        this.setCompany(company);
        this.setCity(city);
        this.setState(state);
        this.setCostOfLiving(costOfLiving);
        this.setRemote(remote);
        this.setSalary(salary);
        this.setBonus(bonus);
        this.setBenefits(benefits);
        this.setLeave(leave);
    }

    public JobDetails(Cursor resultSet){
        this.setTitle(resultSet.getString(2));
        this.setCompany(resultSet.getString(3));
        this.setCity(resultSet.getString(4));
        this.setState(resultSet.getString(5));
        this.setCostOfLiving(Integer.parseInt(resultSet.getString(6)));
        this.setRemote(Integer.parseInt(resultSet.getString(7)));
        this.setSalary(Integer.parseInt(resultSet.getString(8)));
        this.setBonus(Integer.parseInt(resultSet.getString(9)));
        this.setBenefits(Integer.parseInt(resultSet.getString(10)));
        this.setLeave(Integer.parseInt(resultSet.getString(11)));
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
    public Integer getJobId() {
        return this.jobId;
    }
    public void setCurrent(Integer current) {
        this.current = current;
    }
    public Integer getCurrent() {
        return this.current;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany() {
        return this.company;
    }
    public void setCity(String city) { this.city = city; }
    public String getCity() {
        return this.city;
    }
    public void setState(String state) { this.state = state; }
    public String getState() {
        return this.state;
    }
    public void setCostOfLiving(Integer costOfLiving) {
        this.costOfLiving = costOfLiving;
    }
    public Integer getCostOfLiving() {
        return this.costOfLiving;
    }
    public void setRemote(Integer remote) {
        this.remote = remote;
    }
    public Integer getRemote() {
        return this.remote;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public Integer getSalary() {
        return this.salary;
    }
    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
    public Integer getBonus() {
        return this.bonus;
    }
    public void setBenefits(Integer benefits) {
        this.benefits = benefits;
    }
    public Integer getBenefits() {
        return this.benefits;
    }
    public void setLeave(Integer leave) {
        this.leave = leave;
    }
    public Integer getLeave() {
        return this.leave;
    }

}
