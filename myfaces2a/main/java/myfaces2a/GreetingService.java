package myfaces2a;

import java.io.*;
import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named
@ApplicationScoped
public class GreetingService
{
    public String createGreeting(String name)
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
            Statement stmt = c.createStatement();
            stmt.execute("CREATE TABLE tbl2 (a int, b varchar(200));");
            Statement stmt2 = c.createStatement();
            stmt2.execute("INSERT INTO tbl2 (a, b) VALUES (1, '" + name + "');");    
            Statement stmt4 = c.createStatement();
            stmt4.execute("DROP TABLE tbl2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(name);      
        } catch (Exception ignore) {
        }

        return "Hello " + name + ". We hope you enjoy Apache MyFaces!";
    }

}
