package com.demo.ethtracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException; 
import java.math.BigInteger;

import com.demo.ethtracker.entity.Deposit; 
import com.demo.ethtracker.repository.DepositRepository;
import com.demo.ethtracker.service.DepositTrackerService;
import com.microsoft.applicationinsights.TelemetryClient;

import java.util.List;

@RestController
public class DepositController {

    private final TelemetryClient  telemetryClient = new TelemetryClient();
    private Logger logger = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    private DepositTrackerService ethereumService;

    @GetMapping("/latest-block")
    public BigInteger getLatestBlockNumber() throws IOException {
        return ethereumService.getLatestBlockNumber();
    }

    @GetMapping("/block/{blockNumber}")
    public Object getBlockByNumber(@PathVariable BigInteger blockNumber) throws IOException {
        return ethereumService.getBlockByNumber(blockNumber);
    }

    @GetMapping("/receipt/{hash}")
    public Object getTransactionReceipt(@PathVariable String hash) throws IOException {
        return ethereumService.getTransactionReceipt(hash);
    }

    @GetMapping("/deposits/{blockNumber}")
    public void fetchDepositData(@PathVariable BigInteger blockNumber) throws IOException { 
        ethereumService.fetchDepositData(blockNumber);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String eventData) {
        try {
            return ResponseEntity.ok(ethereumService.handleDepositParser(eventData));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok("Error");
        
    }
}
