package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns HTML that contains the page view count. */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // Add lyrics to arraylist
      String[] lyrics = {
        "Like we are made of starlight.", 
        "I can see us lost in the memory, August slipped away into a moment in time.",
        "May these memories break our fall."
      };

      // Return json
      response.setContentType("application/json;");
      Gson gson = new Gson();
      String json = gson.toJson(lyrics);
      response.getWriter().println(json);
  }
}
