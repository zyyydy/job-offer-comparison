package edu.gatech.seclass.jobcompare6300;

import android.database.Cursor;

public class Comparison {

    private Integer remote;
    private Integer salary;
    private Integer bonus;
    private Integer benefit;
    private Integer leave;

    public Comparison(Integer remote, Integer salary, Integer bonus, Integer benefit, Integer leave) {
        this.setRemote(remote);
        this.setSalary(salary);
        this.setBonus(bonus);
        this.setBenefit(benefit);
        this.setLeave(leave);
    }

    public Comparison(Cursor resultSet){
        this.setRemote(Integer.parseInt(resultSet.getString(1)));
        this.setSalary(Integer.parseInt(resultSet.getString(2)));
        this.setBonus(Integer.parseInt(resultSet.getString(3)));
        this.setBenefit(Integer.parseInt(resultSet.getString(4)));
        this.setLeave(Integer.parseInt(resultSet.getString(5)));
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
    public void setBenefit(Integer benefit) {
        this.benefit = benefit;
    }
    public Integer getBenefit() {
        return this.benefit;
    }
    public void setLeave(Integer leave) {
        this.leave = leave;
    }
    public Integer getLeave() {
        return this.leave;
    }

    /*
     * AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
     * AYS = yearly salary adjusted for cost of living
     * AYB = yearly bonus adjusted for cost of living
     * RBP = retirement benefits percentage
     * LT = leave time
     * RWD = remote work days
    */
    public Double calculateRank(JobDetails job) {
        Double colMultiplier = (100.0/job.getCostOfLiving());
        Double Ays = job.getSalary() * colMultiplier;
        Double Ayb = job.getBonus() * colMultiplier;
        Double total = Double.valueOf(this.getSalary()) + this.getBonus() + this.getBenefit() + this.getLeave() + this.getRemote();
        Double rank = ((this.getSalary()/total)*(Ays) +
                (this.getBonus()/total)*(Ayb) +
                (this.getBenefit()/total)*(job.getBenefits() * Ays) +
                (this.getLeave()/total)*((job.getLeave() * Ays) / 260) -
                (this.getRemote()/total)*((260 - (52 * job.getRemote()) * ((Ays / 260)/8))));
        return rank;
    }

}
