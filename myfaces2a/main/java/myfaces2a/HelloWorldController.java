package myfaces2a;

import javax.inject.Named;
import javax.inject.Inject;
import java.io.*;
import java.sql.*;
import javax.enterprise.context.RequestScoped;

/**
 * A typical simple backing bean, that is backed to <code>helloWorld.xhtml</code>
 */
@Named("helloWorld")
@RequestScoped
public class HelloWorldController
{

    private String name;
    private String safe;
    private String dbdata;
    @Inject private GreetingService greetingService;

    /**
     * default empty constructor
     */
    public HelloWorldController()
    {
    }

    /**
     * Method that is backed to a submit button of a form.
     */
    public String send()
    {
        this.safe = "safe";
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
            Statement stmt = c.createStatement();
            stmt.execute("CREATE TABLE tbl (a int, b varchar(200));");
            Statement stmt2 = c.createStatement();
            stmt2.execute("INSERT INTO tbl (a, b) VALUES (1, '" + this.getName() + "');");    
            Statement stmt3 = c.createStatement();
            stmt3.execute("INSERT INTO tbl (a, b) VALUES (2, '" + this.getSafe() + "');");
            PreparedStatement stmt4 = c.prepareStatement("SELECT * FROM tbl");
            ResultSet rs = stmt4.executeQuery();
            StringBuffer sb = new StringBuffer();
            while(rs.next()) {
                sb.append(rs.getString("b"));
                sb.append(" / ");
            }
            setDbdata(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(this.getName());      
        } catch (Exception ignore) {
        }
        try {
            FileInputStream fis = new FileInputStream(this.getSafe());
        } catch (Exception ignore) {
        }

        if(this.getName().contains("ljkasdfjkl")) {
            return this.getName();      
        }
        return "page2.xhtml";
    }

    public String getGreeting()
    {
        return greetingService.createGreeting(name);
    }
	
    //-------------------getter & setter

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSafe()
    {
        return safe;
    }
    public void setSafe(String safe)
    {
        this.safe = safe;
    }
    public String getDbdata() {
        return dbdata;
    }
    public void setDbdata(String dbdata) {
        this.dbdata = dbdata;
    }
}
