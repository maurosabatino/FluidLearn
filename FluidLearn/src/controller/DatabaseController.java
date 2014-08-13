package controller;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import corso.*;

public class DatabaseController {
	static private String url = "jdbc:postgresql://localhost:5432/FluidLearn";
	static private String usr = "admin";
	static private String pwd = "dbalps";
	//UC_gestireCorso
	public static Corso InsertCorso(Corso c) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 

		String sql = "insert into corso(nome,descrizione) values (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getDescrizione());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			c.setIdCorso(rs.getInt("idcorso"));
		rs.close();
		ps.close();
		conn.close();
		return c;
	}
	public static Corso SelectCorso(int idCorso) throws SQLException{
		Corso c = new Corso();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from corso where idcorso = "+idCorso+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			c.setNome(rs.getString("nome"));
			c.setDescrizione(rs.getString("descrizione"));
			c.setIdCorso(rs.getInt("idcorso"));
		}
		rs.close();
		ps.close();
		conn.close();
		return c; 
	}
	public static ArrayList<Corso> SelectAllCorso() throws SQLException{
		ArrayList<Corso> corsi = new ArrayList<Corso>();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from corso";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Corso c = new Corso();
			c.setNome(rs.getString("nome"));
			c.setDescrizione(rs.getString("descrizione"));
			c.setIdCorso(rs.getInt("idcorso"));
			corsi.add(c);
		}
		rs.close();
		ps.close();
		conn.close();
		return corsi; 
	}
	
	public static Corso UpdateCorso(Corso c) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "update corso set nome = ?, descrizione = ? where idcorso = ?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2,c.getDescrizione());
		ps.setInt(3,c.getIdCorso());
		ps.executeUpdate();
		ps.close();
		conn.close();
		return c;
	}
	
	public static boolean DeleteCorso(int idCorso) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "delete from corso where idcorso = "+idCorso+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		return ps.execute();
	}
	
}
