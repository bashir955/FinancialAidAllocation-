package com.example.financialaidallocation;

public class BudgetItemModel{
    private String name;
private String amount;

public BudgetItemModel(String name, String amount) {
    this.name = name;
    this.amount = amount;
}

public String getName() {
    return name;
}

public String getAmount() {
    return amount;
}
}