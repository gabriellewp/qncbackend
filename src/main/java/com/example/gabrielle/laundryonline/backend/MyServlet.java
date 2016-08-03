/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.gabrielle.laundryonline.backend;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;




public class MyServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.example.gabrielle.laundryonline.backend.MyServlet");
    private String takenDate;
    private String snapshot;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        Log.info("startinggetservlet");
        Firebase firebase = new Firebase("https://project-8417802664352299920.firebaseio.com/laundryOrders");

        Log.info("firebaseservletchsen");
        //firebase.child("laundryOrders").orderByChild("username_email").equalTo("gaby3@gmail.com").addListenerForSingleValueEvent(new ValueEventListener() {
        firebase.orderByChild("username_email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Build   the email message contents using every field from Firebase.
                final StringBuilder newItemMessage = new StringBuilder();
               // newItemMessage.append("Good Morning!  "+dataSnapshot.toString());
                snapshot = dataSnapshot.toString();
                //takenDate = dataSnapshot.child("takenDate").getValue(String.class).toString();
//                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        LaundryOrder lo = postSnapshot.getValue(LaundryOrder.class);
//                        orderList.add(lo);
//                    }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }

        });
        resp.getWriter().println(snapshot);
        //resp.getWriter().println("TakenDate :" + takenDate);
        resp.getWriter().println("Please use the form to POST to this url123");

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

            final String name = req.getParameter("name");

            resp.setContentType("text/plain");

        Firebase firebase = new Firebase("https://project-8417802664352299920.firebaseio.com/laundryOrders");


        firebase.orderByChild("username_email").equalTo("gaby3@gmail.com").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Build the email message contents using every field from Firebase.
                final StringBuilder newItemMessage = new StringBuilder();
                newItemMessage.append("Good Morning!  You have the following todo items:\n");
                takenDate = dataSnapshot.child("takenDate").getValue(String.class).toString();
//                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        LaundryOrder lo = postSnapshot.getValue(LaundryOrder.class);
//                        orderList.add(lo);
//                    }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }

        });

        resp.getWriter().println("TakenDate :" + takenDate);

    }
}
