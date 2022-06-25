package com.google.sps.servlets.drink;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/drink-count")
public class DrinkCountServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        // Query to get coffee count
        Query<Entity> coffeeQuery = Query.newEntityQueryBuilder().setKind("Message")
                .setFilter(PropertyFilter.eq("drink", "coffee")).build();
        QueryResults<Entity> cofeeResults = datastore.run(coffeeQuery);
        int coffeeCount = 0;
        while (cofeeResults.hasNext()) {
            Entity entity = cofeeResults.next();
            coffeeCount++;
        }

        // Query to get tea count
        Query<Entity> teaQuery = Query.newEntityQueryBuilder().setKind("Message")
                .setFilter(PropertyFilter.eq("drink", "tea")).build();
        QueryResults<Entity> teaResults = datastore.run(teaQuery);
        int teaCount = 0;
        while (teaResults.hasNext()) {
            Entity entity = teaResults.next();
            teaCount++;
        }

        // Return results in json
        Map<String, Integer> drinkMap = new HashMap<String, Integer>();
        drinkMap.put("coffee", coffeeCount);
        drinkMap.put("tea", teaCount);
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(drinkMap));
    }
}
