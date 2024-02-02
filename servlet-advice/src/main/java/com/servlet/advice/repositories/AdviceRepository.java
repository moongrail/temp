package com.servlet.advice.repositories;

import com.servlet.advice.model.Advice;

import java.util.List;
import java.util.Optional;

public interface AdviceRepository {
    void save(Advice advice);

    List<Advice> getAll();

    Optional<Advice> getRandomAdvice();
}
