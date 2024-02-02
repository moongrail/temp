package com.servlet.advice.servlet;

import com.servlet.advice.model.Advice;
import com.servlet.advice.repositories.AdviceRepository;
import com.servlet.advice.repositories.AdviceRepositoryInMemoryImpl;
import com.servlet.advice.service.AdviceService;
import com.servlet.advice.service.AdviceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/help-service/v1/support")
public class AdviceApiServlet extends HttpServlet {
    private AdviceService adviceService;

    @Override
    public void init() throws ServletException {
        super.init();
        AdviceRepository adviceRepository = AdviceRepositoryInMemoryImpl.getInstance();
        this.adviceService = new AdviceServiceImpl(adviceRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Advice> randomAdvice = adviceService.getRandomAdvice();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=UTF-8");

        String adviceDescription = randomAdvice.map(Advice::getDescription).orElse("");
        resp.getWriter().write(adviceDescription);

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");

        if (description != null && !description.isEmpty()) {
            Advice newAdvice = Advice.builder()
                    .description(description)
                    .build();
            adviceService.addAdvice(newAdvice);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.sendRedirect("/advice");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат добавления совета.");
        }
    }
}
