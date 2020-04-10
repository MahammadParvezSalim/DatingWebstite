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

public class Messages implements Serializable
{
    private  ArrayList<String> Messages = new ArrayList<String>();
    private String r = "read";
    private String ur = "unread";
    private ArrayList<String> FilteredUser = new ArrayList<String>();
    private ArrayList<MessagedUser> MessagedUser = new ArrayList<MessagedUser>();
    private String sid;
    private String rid;
    private String id;
    private String msg;
    
    public String SendMessage() 
    {
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
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kudesiaa0866?useSSL=false";
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kudesiaa0866", "1631773");   
            String sql = "INSERT into messages values" +
                   "('" + sid + "', '" + id + "', '" + msg + "', '" + uStatus+ "')";
            statement = connection.createStatement();
            int r = statement.executeUpdate(sql);
            ReadMessage2(sid,rid);
            return"messagesUsers";
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
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
        
    }
    public String ReadMessage(String sid,String rid) 
    {
        
        String sStatus = "seen";
        this.id = rid;
        this.rid = rid;
        Messages.clear();
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
            resultSet = statement.executeQuery("Select * from messages "
                    + "where sid = '" + sid + "' and rid = '"
                    + rid + "' or sid = '" + rid + "' and  rid = '"
                    + sid +  "'");
            statement = connection.createStatement();
                String sql1 = "UPDATE messages " +
                   "SET status = '" + sStatus + "' WHERE sid  = '" + sid + "' and rid = '" + rid + "' or sid = '" + rid + "' and  rid = '"
                    + sid +  "'";
                int r1 = statement.executeUpdate(sql1);
            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(sid))
                {
                    Messages.add(sid + ": " + resultSet.getString(3));
                }
                else if(resultSet.getString(1).equals(rid))
                {
                    Messages.add(rid + ": " +resultSet.getString(3));
                }
                
            }
            return"messagesUsers";
            
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
    public String ReadMessage2(String sid,String rid) 
    {
        String sStatus = "seen";
        this.id = rid;
        this.rid = rid;
        Messages.clear();
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
            resultSet = statement.executeQuery("Select * from messages "
                    + "where sid = '" + sid + "' and rid = '"
                    + rid + "' or sid = '" + rid + "' and  rid = '"
                    + sid +  "'");
            
            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(sid))
                {
                    Messages.add(sid + ": " + resultSet.getString(3));
                }
                else if(resultSet.getString(1).equals(rid))
                {
                    Messages.add(rid + ": " + resultSet.getString(3));
                }
                
            }
            return"messages";
            
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
    
    public String MessagedUser(String id) 
    {
        FilteredUser.clear();
        MessagedUser.clear();
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
            resultSet = statement.executeQuery("Select distinct sid, rid from messages "
                    + "where sid = '" + id + "' or rid = '" + id +  "'");
            
            while(resultSet.next())
            {
                MessagedUser.add(new MessagedUser(resultSet.getString(1),resultSet.getString(2)));
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
        if(MessagedUser.isEmpty())
        {
            return"messagesUsers";
        }
        else
        {
            for(int i=0;i<MessagedUser.size();i++)
            {
            if(MessagedUser.get(i).getSid().equals(id))
            {
                FilteredUser.add(MessagedUser.get(i).getRid());
            }
            else if(MessagedUser.get(i).getRid().equals(id))
            {
                FilteredUser.add(MessagedUser.get(i).getSid());
            }
            }
            
            for(int i = 0; i < FilteredUser.size()-1; i++)
            {
                for(int j = i+1; j<FilteredUser.size(); j++)
                {
                    if(FilteredUser.get(i).equals(FilteredUser.get(j))){
                        FilteredUser.remove(j);
                    }
                    if(FilteredUser.get(i).equals(id)){
                        FilteredUser.remove(j);
                    }
                }
            }
            return"messagesUsers";
        }
        
    }
    

    public ArrayList<String> getFilteredUser() {
        return FilteredUser;
    }
    

    public ArrayList<String> getMessages() {
        return Messages;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
