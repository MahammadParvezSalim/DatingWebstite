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
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
@Named(value="friends")
public class Friends implements Serializable
{
    
    private ArrayList<String> friendList = new ArrayList<String>();
    private ArrayList<String> friendReqs = new ArrayList<String>();
    private String user_id;
    
    public String FriendList(String user_id)
    {
        friendList.clear();
        String aStatus = "approved";
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
            
            resultSet = statement.executeQuery("Select * from friends "
                    + "where (sid = '" + user_id + "' or rid = '" + user_id  + "') and status = '"
                    + aStatus +  "'");
            
            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(user_id))
                {
                    friendList.add(resultSet.getString(2));
                }
                else if(resultSet.getString(2).equals(user_id))
                {
                    friendList.add(resultSet.getString(1));
                }
                
            }
            return("friendsList");
            
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
    
    public String SendRequest(String id1, String id2) 
    {
    String aStatus = "approved";
        String pStatus = "pending";
        String msg="";
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
            resultSet = statement.executeQuery("Select * from friends "
                    + "where sid = '" + id1 + "' and rid = '"
                    + id2 + "' or sid = '"
                    + id2 + "' and rid = '"
                    + id1 + "' and ( status = '"
                    + pStatus + "' or status = '"
                    + aStatus +  "')");
            
            if(resultSet.next())
            {
                 FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Friend request already sent!"));
                 return"";
            }
            else
            {
                int r = statement.executeUpdate("insert into friends values"
                        + "('" + id1 + "', '" + id2 + "', '"
                        + pStatus + "', '" + msg + "')");
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Friend Request sent!"));
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
    
    public String AcceptRequest(String sid, String rid) 
    {
        String pStatus = "pending";
        String aStatus = "approved";
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
            resultSet = statement.executeQuery("Select * from friends "
                    + "where sid = '" + sid + "' and rid = '"
                    + rid + "' and status = '"
                    + pStatus + "'");
            
            if(resultSet.next())
            {
                String sql = "UPDATE friends " +
                   "SET status = '" + aStatus + "' WHERE rid = '" + rid + "' and sid = '" + sid + "'";
                int r = statement.executeUpdate(sql);
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Request Accepted!"));
                return"";
            }
            else
            {
                
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("No request found!"));
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
    
    public String DenyRequest(String sid, String rid) 
    {
        String pStatus = "pending";
        String dStatus = "denied";
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
            resultSet = statement.executeQuery("Select * from friends "
                    + "where sid = '" + sid + "' and rid = '"
                    + rid + "' and status = '"
                    + pStatus + "'");
            
            if(resultSet.next())
            {
                String sql = "UPDATE friends " +
                   "SET status = '" + dStatus + "' WHERE rid = '" + rid + "' and sid = '" + sid + "'";
                int r = statement.executeUpdate(sql);
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Request Accepted!"));
                return"";
            }
            else
            {
                
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("No request found!"));
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
    
    public String ReceivedRequests(String user_id) 
    {
        friendReqs.clear();
        String pStatus = "pending";
        String aStatus = "approved";
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
            resultSet = statement.executeQuery("Select * from friends "
                    + "where rid = '" + user_id  + "' and status = '"
                    + pStatus +  "'");
            
            while(resultSet.next())
            {
                friendReqs.add(resultSet.getString(1));
            }
            return"friendRequests";
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

    public ArrayList<String> getFriendList() {
        return friendList;
    }

    public ArrayList<String> getFriendReqs() {
        return friendReqs;
    }
    

    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
}
