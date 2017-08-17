import jdk.nashorn.internal.runtime.JSONListAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by yash.raj on 14/08/17.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
      response.setContentType("text/html");



        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/news","root","!rnthakur123");
            PreparedStatement ps=con.prepareStatement("select title from business");
            ResultSet rs=ps.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();

            JSONArray json = new JSONArray();

            while (rs.next()) {
                int total=rsmd.getColumnCount();
                JSONObject jon = new JSONObject();

                for(int i=0;i<total;i++) {
                    String col_name = rsmd.getColumnName(i+1);

                    jon.put(col_name,rs.getString(i+1));
                }
                json.put(jon);
            }
          //  System.out.println(json);
            //response.setContentType("application/json");
           // response.getWriter().write(json.toString());
            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
            pw.close();

        }catch (Exception e2) {e2.printStackTrace();}


    }

}

