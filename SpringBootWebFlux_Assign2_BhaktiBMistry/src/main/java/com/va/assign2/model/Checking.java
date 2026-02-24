package com.va.assign2.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "checking")
public class Checking extends Account {
    private double insufficientFundFee;

    public double getInsufficientFundFee() {
        return insufficientFundFee;
    }

    public void setInsufficientFundFee(double insufficientFundFee) {
        this.insufficientFundFee = insufficientFundFee;
    }
}