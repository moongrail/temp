package com.servlet.advice.service;

import com.servlet.advice.model.Advice;

import java.util.Optional;

public interface AdviceService {
    void addAdvice(Advice advice);

    Optional<Advice> getRandomAdvice();
}
