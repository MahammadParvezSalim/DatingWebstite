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
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
@Named(value="accountLogin")
public class AccountLogin implements Serializable 
{
    private String user_id;
    private String pswd;
   
    private User u1;
    public String login()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ("internalError");
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
                    + "where user_id = '" + 
                    user_id + "'" );
            
            if(resultSet.next())
            {
                if(pswd.equals(resultSet.getString(2)))
                {
                    String s = DateAndTime.DateTime();
                    String sql = "UPDATE users " +
                    "SET timeStamp = '" + s + "' WHERE user_id = '" + user_id + "'";
                    statement = connection.createStatement();
                    int r = statement.executeUpdate(sql);
                    u1 = new User(user_id);
                    return "loggedinPage";
                }
                else
                {
                    user_id = "";
                    pswd = "";
                    FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Invalid Credentials, Please try again!"));
                    return "";
                }
            }
            else
            {
                user_id = "";
                pswd = "";
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("User does not exist!"));
                return "";   
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

    public User getU1() {
        return u1;
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
}
