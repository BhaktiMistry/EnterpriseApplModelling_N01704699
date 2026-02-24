package com.va.assign2.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.va.assign2.model.Checking;

@Repository
public interface CheckingRepository extends ReactiveMongoRepository<Checking, String> { }