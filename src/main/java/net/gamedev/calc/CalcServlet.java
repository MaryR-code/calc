package net.gamedev.calc;

import net.gamedev.calc.model.Player;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "CalcServlet", urlPatterns = "")
public class CalcServlet extends HttpServlet {
    private Player player = new Player();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var rand = ThreadLocalRandom.current();
        player.setNum1(rand.nextInt(1, 10));
        player.setNum2(rand.nextInt(1, 10));
        request.setAttribute(Player.ATTR, player);
        request.setAttribute("num1", player.getNum1());
        request.setAttribute("num2", player.getNum2());
        request.getRequestDispatcher("/WEB-INF/calc.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var playerSum = Integer.parseInt(request.getParameter("playerSum"));
        request.getAttribute(Player.ATTR);
        var sum = player.getNum1() + player.getNum2();
        var msg = "";
        if (playerSum == sum) {
            msg = "Correct!";
        } else {
            msg = "Not correct!";
        }
        request.setAttribute("num1", player.getNum1());
        request.setAttribute("num2", player.getNum2());
        request.setAttribute("sum", sum);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/answer.jsp")
                .forward(request, response);
    }
}
