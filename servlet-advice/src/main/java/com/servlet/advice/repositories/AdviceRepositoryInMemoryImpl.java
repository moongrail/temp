package com.servlet.advice.repositories;

import com.servlet.advice.model.Advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AdviceRepositoryInMemoryImpl implements AdviceRepository {
    private static final Map<Long, Advice> ADVICE_MAP = new HashMap<>();
    private static Long adviceId = 1L;
    private static boolean initialDataAdded = false;

    private static final AdviceRepositoryInMemoryImpl instance = new AdviceRepositoryInMemoryImpl();

    private AdviceRepositoryInMemoryImpl() {
        if (!initialDataAdded) {
            addInitialAdvice();
            initialDataAdded = true;
        }
    }

    public static AdviceRepositoryInMemoryImpl getInstance() {
        return instance;
    }

    @Override
    public void save(Advice advice) {
        if (!ADVICE_MAP.containsValue(advice)) {
            ADVICE_MAP.put(adviceId, advice);
            adviceId++;
        } else {
            System.err.println("Данный совет уже существует: " + advice.getDescription());
        }
    }

    @Override
    public Optional<Advice> getRandomAdvice() {
        List<Advice> adviceList = new ArrayList<>(ADVICE_MAP.values());
        if (!adviceList.isEmpty()) {
            int randomIndex = ThreadLocalRandom.current().nextInt(adviceList.size());
            return Optional.of(adviceList.get(randomIndex));
        }
        return Optional.empty();
    }

    @Override
    public List<Advice> getAll() {
        return new ArrayList<>(ADVICE_MAP.values());
    }

    private void addInitialAdvice() {
        Advice advice1 = Advice.builder()
                .description("Всё будет хорошо.")
                .build();
        Advice advice2 = Advice.builder()
                .description("Сервлеты это же весело, чего ты.")
                .build();
        Advice advice3 = Advice.builder()
                .description("Попробуй ещё раз.")
                .build();
        Advice advice4 = Advice.builder()
                .description("Попробуй ещё раз. Попей чай и ещё раз попробуй.")
                .build();
        Advice advice5 = Advice.builder()
                .description("Совет 5. https://www.youtube.com/watch?v=cMDbsL27g_E&ab_channel=%D0%9C%D1%83%D0%B4%D1%80%D0%B5%D1%86%D1%8B%D0%93%D0%BE%D0%B2%D0%BE%D1%80%D1%8F%D1%82")
                .build();

        save(advice1);
        save(advice2);
        save(advice3);
        save(advice4);
        save(advice5);
    }
}
