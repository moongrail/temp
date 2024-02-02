package com.servlet.advice.service;

import com.servlet.advice.model.Advice;
import com.servlet.advice.repositories.AdviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdviceServiceImplTest {
    private AdviceServiceImpl adviceService;

    @Mock
    private AdviceRepository adviceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adviceService = new AdviceServiceImpl(adviceRepository);
    }

    @Test
    public void testAddAdvice() {
        Advice advice = new Advice(1L, "Совет");

        adviceService.addAdvice(advice);

        verify(adviceRepository, times(1)).save(advice);
    }

    @Test
    public void testGetRandomAdvice() {
        Advice advice = new Advice(1L, "Совет");

        when(adviceRepository.getRandomAdvice()).thenReturn(Optional.of(advice));

        Optional<Advice> result = adviceService.getRandomAdvice();

        assertEquals(advice, result.orElse(null));
    }
}