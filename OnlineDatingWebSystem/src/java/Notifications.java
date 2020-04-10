/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
public class Notifications implements Serializable {
    
    private ArrayList<String> notif = new ArrayList<String>();
    private String id;

    public String Notification1(String id) 
    {
        notif.clear();
        String uStatus="unseen";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            return ("internalError");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kudesiaa0866?useSSL=false";
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kudesiaa0866", "1631773");   
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select distinct sid from messages "
                    + "where rid = '" + id +  "' and  status = '"
                    + uStatus +  "'");
            
            while(resultSet.next())
            {
                notif.add("You have a message from " + resultSet.getString(1));
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("internalError");
             
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
        if(notif.isEmpty()){
            notif.add("No new notifications!");
        }
        return"";
    }    

    public ArrayList<String> getNotif() {
        return notif;
    }

    public void setNotif(ArrayList<String> notif) {
        this.notif = notif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
