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


/**
 *
 * @author Ashish
 */

@ManagedBean
@SessionScoped
@Named(value="topProfiles")

public class TopProfiles 
{
    private ArrayList<String> Top3f = new ArrayList<String>();
    private ArrayList<String> Top3 = new ArrayList<String>();
    private ArrayList<Integer> Top3C = new ArrayList<Integer>();
    private String gend;
    private String user_id;
    public String Top3()
    {
        Top3.clear();
        Top3C.clear();
        Top3f.clear();
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
            
            resultSet = statement.executeQuery("Select user_id, count from users "
                    + "where not user_id = '" + user_id + "' and gend = '" + gend + "'");
            
            
            while(resultSet.next())
            {
             
                Top3.add(resultSet.getString(1));
                Top3C.add(resultSet.getInt(2));
            }
            if(Top3.isEmpty()){
                return("internalError");
            }
            else{
                TopSort();
                return("topProfiles");
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
    
    public void TopSort()
    {
        
        for(int i =0; i < Top3C.size(); i++)
            {
            for(int j =i+1; j < Top3C.size(); j++)
            {
            if(Top3C.get(i) < Top3C.get(j))
                {
                int temp = Top3C.get(i);
                Top3C.add(i, Top3C.get(j));
                Top3C.remove(i+1);
                Top3C.add(j, temp);
                Top3C.remove(j+1);
                
                String temp1 = Top3.get(i);
                Top3.add(i, Top3.get(j));
                Top3.remove(i+1);
                Top3.add(j, temp1);
                Top3.remove(j+1);
                
                }
            }
        }
        for(int i =0; i < 3; i++){
            Top3f.add(Top3.get(i));
        }
    }

    public ArrayList<String> getTop3f() {
        return Top3f;
    }

    public void setTop3f(ArrayList<String> Top3f) {
        this.Top3f = Top3f;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    
    
}
