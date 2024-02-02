package com.servlet.advice.service;

import com.servlet.advice.model.Advice;
import com.servlet.advice.repositories.AdviceRepository;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class AdviceServiceImpl implements AdviceService {
    private final AdviceRepository adviceRepository;

    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    public void addAdvice(Advice advice) {
        if (nonNull(advice.getDescription())) {
            adviceRepository.save(advice);
        }
    }

    @Override
    public Optional<Advice> getRandomAdvice() {
        return adviceRepository.getRandomAdvice();
    }
}
