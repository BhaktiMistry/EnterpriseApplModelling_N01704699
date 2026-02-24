package com.va.assign2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.va.assign2.model.Checking;
import com.va.assign2.model.Savings;
import com.va.assign2.repository.CheckingRepository;
import com.va.assign2.repository.SavingsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private CheckingRepository checkingRepo;

    @Autowired
    private SavingsRepository savingsRepo;

    // --- Checking Account Operations ---
    
    public Mono<Checking> saveChecking(Checking account) {
        return checkingRepo.save(account);
    }

    public Flux<Checking> findAllChecking() {
        return checkingRepo.findAll();
    }

    // --- Savings Account Operations ---

    public Mono<Savings> saveSavings(Savings account) {
        return savingsRepo.save(account);
    }

    public Flux<Savings> findAllSavings() {
        return savingsRepo.findAll();
    }
}