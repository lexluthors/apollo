package com.apecoder.apollo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Girl {
    private Integer id;

    /**
     * @Min 表单验证
     */
    @Min(value = 18,message = "禁止未成年少女入内")
    private Integer age;
    private String cupSize;
    private String des;
    private String name;
    private String income;

    @NotNull(message = "金额必传")
    private Double money;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Girl() {
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", age=" + age +
                ", cupSize='" + cupSize + '\'' +
                ", des='" + des + '\'' +
                ", name='" + name + '\'' +
                ", income='" + income + '\'' +
                ", money=" + money +
                '}';
    }
}
