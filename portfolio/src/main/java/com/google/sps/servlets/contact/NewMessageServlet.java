package com.google.sps.servlets.contact;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for creating new messages from user inputs. */
@WebServlet("/new-message")
public class NewMessageServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");
        String email = request.getParameter("email");
        String drink = request.getParameter("forFun");
        long timestamp = System.currentTimeMillis();

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
        FullEntity messageEntity = Entity.newBuilder(keyFactory.newKey())
                .set("message", message)
                .set("email", email)
                .set("drink", drink)
                .set("timestamp", timestamp)
                .build();
        datastore.put(messageEntity);
        response.sendRedirect("messages.html");
    }
}
