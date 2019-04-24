package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet (name = "ExitServlet", urlPatterns = {"/exit"})
public class ExitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie1 : cookies) {
                cookie1.setMaxAge(0);
                resp.addCookie(cookie1);
            }
        }
        rd.forward(req,resp);
    }
}
