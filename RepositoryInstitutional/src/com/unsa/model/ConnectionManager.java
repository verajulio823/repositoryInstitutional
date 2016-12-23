package com.unsa.model;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.unsa.entity.Facultad;


public  class ConnectionManager {
	static Connection c ;
	// Funcion que devuelve una lista 
    class ColumnInfo extends Object {

        public String columnName;
        public int columnType;

        public ColumnInfo(String columnName, int columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }
    }
    public static void GetConnection() {
      
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/repository?user=root&password=sistemas");
        } catch (SQLException e) {
        	System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        //return c;
    }
    
    
    
    public static List<Facultad> getFacultades() throws SQLException {
    	List<Facultad> facultades = new ArrayList<Facultad>();    	
    	
    	try {
    		c.setAutoCommit(false);    		
    		String query = "SELECT * FROM Facultad";			
    		Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
            	Facultad facultad = new Facultad();
            	facultad.setIdFacultad(rs.getInt("idFacultad"));
            	facultad.setNombre(rs.getString("nombre"));
            	facultad.setNombre_unsa(rs.getString("nombre_unsa"));            	
            	facultades.add(facultad);
            }
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			c.rollback();
			e.printStackTrace();
			
			return null;
		}
    	
    	return facultades;
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    