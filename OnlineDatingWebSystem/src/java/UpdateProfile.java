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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
public class UpdateProfile 
{
    private String user_id;
    private String fname;
    private String lname;
    private String gender;
    private int age;
    private String city;
    private String intrst1;
    private String intrst2;
    private String intrst3;
    
    
    public String GetProfile(String user_id)
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
                this.gender = resultSet.getString(3);
                this.age = resultSet.getInt(4);
                this.city = resultSet.getString(5);
                this.intrst1 = resultSet.getString(7);
                this.intrst2 = resultSet.getString(8);
                this.intrst3 = resultSet.getString(9);
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
        return"profile";
    }
    
    public String UpdatePro()
    {
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
        //ResultSet resultSet = null;  
        
        try
        {      
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kudesiaa0866", "1631773");   
            statement = connection.createStatement();
            
            String sql = "UPDATE users " +
                   "SET fname = '" + fname + "' ,lname = '" + lname+ "' ,gend = '" + gender + "' ,age = '" + age  +
                     "' ,city = '" + city + "' ,intrst1 = '" + intrst1 + "' ,intrst2 = '" + intrst2 +
                     "' ,intrst3 = '" + intrst3 +"' WHERE user_id = '" + user_id +  "'";
             int r = statement.executeUpdate(sql);
             return"";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return"";
        }
        finally
        {
            try
            {
                //resultSet.close();
                statement.close();
                connection.close();
                 
            }
            catch (Exception e)
            {
                e.printStackTrace();    
            }
        }
       
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIntrst1() {
        return intrst1;
    }

    public void setIntrst1(String intrst1) {
        this.intrst1 = intrst1;
    }

    public String getIntrst2() {
        return intrst2;
    }

    public void setIntrst2(String intrst2) {
        this.intrst2 = intrst2;
    }

    public String getIntrst3() {
        return intrst3;
    }

    public void setIntrst3(String intrst3) {
        this.intrst3 = intrst3;
    }
}
