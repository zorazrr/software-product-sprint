package com.google.sps.servlets.drink;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/drink-count")
public class DrinkCountServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").build();
        QueryResults<Entity> results = datastore.run(query);
        int coffeeCount = 0;
        int teaCount = 0;
        while (results.hasNext()) {
            Entity entity = results.next();
            String drink = entity.getString("drink");
            if (drink.equals("coffee")) {
                coffeeCount++;
            } else {
                teaCount++;
            }
        }
        Map<String, Integer> drinkMap = new HashMap<String, Integer>();
        drinkMap.put("coffee", coffeeCount);
        drinkMap.put("tea", teaCount);
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(drinkMap));
    }
}
