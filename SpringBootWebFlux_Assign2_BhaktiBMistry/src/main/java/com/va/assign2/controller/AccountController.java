package com.va.assign2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.va.assign2.model.Checking;
import com.va.assign2.model.Savings;
import com.va.assign2.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // 1. Create Checking Account
    @PostMapping("/checking")
    public Mono<Checking> createChecking(@RequestBody Checking checking) {
        return accountService.saveChecking(checking);
    }

    // 2. Get All Checking Accounts
    @GetMapping("/checking")
    public Flux<Checking> getAllChecking() {
        return accountService.findAllChecking();
    }

    // 3. Create Savings Account
    @PostMapping("/savings")
    public Mono<Savings> createSavings(@RequestBody Savings savings) {
        return accountService.saveSavings(savings);
    }

    // 4. Get All Savings Accounts
    @GetMapping("/savings")
    public Flux<Savings> getAllSavings() {
        return accountService.findAllSavings();
    }
}
