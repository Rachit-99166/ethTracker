package com.demo.ethtracker.util;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.ethtracker.entity.Deposit;
import com.demo.ethtracker.repository.DepositRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DepositParser {

    @Autowired
    private DepositRepository depositRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public String handleDepositParser(String eventData){
        try {
            JsonNode rootNode = objectMapper.readTree(eventData);
            JsonNode activityNode = rootNode.path("event").path("activity");

            for (JsonNode node : activityNode) {
                BigInteger blockNumber = new BigInteger(node.path("blockNum").asText().replace("0x", ""), 16);
                Instant blockTimestamp = Instant.now();
                BigInteger fee = new BigInteger(node.path("value").asText());
                String hash = node.path("hash").asText();
                String pubkey = node.path("fromAddress").asText(); // Assuming 'fromAddress' is used as 'pubkey'

                // Create and save Deposit entity
                Deposit deposit = new Deposit(blockNumber, blockTimestamp, fee, hash, pubkey);
                depositRepository.save(deposit);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }
}
