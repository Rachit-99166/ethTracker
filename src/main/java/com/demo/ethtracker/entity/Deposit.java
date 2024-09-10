package com.demo.ethtracker.entity;
import java.math.BigInteger;
import java.time.Instant;

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
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger blockNumber;
    private Instant blockTimestamp; // or use Long if you prefer Unix timestamp
    private BigInteger fee;
    private String hash;
    private String pubkey;

    public Deposit(BigInteger blockNumber, Instant blockTimestamp, BigInteger fee, String hash, String pubKey){
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
        this.fee = fee;
        this.hash = hash;
        this.pubkey = pubKey;
    }

}