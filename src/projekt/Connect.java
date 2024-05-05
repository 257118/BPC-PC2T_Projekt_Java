package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connect {
	private Connection conn; 
	public boolean connect() { 
	       conn= null; 
	       try {
	    	   
	              conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");       
	              System.out.println("Pripojenie k databazi bolo uspesne.");
	       } 
	      catch (SQLException e) { 
	            System.out.println(e.getMessage());
		    return false;
	      } 
	      return true;
	      
	}

	public boolean createTable()
	{
	     if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS knihy (" + "Nazov varchar(255) PRIMARY KEY," + "Autor text NOT NULL,"+ "RokVydania integer, " + "Dostupnost boolean, " + "Zaner varchar(255),"+"Rocnik integer" + ");";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
	public void insertKniha(String nazov, String autor, int rok, boolean dostupnost, String zaner, Integer rocnik) {
	    String selectSql = "SELECT * FROM knihy WHERE Nazov = ?";
	    String updateSql = "UPDATE knihy SET Autor = ?, RokVydania = ?, Dostupnost = ?, Zaner = ?, Rocnik = ? WHERE Nazov = ?";
	    String insertSql = "INSERT INTO knihy(Nazov, Autor, RokVydania, Dostupnost, Zaner, Rocnik) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        
	        PreparedStatement selectStmt = conn.prepareStatement(selectSql);
	        selectStmt.setString(1, nazov);
	        ResultSet rs = selectStmt.executeQuery();

	        if (rs.next()) {
	            
	            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	            updateStmt.setString(1, autor);
	            updateStmt.setInt(2, rok);
	            updateStmt.setBoolean(3, dostupnost);
	            updateStmt.setString(4, zaner);
	            if (rocnik != null) {
	                updateStmt.setInt(5, rocnik);
	            } else {
	                updateStmt.setNull(5, Types.INTEGER);
	            }
	            updateStmt.setString(6, nazov);
	            updateStmt.executeUpdate();
	        } else {
	            
	            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
	            insertStmt.setString(1, nazov);
	            insertStmt.setString(2, autor);
	            insertStmt.setInt(3, rok);
	            insertStmt.setBoolean(4, dostupnost);
	            insertStmt.setString(5, zaner);
	            if (rocnik != null) {
	                insertStmt.setInt(6, rocnik);
	            } else {
	                insertStmt.setNull(6, Types.INTEGER);
	            }
	            insertStmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	public List<Kniha> selectAll(){
	    List<Kniha> knihy = new ArrayList<>();
	    String sql = "SELECT Nazov,Autor,rokVydania,Dostupnost,Zaner,Rocnik FROM knihy";
	    try {
	         Statement stmt  = conn.createStatement();
	         ResultSet rs    = stmt.executeQuery(sql);
	         while (rs.next()) {
	             String nazov = rs.getString("Nazov");
	             List<String> autori = Arrays.asList(rs.getString("Autor").split(","));
	             int rokVydania = rs.getInt("RokVydania");
	             boolean dostupnost = rs.getBoolean("Dostupnost");
	             String zaner = rs.getString("Zaner");
	             int rocnik = rs.getInt("Rocnik");
	             Kniha kniha;
	             if (zaner != null) {
	                 kniha = new Roman(nazov, autori, rokVydania, dostupnost, zaner);
	             } else {
	                 kniha = new Ucebnice(nazov, autori, rokVydania, dostupnost, rocnik);
	             }
	             knihy.add(kniha);
	         }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return knihy;
	}
	public void disconnect() { 
		if (conn != null) {
		       try {     
		    	   conn.close();
		    	   System.out.println("Odpojenie od databáze.");
		       } 
	               catch (SQLException ex) { System.out.println(ex.getMessage()); }

}
	}
}

