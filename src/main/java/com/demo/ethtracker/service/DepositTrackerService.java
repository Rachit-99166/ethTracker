package com.demo.ethtracker.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import com.demo.ethtracker.util.DepositParser;

@Service
public class DepositTrackerService {

    private final Web3j web3j;

    @Autowired
    private DepositParser parser;

    public DepositTrackerService(Web3j web3j) {
        this.web3j = web3j;
    }

    // Method to fetch the latest block number
    public BigInteger getLatestBlockNumber() throws IOException {
        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
        return blockNumber.getBlockNumber();
    }

    // Method to fetch a block by its number
    public EthBlock.Block getBlockByNumber(BigInteger blockNumber) throws IOException {
        EthBlock block = web3j.ethGetBlockByNumber(
                org.web3j.protocol.core.DefaultBlockParameter.valueOf(blockNumber), 
                true
        ).send();
        return block.getBlock();
    }

    // Method to fetch a transaction receipt by transaction hash
    public Optional<TransactionReceipt> getTransactionReceipt(String transactionHash) throws IOException {
        return web3j.ethGetTransactionReceipt(transactionHash).send().getTransactionReceipt();
    }

    // Method to fetch deposit data from transactions in a specific block
    public void fetchDepositData(BigInteger blockNumber) throws IOException {
        EthBlock.Block block = getBlockByNumber(blockNumber);
        block.getTransactions().forEach(transactionResult -> {
            System.out.println(transactionResult);
        });
    }

    public String handleDepositParser(String eventData) throws IOException {
        return parser.handleDepositParser(eventData);
    }

}