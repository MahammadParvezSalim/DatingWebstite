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
@Named(value="viewProfile")

public class ViewProfile implements Serializable
{
    private ArrayList<Profile> viewp = new ArrayList<Profile>();
    private ArrayList<String> viewp1 = new ArrayList<String>();
    
    private String user_id;// viewed profile
    private String viewer;//viewers profile
    private ArrayList<String> whop = new ArrayList<String>();
    
    public String ViewProfile(String viewer, String user_id)
    {
        String pStatus = "pending";
        viewp.clear();
        viewp1.clear();
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
                    + "where user_id = '" + user_id + "'");
            
            if(resultSet.next())
            {
                int count = resultSet.getInt(10);
                count = count + 1;
                String sql = "UPDATE users " +
                   "SET count = '" + count + "' WHERE user_id = '" + user_id + "'";
                statement = connection.createStatement();
                int r = statement.executeUpdate(sql);
                
                //for Who viewed part
                
                String sql1 = "INSERT into views values" +
                   "('" + viewer + "', '" + user_id + "', '" + pStatus + "')";
                statement = connection.createStatement();
                int r1 = statement.executeUpdate(sql1);
                
                viewp.add(new Profile(resultSet.getString(11),resultSet.getString(12),resultSet.getString(3),resultSet.getInt(4)
                        ,resultSet.getString(5),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(6)));
            }
            for(Profile p:viewp)
            {
              viewp1.add("Name: " + p.getFname()+" "+p.getLname());
              viewp1.add("Gender: " + p.getGender());
              viewp1.add("City: " + p.getCity());
              viewp1.add("Age: " + p.getAge());
              viewp1.add("Interested in " + p.getIntrst1());
              viewp1.add("Interested in " + p.getIntrst2());
              viewp1.add("Interested in " + p.getIntrst3());
              viewp1.add("Last Seen at " + p.getTimeStamp());
              
            }
            if(!viewp1.isEmpty()){
                return("profileRes");
            }
            else{
                return("internalError");
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
    
    
    
    public String WhoViewed(String pid) 
    {
        whop.clear();
        String aStatus = "viewed";
        String pStatus = "pending";
        
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
            resultSet = statement.executeQuery("Select distinct viewer from views "
                    + "where profile = '" + pid + "' and status = '"
                    + pStatus +   "'");
            
            while(resultSet.next())
            {
                whop.add(resultSet.getString(1));
            }
            return"whoViewed";
            
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
    
    
    public String WViewProfile(String viewer, String user_id)
    {
        String aStatus = "seen";
        viewp.clear();
        viewp1.clear();
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
                    + "where user_id = '" + user_id + "'");
            
            if(resultSet.next())
            {
                int count = resultSet.getInt(10);
                count = count + 1;
                String sql = "UPDATE users " +
                   "SET count = '" + count + "' WHERE user_id = '" + user_id + "'";
                statement = connection.createStatement();
                int r = statement.executeUpdate(sql);
                
                //for Who viewed part
                String sql1 = "UPDATE views " +
                   "SET status = '" + aStatus + "' WHERE viewer  = '" + user_id + "' and profile = '" + viewer + "'";
                int r1 = statement.executeUpdate(sql1);
                
                
                
                viewp.add(new Profile(resultSet.getString(11),resultSet.getString(12),resultSet.getString(3),resultSet.getInt(4)
                        ,resultSet.getString(5),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(6)));
            }
            for(Profile p:viewp)
            {
              viewp1.add("Name: " + p.getFname()+" "+p.getLname());
              viewp1.add("Gender: " + p.getGender());
              viewp1.add("City: " + p.getCity());
              viewp1.add("Age: " + p.getAge());
              viewp1.add("Interested in " + p.getIntrst1());
              viewp1.add("Interested in " + p.getIntrst2());
              viewp1.add("Interested in " + p.getIntrst3());
              viewp1.add("Last Seen at " + p.getTimeStamp());
              
            }
            if(!viewp1.isEmpty()){
                return("profileRes");
            }
            else{
                return("internalError");
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

    public ArrayList<String> getViewp1() {
        return viewp1;
    }

    public void setViewp1(ArrayList<String> viewp1) {
        this.viewp1 = viewp1;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Profile> getViewp() {
        return viewp;
    }

    public void setViewp(ArrayList<Profile> viewp) {
        this.viewp = viewp;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public ArrayList<String> getWhop() {
        return whop;
    }

    public void setWhop(ArrayList<String> whop) {
        this.whop = whop;
    }
    
}
