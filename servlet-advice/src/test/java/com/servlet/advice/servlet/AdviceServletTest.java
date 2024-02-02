package com.servlet.advice.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdviceServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private AdviceServlet adviceServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet_whenInvoke_thenReturnPage() throws ServletException, IOException {
        when(request.getRequestDispatcher("jsp/advice.jsp")).thenReturn(requestDispatcher);

        adviceServlet.doGet(request, response);

        verify(request).getRequestDispatcher("jsp/advice.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}