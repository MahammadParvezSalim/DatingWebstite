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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;



/**
 *
 * @author Ashish
 */
@ManagedBean
@RequestScoped
@Named(value="accountSignup")
public class AccountSignup  {
    
    private String user_id;
    private String pswd;
    private String gend;
    private int age;
    private String city;
    private String timeStamp = " ";
    private String intrst1;
    private String intrst2;
    private String intrst3;
    private int count = 0;
    private String fname;
    private String lname;
    private String ans;
    
    public String signup() 
    {
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
            resultSet = statement.executeQuery("Select * from users "
                    + "where user_id = '" + 
                    user_id  + "'" );
            
            if(resultSet.next())
            {
                 FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("User ID already Exists!"));
                 return"";
            }
            else
            {
                int r = statement.executeUpdate("insert into users "
                        + "values ('" + user_id + "', '" + pswd + "', '" 
                    + gend + "', '" + age + "', '" + city + "', '" + timeStamp +
                        "', '" + intrst1 + "', '" + intrst2 + "', '" + intrst3 + 
                        "', '" + count + "', '" + fname + "', '" + lname +"')");
                
                int r1 = statement.executeUpdate("insert into forgetpassword "
                        + "values ('" + user_id + "', '" + ans +"')");
                
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Signup successful"));
                return ("");
                
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
        
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
    
}
