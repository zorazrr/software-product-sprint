package com.google.sps.servlets.form;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the value entered in the form.
        String message = request.getParameter("message");
        String email = request.getParameter("email");
        String forFun = request.getParameter("forFun");

        // Print the value so you can see it in the server logs.
        System.out.println("User Email: " + email);
        System.out.println("User Message: " + message);

        // Write the value to the response so the user can see it.
        response.getWriter().println("<h1>Thank You!</h1>");
        response.getWriter().println("Your Email: " + email);
        response.getWriter().println("<br/>");
        response.getWriter().println("Your Message: " + message);
        response.getWriter().println("<br/>");
        if ("coffee".equals(forFun)) {
            response.getWriter().println(
                    "<img src = \" https://danverspublicschools.org/dhs/wp-content/uploads/sites/4/2017/09/coffee-clipart-free-coffee-cup-clipart.jpg \"/>");
        } else {
            response.getWriter().println(
                "<img src = \" https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFLBQ8ONYNSWejYsN7i4E7KDhpW9yk061qhA&usqp=CAU \"/>");
        }
    }
}