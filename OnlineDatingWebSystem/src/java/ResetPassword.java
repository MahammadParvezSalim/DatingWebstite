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
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
@Named(value="resetPassword")

public class ResetPassword 
{
    
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;
    private String user_id;
    
    public String ResetPassword(String user_id)
    {
        boolean newpass = false;
        boolean oldpass = false;
        if(newPassword1.equals(newPassword2)){
            newpass = true;
        }
        if(!newPassword1.equals(oldPassword))
        {
            oldpass = true;
        }
        if(!oldpass)
        {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Old password cannot be same as new!"));
            return(""); 
        }
        else if(!newpass)
        {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Please confirm the password again!"));
            return(""); 
        }
        else
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
                    user_id  + "' and pswd = '" + newPassword1 + "'" );
            
            if(resultSet.next())
            {
                 FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("New password matches old password! Try again."));
                 return("");
            }
            else
            {
                String sql = "UPDATE users " +
                   "SET pswd = '" + newPassword1 + "' WHERE user_id = '" + user_id + "'";
                   statement = connection.createStatement();
                   int r = statement.executeUpdate(sql);
                
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Reset successful! Try logging in again."));
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
        
        
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
