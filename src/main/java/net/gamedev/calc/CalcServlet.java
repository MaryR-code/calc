package net.gamedev.calc;

import net.gamedev.calc.model.Player;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "CalcServlet", urlPatterns = "")
public class CalcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var rand = ThreadLocalRandom.current();
        var player = new Player();
        player.setNum1(rand.nextInt(100, 1000));
        player.setNum2(rand.nextInt(100, 1000));
        request.getSession().setAttribute(Player.ATTR, player);
        request.getRequestDispatcher("/WEB-INF/calc.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var playerSum = Integer.parseInt(request.getParameter("playerSum"));
        var player = (Player) request.getSession().getAttribute(Player.ATTR);
        var num1 = player.getNum1();
        var num2 = player.getNum2();
        var sum = num1 + num2;
        var msg = "";
        if (playerSum == sum) {
            msg = "Correct!";
        } else {
            msg = "Not correct!";
        }
        request.setAttribute("sum", sum);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/answer.jsp")
                .forward(request, response);
    }
}
