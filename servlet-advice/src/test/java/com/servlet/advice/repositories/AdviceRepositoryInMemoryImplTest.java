package com.servlet.advice.repositories;

import com.servlet.advice.model.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdviceRepositoryInMemoryImplTest {
    private AdviceRepositoryInMemoryImpl adviceRepository;

    @BeforeEach
    public void setUp() {
        adviceRepository = AdviceRepositoryInMemoryImpl.getInstance();
    }

    /**
     * Я понимаю что такой тест не надёжный(если что-то изменится). Но такая уж простая реализация была.
     */
    @Test
    public void testSave_whenSave_thenSizeMap6() {
        Advice advice = new Advice(6L, "Совет");

        adviceRepository.save(advice);

        assertEquals(6, adviceRepository.getAll().size());
    }

    @Test
    public void testGetRandomAdvice_whenInvoke_thenHaveAdvice() {
        Optional<Advice> randomAdvice = adviceRepository.getRandomAdvice();

        assertTrue(randomAdvice.isPresent());
    }
}