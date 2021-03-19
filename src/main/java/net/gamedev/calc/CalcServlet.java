package net.gamedev.calc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "CalcServlet", urlPatterns = "")
public class CalcServlet extends HttpServlet {
    private int number1;
    private int number2;
    private int sum;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var rand = ThreadLocalRandom.current();
        number1 = rand.nextInt(100, 1000);
        number2 = rand.nextInt(100, 1000);
        sum = number1 + number2;
        request.setAttribute("num1", number1);
        request.setAttribute("num2", number2);
        request.getRequestDispatcher("/WEB-INF/calc.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var playerSum = Integer.parseInt(request.getParameter("playerSum"));
        var msg = "";
        if (playerSum == sum) {
            msg = "Correct!";
        } else {
            msg = "Not correct!";
        }
        openAnswer(request, response, msg);

    }

    private void openAnswer(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute("num1", number1);
        request.setAttribute("num2", number2);
        request.setAttribute("sum", sum);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/answer.jsp")
                .forward(request, response);
    }
}
