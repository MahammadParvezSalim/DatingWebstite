/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.Serializable;
/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
public class User implements Serializable
{
    private String user_id;
    private String pswd;
    private String gend;
    private int age;
    private String city;
    private String timeStamp;
    private String intrst1;
    private String intrst2;
    private String intrst3;
    private int count;
    private String fname;
    private String lname;

    public User(String user_id) 
    {
        this.user_id = user_id;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kudesiaa0866?useSSL=false";
        Connection connection = null;  
        Statement statement = null;    
        ResultSet resultSet = null;  
        
        
        try
        {      
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kudesiaa0866", "1631773");   
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select * from users "
                    + "where user_id = '" + user_id + "'");
            
            if(resultSet.next())
            {
                this.pswd = resultSet.getString(2);
                this.gend = resultSet.getString(3);
                this.age = resultSet.getInt(4);
                this.city = resultSet.getString(5);
                this.timeStamp = resultSet.getString(6);
                this.intrst1 = resultSet.getString(7);
                this.intrst2 = resultSet.getString(8);
                this.intrst3 = resultSet.getString(9);
                this.count = resultSet.getInt(10);
                this.fname = resultSet.getString(11);
                this.lname = resultSet.getString(12);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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
    }

    public String getUserid() {
        return user_id;
    }

    public String getPswd() {
        return pswd;
    }

    public String getGend() {
        return gend;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getIntrst1() {
        return intrst1;
    }

    public String getIntrst2() {
        return intrst2;
    }

    public String getIntrst3() {
        return intrst3;
    }

    public int getCount() {
        return count;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
