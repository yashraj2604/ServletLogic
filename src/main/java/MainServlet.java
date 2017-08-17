import jdk.nashorn.internal.runtime.JSONListAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by yash.raj on 14/08/17.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String pass = "qwerty123";
        try {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection con = databaseHandler.connecttodatabase(pass);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
      response.setContentType("text/html");



        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/news","root","qwerty123");
            String tablename = "business_cluster_score";
            String str1 = "5";

            Vector<String> topcluster= new DatabaseHandler().topcluster(con, 10, "sports_cluster_score");

            JSONArray json = new DatabaseHandler().toparticle(con,topcluster);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();


        }catch (Exception e2) {e2.printStackTrace();}


    }

}

