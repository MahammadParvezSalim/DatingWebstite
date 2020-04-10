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
import javax.faces.context.FacesContext;
import java.io.Serializable;
import javax.inject.Named;
/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
@Named(value="logOut")
public class LogOut implements Serializable
{
    
    
    
    public String LogOut(String user_id)
    {
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kudesiaa0866?useSSL=false";
        Connection connection = null;
        Statement statement = null;
        
        
        try
        {
           connection = DriverManager.getConnection(DATABASE_URL, 
                  "kudesiaa0866", "1631773");
            statement = connection.createStatement(); 
            String s = DateAndTime.DateTime();
            String sql = "UPDATE users " +
                   "SET timeStamp = '" + s + "' WHERE user_id = '" + user_id + "'";
            statement = connection.createStatement();
            int r = statement.executeUpdate(sql);
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return"internalError";
        }
        finally
        {     
            try
            {
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        Logout1();
        return "index.xhtml";
    }
    
    public void Logout1(){
        FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
        
    }
}
