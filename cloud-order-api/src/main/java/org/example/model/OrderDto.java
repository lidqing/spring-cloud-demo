package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {

    private Long id;

    private String userId;

    private String commodityCode;

    private BigDecimal money;

}
