package com.demo.ethtracker.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionHash;
    private String blockHash;
    private BigInteger blockNumber;
    private String fromAddress;
    private String toAddress;
    private BigInteger value;
    private BigInteger gas;
    private BigInteger gasPrice;
    private BigInteger nonce;
    private String inputData;
}