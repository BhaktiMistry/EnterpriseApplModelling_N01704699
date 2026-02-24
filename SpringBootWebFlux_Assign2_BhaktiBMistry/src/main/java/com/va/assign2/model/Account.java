package com.va.assign2.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {
    @Id
    private String id;
    private String accountHolder;
    private double balance;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}