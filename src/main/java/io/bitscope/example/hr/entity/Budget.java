package io.bitscope.example.hr.entity;

import java.math.BigDecimal;

public class Budget {

    private String department;

    private BigDecimal spending;

    public Budget() {}

    public Budget(String department, BigDecimal spending) {
        this.department = department;
        this.spending = spending;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSpending() {
        return spending;
    }

    public void setSpending(BigDecimal spending) {
        this.spending = spending;
    }
}
