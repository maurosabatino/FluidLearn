package controller;



import java.sql.*;
import java.util.*;

import com.sun.faces.mgbean.ManagedBeanPreProcessingException.Type;

import partecipante.*;
import contributo.*;
import contributo.corpo.Artefatto;
import contributo.corpo.Corpo;
import contributo.corpo.Testo;
import contributo.valutazione.Valutazione;
import contributo.valutazione.ValutazioneInt;
import contributo.valutazione.ValutazioneString;
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
		if(UDA.getData()!=null){
			Timestamp t= new Timestamp(UDA.getData().getTime());
			ps.setTimestamp(4,t );
		}else ps.setNull(4, Types.TIMESTAMP);;
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
		String sqlPercorso = "select * from percorsoDA where iduda1 = ?";
		ps = conn.prepareStatement(sqlPercorso);
		ps.setInt(1, idUDA);
		rs = ps.executeQuery();
		while (rs.next()){
			UDA.getUDADipendenti().add(rs.getInt("iduda2"));
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
	
	public static PercorsoDiApprendimento insertPercorso(PercorsoDiApprendimento percorso) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into percorsoda(iduda1,iduda2) values (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, percorso.getUda1().getIdUDA());
		ps.setInt(2, percorso.getUda2().getIdUDA());
		ps.executeUpdate();
		ps.close();
		conn.close();
		return percorso;
	}
	
	public static void deletePercorsoDA(PercorsoDiApprendimento percorso) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "delete from percorsoda where iduda1 = ? and iduda2 = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, percorso.getUda1().getIdUDA());
		ps.setInt(2, percorso.getUda2().getIdUDA());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	public static void updatePercorsoDA(int iduda1,int iduda2,int idPercorsoDA) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "update percorsoda set iduda1 = ? ,iduda2 = ? where idpercorsoda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, iduda1);
		ps.setInt(2, iduda2);
		ps.setInt(3, idPercorsoDA);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	
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
	
	public static void deleteNodo(int idnodo) throws SQLException{
		String sql = "delete from nodo where idnodo = ?";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setInt(1, idnodo);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	public static Nodo updateNodo(Nodo nd) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "update nodo set nome = ?, descrizione = ?, iduda = ? where idnodo = ?";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setString(1, nd.getNome());
		ps.setString(2,nd.getDescrizione());
		ps.setInt(3,nd.getIdUDA());
		ps.setInt(4, nd.getIdNodo());
		ps.executeUpdate();
		ps.close();
		conn.close();
		return nd;
	}
	
	
	/*----------post-------------*/
	
	public static Azione insertAzione(Azione azione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "insert into post(idPartecipante,iduda,idNodo,testo,data,deadline,idplugin,stato,visibility) values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, azione.getIDPartecipante());
		ps.setInt(2, azione.getIDUDA());
		if(azione.getIDNodo()!=0)ps.setInt(3, azione.getIDNodo());
		else ps.setNull(3, Types.INTEGER);
		ps.setString(4, azione.getCorpo().getText());
		Timestamp t= new Timestamp(azione.getData().getTime());
		ps.setTimestamp(5,t);
		if(azione.hasDeadline()) ps.setTimestamp(6, new Timestamp(((Sollecitazione)azione).getDeadline().getTime()));
		else ps.setNull(6, Types.TIMESTAMP);
		if(azione.getCorpo().hasPlugin()) ps.setInt(7, ((Artefatto)azione).getIdPlugin());
		else ps.setNull(7,Types.INTEGER);
		ps.setString(8, azione.getStato().getState());
		ps.setInt(9, azione.getVisibilita());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			azione.setIDPost(rs.getInt("idpost"));
		rs.close();
		ps.close();
		conn.close();
		return azione;
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
			contributo.setIDUDA(rs.getInt("iduda"));
			contributo.setIDNodo(rs.getInt("idnodo"));
			contributo.setIDPartecipante(rs.getInt("idpartecipante"));
			contributo.setIDPost(rs.getInt("idpost"));			
		}
		rs.close();
		ps.close();
		conn.close();
		return contributo;
	}
	public static ArrayList<Azione> selectAllAzione(int idUDA,int idNodo) throws SQLException{
		ArrayList<Azione> contributoNodo = new ArrayList<Azione>();
		String sql ="";
		if(idNodo!=0)
		 sql = "select * from post where iduda = "+idUDA+" and idnodo = "+idNodo+"";
		else sql = "select * from post where iduda = "+idUDA+" and idnodo is null";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Azione contributo = new Post();;
			Timestamp deadline = rs.getTimestamp("deadline");
			if(deadline!=null){
				System.out.println("creo una sollecitazione");
				contributo = new Sollecitazione();
				((Sollecitazione)contributo).setDeadline(deadline);
			}
			
			contributo.setIDUDA(rs.getInt("iduda"));
			contributo.setIDNodo(rs.getInt("idnodo"));
			contributo.setIDPartecipante(rs.getInt("idpartecipante"));
			contributo.setIDPost(rs.getInt("idpost"));
			contributo.setData(rs.getTime("data"));
			Corpo testo = new Testo();
			testo.setText(rs.getString("testo"));
			contributo.setCorpo(testo);
			System.out.println("ma continua ad essere una sollecitazione? "+contributo.hasDeadline());
			contributoNodo.add(contributo);
		}
		rs.close();
		ps.close();
		conn.close();
		return contributoNodo;
	}
	
	public static Azione updateAzione(Azione azione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "update post set visibility=?, testo=?, data=?, stato=?, idplugin=? where idpost = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, azione.getVisibilita());
		ps.setString(2, azione.getCorpo().getText());
		Timestamp t= new Timestamp(azione.getData().getTime());
		ps.setTimestamp(3,t);
		ps.setString(4, azione.getStato().getState());
		if(azione.getCorpo().hasPlugin())ps.setInt(5, ((Artefatto)azione).getIdPlugin());
		else ps.setNull(5, Types.INTEGER);
		ps.setInt(6, azione.getIDPost());
		ps.executeUpdate();
		ps.close();
		conn.close();
		return azione;
	}
	public static void deleteAzione(int idAzione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "delete from post where idpost=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idAzione);
		ps.executeUpdate();
		ps.close();
		conn.close();
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
	public static Reazione selectCommento(int idCommento) throws SQLException{
		Reazione commento = new Commento();
		String sql = "select * from commento where idcommento=?";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setInt(1, idCommento);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
		
			commento.setIDCommento(rs.getInt("idcommento"));
			commento.setIDPartecipante(rs.getInt("idpartecipante"));
			commento.setIDPost(rs.getInt("idpost"));
			commento.setData(rs.getTime("data"));
			Corpo testo = new Testo();
			testo.setText(rs.getString("testo"));
			commento.setCorpo(testo);
		
		}
		rs.close();
		ps.close();
		conn.close();
		return commento;
	}
	public static ArrayList<Reazione> selectCommentiPost(int idPost) throws SQLException{
		ArrayList<Reazione> commentiPost = new ArrayList<Reazione>();
		String sql = "select * from commento where idpost=?";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setInt(1, idPost);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Reazione commento = new Commento();
			commento.setIDCommento(rs.getInt("idcommento"));
			commento.setIDPartecipante(rs.getInt("idpartecipante"));
			commento.setIDPost(rs.getInt("idpost"));
			commento.setData(rs.getTime("data"));
			Corpo testo = new Testo();
			testo.setText(rs.getString("testo"));
			commento.setCorpo(testo);
			commentiPost.add(commento);
		}
		rs.close();
		ps.close();
		conn.close();
		return commentiPost;
	}
	
	public static void deleteReazione(int idCommento) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "delete from commento where idcommento=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCommento);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	public static Reazione updateReazione(Reazione reazione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 
		String sql = "update commento set testo=?, data=?, idplugin=? where idcommento = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, reazione.getCorpo().getText());
		Timestamp t= new Timestamp(reazione.getData().getTime());
		ps.setTimestamp(2,t);
		if(reazione.getCorpo().hasPlugin())ps.setInt(3, ((Artefatto)reazione).getIdPlugin());
		else ps.setNull(3, Types.INTEGER);
		ps.setInt(4, reazione.getIDCommento());
		ps.executeUpdate();
		ps.close();
		conn.close();
				
		return reazione;
	}
	
	
	/*----------parteciapnte--------*/
	
	public static Partecipante insertUtente(Partecipante part) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "insert into utente(username,password,ruolo) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, part.getNome());
		ps.setString(2, part.getPassword());
		if(part.hasRole(Role.AMMINISTRATORE)) ps.setString(3,Role.AMMINISTRATORE.toString().toLowerCase());
		if(part.hasRole(Role.TECNICO)) ps.setString(3,Role.TECNICO.toString().toLowerCase());
		if(part.hasRole(Role.IDEATORE)) ps.setString(3,Role.IDEATORE.toString().toLowerCase());
		if(part.hasRole(Role.UTENTE)) ps.setString(3,Role.UTENTE.toString().toLowerCase());
	
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			part.setIDPartecipante(rs.getInt("idutente"));
		rs.close();
		ps.close();
		conn.close();
		return part;
		
	}
	public static Partecipante login(String username, String password) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = " select * from utente where username = ? and password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Partecipante part = new PartecipanteConcreto();
			switch(rs.getString("ruolo")){
			case "tecnico" : part = new Tecnico(part); break;
			case "amministratore" : part = new Amministratore(part); break;
			case "ideatore" : part = new Ideatore(part); break;
			case "utente" : part = new Utente(part); break;
			}
			part.setNome(rs.getString("username"));
			part.setIDPartecipante(rs.getInt("idutente"));
			System.out.println("id part"+part.getIDPartecipante());
			return part;
		}
		System.out.println("login fallito");
		ps.close();
		rs.close();
		conn.close();
		return null;
	}
	public static Partecipante selectPartecipante(int idPartecipante) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = " select * from utente where idutente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idPartecipante);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Partecipante part = new PartecipanteConcreto();
			part.setNome(rs.getString("username"));
			part.setIDPartecipante(rs.getInt("idutente"));
			return part;
		}
		ps.close();
		rs.close();
		conn.close();
		return null;
	}
	public static int numRuoli(Partecipante part, int idCorso) throws SQLException{
		int numRuolo = 0;
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select count(ruolo) as numRuolo from partecipante where idcorso=? and idutente=? group by ruolo";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCorso);
		ps.setInt(2, part.getIDPartecipante());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			numRuolo = rs.getInt("numRuolo");
		}
		rs.close();
		ps.close();
		conn.close();
		return numRuolo;
	}
	public static ArrayList<String> getRuoli(Partecipante part, int idCorso) throws SQLException{
		ArrayList<String> ruoli = new ArrayList<String>();
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select ruolo from partecipante where idcorso=? and idutente=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCorso);
		ps.setInt(2, part.getIDPartecipante());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			
			ruoli.add(rs.getString("ruolo"));
		}
		rs.close();
		ps.close();
		conn.close();
		return ruoli;
	}
	public static Partecipante decorate (Partecipante part, int idCorso) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = "select ruolo from partecipante where idcorso=? and idutente=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCorso);
		ps.setInt(2, part.getIDPartecipante());
		ResultSet rs = ps.executeQuery();
		
		if(part.isDecorated()){
			part = ((Ruolo)part).undecorate();
		}
		
		while(rs.next()){
			System.out.println(rs.getString("ruolo"));
			switch(rs.getString("ruolo")){
			case "docente" : part = new Docente(part); break;
			case "esaminatore" : part = new Esaminatore(part); break;
			case "moderatore" : part = new Moderatore(part); break;
			case "redattore" : part = new Redattore(part); break;
			case "studente" : part = new Studente(part); break;
			case "tutor" : part = new Tutor(part); break;
			default : break;
			}
		}
		if(!(part.isDecorated())) part = new Utente(part);
		ps.close();
		conn.close();
		return part;
	}
	
	public static boolean isAutorePost(int idPartecipante,int idPost) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = " select * from post where idpartecipante = ? and idpost=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idPartecipante);
		ps.setInt(2, idPost);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			return true;
		}
		ps.close();
		rs.close();
		conn.close();
		return false;
	}
	public static boolean isAutoreCommento(int idPartecipante,int idCommento) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		String sql = " select * from commento where idpartecipante = ? and idcommento=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idPartecipante);
		ps.setInt(2, idCommento);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			return true;
		}
		ps.close();
		rs.close();
		conn.close();
		return false;
	}
	
	
	/*-------valutazione------------*/
	
	public static Valutazione insertValutazione(int idRisposta,Valutazione valutazione) throws SQLException{
		Connection conn = DriverManager.getConnection(url,usr,pwd); 

		String sql = "insert into valutazione(idcommento,tipovalutazione,voto,note,visibilita) values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1,idRisposta);
		ps.setInt(2,valutazione.getTipo());
		ps.setString(3, valutazione.getVoto());
		ps.setString(4, valutazione.getNote());
		ps.setInt(5, valutazione.getVisibilit�());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		while (rs.next())
			valutazione.setIdValutazione(rs.getInt("idvalutazione"));
		rs.close();
		ps.close();
		conn.close();
		return valutazione;
	}
	//TODO
	public static ArrayList<Reazione> selectRisposteValutate(int idPost) throws SQLException{
		ArrayList<Reazione> commentiPost = new ArrayList<Reazione>();
		String sql = "select * from commento where idpost=?";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setInt(1, idPost);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Reazione commento = new Commento();
			commento.setIDCommento(rs.getInt("idcommento"));
			commento.setIDPartecipante(rs.getInt("idpartecipante"));
			commento.setIDPost(rs.getInt("idpost"));
			commento.setData(rs.getTime("data"));
			Corpo testo = new Testo();
			testo.setText(rs.getString("testo"));
			commento.setCorpo(testo);
			
			commentiPost.add(commento);
		}
		rs.close();
		ps.close();
		conn.close();
		return commentiPost;
	}

	public static ArrayList<Reazione> selectValuazioniStudente(int idPartecipante,int idUda) throws SQLException{
		ArrayList<Reazione> risposteValutate = new ArrayList<Reazione>();
		String sql = "select * from commento,valutazione where commento.idcommento = valutazione.idcommento and commento.idpartecipante=? and visibilita>1 and commento.idcommento in(select idcommento from post,commento where post.idpost = commento.idpost and post.iduda=?)";
		Connection conn = DriverManager.getConnection(url,usr,pwd);
		PreparedStatement ps =  conn.prepareStatement(sql);
		ps.setInt(2, idUda);
		ps.setInt(1, idPartecipante);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Reazione reazione = new Risposta();
			reazione.setData(rs.getTimestamp("data"));
			reazione.setIDCommento(rs.getInt("idcommento"));
			reazione.setIDPartecipante(rs.getInt("idpartecipante"));
			reazione.setIDPost(rs.getInt("idpost"));
			Valutazione valutazione;
			if(rs.getInt("tipo")==1) valutazione = new ValutazioneInt();
			else valutazione = new ValutazioneString();
			valutazione.setIdValutazione(rs.getInt("idvalutazione"));
			valutazione.setVoto(rs.getString("voto"));
			valutazione.setNote(rs.getString("note"));
			valutazione.setVisibilit�(rs.getInt("visibilita"));
			((Risposta)reazione).setValutazione(valutazione);
			risposteValutate.add(reazione);
		}
		ps.close();
		rs.close();
		conn.close();
		return risposteValutate;
	}
	
}
