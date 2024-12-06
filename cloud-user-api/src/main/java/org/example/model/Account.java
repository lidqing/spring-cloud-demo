package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private String userId;

    private BigDecimal balance;

}
