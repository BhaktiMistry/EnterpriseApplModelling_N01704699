package com.va.assign2.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.va.assign2.model.Savings;

@Repository
public interface SavingsRepository extends ReactiveMongoRepository<Savings, String> { }