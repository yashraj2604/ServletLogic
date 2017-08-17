import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.Vector;

/**
 * Created by yash.raj on 17/08/17.
 */
public class DatabaseHandler {

    public Connection connecttodatabase(String pass) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/news","root",pass);
        return con;
    }

    public void connectionclose(Connection con) throws SQLException {
        con.close();
    }

    public Vector<String> topcluster(Connection con, int noofClu, String tablename) throws SQLException {

        String str1 = Integer.toString(noofClu);

        String query = "select clusterid from " + tablename + " order by clusterScore limit " + str1;
        PreparedStatement ps=con.prepareStatement(query);

        ResultSet rs=ps.executeQuery();

        ResultSetMetaData rsmd=rs.getMetaData();

        JSONArray json = new JSONArray();

        Vector<String> vector= new Vector<String>();

        while (rs.next()) {
            int total=rsmd.getColumnCount();

            for(int i=0;i<total;i++) {
                String col_name = rsmd.getColumnName(i+1);

                vector.add(rs.getString(i+1));
            }

        }

        return vector;
    }

    public JSONArray toparticle(Connection con, Vector<String> vec) throws SQLException {
        JSONArray json = new JSONArray();

        for(int i=0;i<vec.size();i++) {
            String s = vec.get(i);
            String query = "select \n" +
                    "a.title as title, \n" +
                    "a.url as url,\n" +
                    "a.pubdate as pubdate,\n" +
                    "a.articleScore as articleScore,\n" +
                    "b.clusterID as clusterID\n" +
                    "from business a\n" +
                    "inner join business_cluster b\n" +
                    "on a.id = b.id\n" +
                    "where b.clusterID = " + s + "\n" +
                    "order by a.articleScore desc\n" +
                    "limit 10";

            PreparedStatement ps=con.prepareStatement(query);

            ResultSet rs=ps.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();

            while (rs.next()) {
                int total=rsmd.getColumnCount();
                JSONObject jon = new JSONObject();

                for(int j=0;j<total;j++) {
                    String col_name = rsmd.getColumnName(j+1);

                    jon.put(col_name,rs.getString(j+1));
                }
                json.put(jon);
            }


        }

        return json;
    }

}
