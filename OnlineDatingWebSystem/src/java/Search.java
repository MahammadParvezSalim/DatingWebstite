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
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serializable;

/**
 *
 * @author Ashish
 */
@ManagedBean
@SessionScoped
@Named(value="search")
public class Search implements Serializable {

    /**
     * Creates a new instance of Search
     */
    private String userid;
    private String gend;
    private int minAge;
    private int maxAge;
    private String city;
    private String interest;
    private ArrayList<String> searchedList = new ArrayList<String>();
    private String any;
    
    public String Search()
    {
        searchedList.clear();
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
                    + "where not user_id = '" + userid + "' and gend = '" + gend + "' and age between '"
                    + minAge + "' and '" + maxAge + "' and city = '" + city + "' and ( intrst1 = '" 
                    + interest + "' or intrst2 = '" + interest 
                    + "' or intrst3 = '" + interest + "')");
            while(resultSet.next())
            {
                searchedList.add(resultSet.getString(1));
            }
            if(!searchedList.isEmpty()){
                return("searchedRes");
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("No Result Found!"));
                return("");
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
    public String AnySearch()
    {
        searchedList.clear();
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
                    + "where not user_id = '" + userid + "' and user_id = '" + any + "' or gend = '" + any + "' or city = '" + any + "' or  intrst1 = '" 
                    + any + "' or intrst2 = '" + any 
                    + "' or intrst3 = '" + any + "'");
            while(resultSet.next())
            {
                searchedList.add(resultSet.getString(1));
            }
            if(!searchedList.isEmpty()){
                return("searchedRes");
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("No Result Found!"));
                return("");
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

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }
    

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public ArrayList<String> getSearchedList() {
        return searchedList;
    }

    public void setSearchedList(ArrayList<String> searchedList) {
        this.searchedList = searchedList;
    }
    
    
}
