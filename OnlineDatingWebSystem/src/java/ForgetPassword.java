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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
public class ForgetPassword implements Serializable {

    private String oldPassword = "";
    
    private String user_id;
    private String ans;
    
    public String CheckUser()
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
                this.oldPassword = resultSet.getString(2);
                return"forgetPasswordQue.xhtml";
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("User Does not Exist!"));
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
    
    public String ForgotPassword()
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
        
        ResultSet resultSet1 = null;
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kudesiaa0866?useSSL=false";
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kudesiaa0866", "1631773");   
            statement = connection.createStatement();
            resultSet1 = statement.executeQuery("Select * from forgetpassword "
                    + "where user_id = '" + 
                    user_id  + "'" );
            
                 if(resultSet1.next())
                 {
                     if(resultSet1.getString(2).equals(ans))
                     {
                         return("forgetPasswordFinal");
                     }
                     else
                     {
                     FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Incorrect answer! Please try Again."));
                     return (""); 
                     }
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
                resultSet1.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }  
        return"";
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
}
