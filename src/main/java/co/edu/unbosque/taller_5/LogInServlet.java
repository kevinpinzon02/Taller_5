package co.edu.unbosque.taller_4;


import java.io.*;
import java.util.List;

import co.edu.unbosque.taller_4.DTO.User;
import co.edu.unbosque.taller_4.services.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LogInServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        List<User> users = new UserService().getUsers();

        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());

            Cookie cookie = new Cookie("role", userFounded.getRole());
            cookie.setMaxAge(20);
            response.addCookie(cookie);



            if(userFounded.getRole().equals("Artist")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./artista.html");
                dispatcher.forward(request, response);

            } else if (userFounded.getRole().equals("Customer")) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("./usuario.html");
                dispatcher.forward(request, response);
            }


        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {}
}