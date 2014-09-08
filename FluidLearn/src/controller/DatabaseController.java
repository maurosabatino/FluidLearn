package controller;



import java.sql.*;
import java.util.*;

import partecipante.*;
import contributo.*;
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
		boolean result = ps.execute();
		ps.close();
		conn.close();
		return result;
	}
	
	//UC_gestireUDA
	
	public static UnitaDA insertUDA(UnitaDA UDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into uda(nome,descrizione,idcorso,dataattivazione) values (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, UDA.getNome());
		ps.setString(2, UDA.getDescrizione());
		ps.setInt(3, UDA.getIdCorso());
		Timestamp t= new Timestamp(UDA.getData().getTime());
		ps.setTimestamp(4,t );
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			UDA.setIdUDA(rs.getInt("iduda"));
		rs.close();
		ps.close();
		conn.close();
		return UDA;
	}
	public static UnitaDA updateUDA(UnitaDA UDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "update uda set nome = ?, descrizione = ?, idcorso = ?, dataattivazione = ? where iduda = ?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, UDA.getNome());
		ps.setString(2,UDA.getDescrizione());
		ps.setInt(3,UDA.getIdCorso());
		Timestamp t= new Timestamp(UDA.getData().getTime());
		ps.setTimestamp(4,t);
		ps.setInt(5, UDA.getIdUDA());
		ps.executeUpdate();
		ps.close();
		conn.close();
		return UDA;
	}
	public static boolean deleteUDA(int idUDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "delete from uda where iduda = "+idUDA+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		boolean result = ps.execute();
		ps.close();
		conn.close();
		return result;
	}
	public static UnitaDA selectUDA(int idUDA) throws SQLException{
		UnitaDA UDA = new UnitaDA(); 
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from uda where iduda = "+idUDA+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			UDA.setNome(rs.getString("nome"));
			UDA.setDescrizione(rs.getString("descrizione"));
			UDA.setIdCorso(rs.getInt("idcorso"));
			UDA.setData(rs.getTimestamp("dataattivazione"));
			UDA.setIdUDA(rs.getInt("iduda"));
		}
		rs.close();
		ps.close();
		conn.close();
		return UDA; 
	}
	public static ArrayList<UnitaDA> selectAllUDA(int idCorso) throws SQLException{
		ArrayList<UnitaDA> ListUDA = new ArrayList<UnitaDA>();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from uda where idcorso = "+idCorso+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			UnitaDA UDA = new UnitaDA(); 
			UDA.setNome(rs.getString("nome"));
			UDA.setDescrizione(rs.getString("descrizione"));
			UDA.setIdCorso(rs.getInt("idcorso"));
			UDA.setData(rs.getTimestamp("dataattivazione"));
			UDA.setIdUDA(rs.getInt("iduda"));
			ListUDA.add(UDA);
		}
		rs.close();
		ps.close();
		conn.close();
		return ListUDA;
	}
	
	//UC_gestirePercorsoDiApprendimento
	
	//aggiungiPercorso
	
	//rimuoviPercorso
	
	public static void attivaUDA(int idUDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "update uda set dataattivazione = ? where iduda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		GregorianCalendar gc = new GregorianCalendar();
		ps.setTimestamp(1,new Timestamp(gc.getTime().getTime()));
		ps.setInt(2, idUDA);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	public static void disattivaUDA(int idUDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "update uda set dataattivazione = ? where iduda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setTimestamp(1,new Timestamp(0));
		ps.setInt(2, idUDA);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	/*---------nodo-----------*/
	public static Nodo insertNodo(Nodo nodo) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into nodo(nome,descrizione,iduda,iscomposite) values (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, nodo.getNome());
		ps.setString(2, nodo.getDescrizione());
		ps.setInt(3, nodo.getIdUDA());
		ps.setBoolean(4, false); //sempre false perch� di base si crea sempre una foglia
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			nodo.setIdNodo(rs.getInt("idnodo"));
		rs.close();
		ps.close();
		conn.close();
		return nodo;
	}
	public static Nodo insertNodoLeaf(Nodo nodo) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		PreparedStatement ps = conn.prepareStatement("update nodo set iscomposite=true where idnodo="+nodo.getIdNodo()+"");
		PreparedStatement composto = null;
		ps.execute();
		for(Nodo nd :((NodoComposite)nodo).getNodi()){
			nd = insertNodo(nd);
			 composto = conn.prepareStatement("insert into nodocomposto(idnodopadre,idnodofiglio) values(?,?)");
			composto.setInt(1,nodo.getIdNodo());
			composto.setInt(2, nd.getIdNodo());
			composto.executeUpdate();
		}
		composto.close();
		ps.close();
		conn.close();
		return nodo;
	}
	
	public static Nodo selectNodo(int idNodo) throws SQLException{
		Nodo nodo= new NodoLeaf();; 
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from nodo where idnodo = "+idNodo+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			if(rs.getBoolean("iscomposite")) {
				nodo = new NodoComposite();
				((NodoComposite)nodo).setNodi(selectNodiLeaf(idNodo));
			}
			nodo.setIdNodo(rs.getInt("idnodo"));
			nodo.setNome(rs.getString("nome"));
			nodo.setDescrizione(rs.getString("descrizione"));
			nodo.setIdUDA(rs.getInt("iduda"));
			
		}
		rs.close();
		ps.close();
		conn.close();
		return nodo; 
	}
	
	public static ArrayList<Nodo> selectAllNodi(int idUDA) throws SQLException{
		ArrayList<Nodo> ListNodi = new ArrayList<Nodo>();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from nodo where iduda = "+idUDA+" and (iscomposite=true or idnodo not in (select idnodofiglio from nodocomposto))";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			ListNodi.add(selectNodo(rs.getInt("idnodo")));
		}
		rs.close();
		ps.close();
		conn.close();
		return ListNodi;
	}
	
	
	/*----------post-------------*/
	
	public static Azione insertAzione(Azione azione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into post(idPartecipante,idNodo,testo,data,deadline,idplugin) values (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, azione.getIDPartecipante());
		ps.setInt(2, azione.getIDNodo());
		ps.setString(3, azione.getCorpo().getText());
		Timestamp t= new Timestamp(azione.getData().getTime());
		ps.setTimestamp(4,t);
		if(azione.hasDeadline()) ps.setTimestamp(5, new Timestamp(((Sollecitazione)azione).getDeadline().getTime()));
		else ps.setNull(5, Types.TIMESTAMP);
		if(azione.getCorpo().hasPlugin()) ps.setInt(6, ((Artefatto)azione).getIdPlugin());
		else ps.setNull(6,Types.INTEGER);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			azione.setIDPost(rs.getInt("idpost"));
		rs.close();
		ps.close();
		conn.close();
		return azione;
	}
	public static ArrayList<Nodo> selectNodiLeaf(int idNodo) throws SQLException{
		ArrayList<Nodo> nodiLeaf = new ArrayList<Nodo>();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select idnodofiglio from nodocomposto where idnodopadre = "+idNodo+"";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Nodo nodo = selectNodo(rs.getInt("idnodofiglio"));
			nodiLeaf.add(nodo);
		}
		rs.close();
		ps.close();
		conn.close();
		return nodiLeaf;
		
	}
	public static Azione selectPost(int idPost) throws SQLException{
		Azione contributo = new Post();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select * from post where idpost = "+idPost+"";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Corpo corpo;
			if(rs.getInt("idplugin")!=0){
				corpo = new Artefatto();
				((Artefatto)corpo).setIdPlugin(rs.getInt("idplugin"));
			}
			else corpo = new Testo();
			corpo.setText(rs.getString("testo"));
			if(rs.getTimestamp("deadline")!=null){
				contributo = new Sollecitazione();
				((Sollecitazione)contributo).setDeadline(rs.getTimestamp("deadline"));
			}
			contributo.setIDNodo(rs.getInt("idnodo"));
			contributo.setIDPartecipante(rs.getInt("idpartecipante"));
			contributo.setIDPost(rs.getInt("idpost"));			
		}
		rs.close();
		ps.close();
		conn.close();
		return contributo;
	}
	public static ArrayList<Azione> selectAllPostNodo(int idNodo) throws SQLException{
		ArrayList<Azione> postNodo = new ArrayList<Azione>();
		String sql = "select * from post where idnodo = "+idNodo+" and idplugin is  NULL and deadline is null";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Azione post = new Post();
			post.setIDPost(rs.getInt("idpost"));
			post.setData(rs.getTime("data"));
			Corpo testo = new Testo();
			testo.setText(rs.getString("testo"));
			post.setCorpo(testo);
			postNodo.add(post);
		}
		rs.close();
		ps.close();
		conn.close();
		return postNodo;
	}
	
	/*------------------Commento-------------------*/
	public static Reazione insertReazione(Reazione reazione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into commento(idpost,idpartecipante,testo,data,idplugin) values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, reazione.getIDPost());
		ps.setInt(2, reazione.getIDPartecipante());
		ps.setString(3, reazione.getCorpo().getText());
		Timestamp t= new Timestamp(reazione.getData().getTime());
		ps.setTimestamp(4,t);
		
		if(reazione.getCorpo().hasPlugin()) ps.setInt(5, ((Artefatto)reazione).getIdPlugin());
		else ps.setNull(5,Types.INTEGER);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			reazione.setIDPost(rs.getInt("idpost"));
		rs.close();
		ps.close();
		conn.close();
		return reazione;
	}
	
	
	
	/*----------parteciapnte--------*/
	public static Partecipante selectPartecipante(String username, String password) throws SQLException{
	
			Connection conn = DriverManager.getConnection(url,usr,pwd);
			String sql = " select * from utente where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Partecipante p = new PartecipanteConcreto();
				p.setNome(rs.getString("username"));
				p.setIDPartecipante(rs.getInt("idutente"));
				return p;
			}
			ps.close();
			rs.close();
			conn.close();
			return null;
		
	}
}
