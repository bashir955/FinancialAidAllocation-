package com.example.financialaidallocation.Classes;

public class BudgetModel {
    private int budgetId;
    private double budgetAmount ;
    private double remainingAmount;

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getBudget_session() {
        return budget_session;
    }

    public void setBudget_session(String budget_session) {
        this.budget_session = budget_session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String budget_session;
    private String  status;
}
