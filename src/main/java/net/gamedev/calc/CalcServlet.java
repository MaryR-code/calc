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
    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var rand = ThreadLocalRandom.current();
        player.setNum1(rand.nextInt(100, 1000));
        player.setNum2(rand.nextInt(100, 1000));
        //request.getSession().setAttribute(Player.ATTR, player);
        request.getSession().setAttribute("num1", player.getNum1());
        request.getSession().setAttribute("num2", player.getNum2());
        request.getRequestDispatcher("/WEB-INF/calc.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var playerSum = Integer.parseInt(request.getParameter("playerSum"));
        //request.getSession().getAttribute(Player.ATTR);
        var num1 = (int) request.getSession().getAttribute("num1");
        var num2 = (int) request.getSession().getAttribute("num2");
        var sum = num1 + num2;
        var msg = "";
        if (playerSum == sum) {
            msg = "Correct!";
        } else {
            msg = "Not correct!";
        }
        request.getSession().setAttribute("num1", num1);
        request.getSession().setAttribute("num2", num2);
        request.setAttribute("sum", sum);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/answer.jsp")
                .forward(request, response);
    }
}
