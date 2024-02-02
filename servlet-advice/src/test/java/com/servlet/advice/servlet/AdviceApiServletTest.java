package com.servlet.advice.servlet;

import com.servlet.advice.model.Advice;
import com.servlet.advice.repositories.AdviceRepository;
import com.servlet.advice.service.AdviceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdviceApiServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AdviceService adviceService;
    @Mock
    private AdviceRepository adviceRepository;
    @InjectMocks
    private AdviceApiServlet adviceApiServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SneakyThrows
    public void testDoGet_whenInvokeRandom_thenStatusOk() {
        Advice advice = new Advice(1L, "Test advice");
        when(adviceRepository.getRandomAdvice()).thenReturn(Optional.of(advice));
        when(adviceService.getRandomAdvice()).thenReturn(Optional.of(advice));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        adviceApiServlet.doGet(request, response);

        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setContentType("text/plain; charset=UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertEquals("Test advice", stringWriter.toString().trim());
    }

    @Test
    @SneakyThrows
    public void testDoPost_whenValidDescription_thenStatusCreated() {
        when(request.getParameter("description")).thenReturn("New advice");

        adviceApiServlet.doPost(request, response);

        verify(adviceService).addAdvice(any(Advice.class));
        verify(response).setStatus(HttpServletResponse.SC_CREATED);
        verify(response).sendRedirect("/advice");
    }

    @Test
    @SneakyThrows
    public void testSave_whenInvalidDescription_thenBadRequest() {
        when(request.getParameter("description")).thenReturn("");

        adviceApiServlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат добавления совета.");
    }
}