package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCentralino {
	private ArrayList<DBGruppo> gruppi;
	private ArrayList<DBListaNumeriTelefonici> liste;
	private ArrayList<DBCentralinista> centralinisti;
	private ArrayList<DBAppuntamento> appuntamenti;
	private ArrayList<DBTelefonata> telefonate;
	
	public DBCentralino() {
		super();
	}
	
	public static int trovaLista(int id) {
		int ret = 0;
		
		String query = "SELECT * FROM listenumeritelefonici where id='"+id+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int ottieniLatestIDLista() {
		int ret = 0;
		
		String query = "SELECT MAX(id) FROM listenumeritelefonici;";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=rs.getInt(1)+1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int trovaGruppo(int id) {
		int ret = 0;
		
		String query = "SELECT * FROM gruppi where id='"+id+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int ottieniLatestIDGruppo() {
		int ret = 0;
		
		String query = "SELECT MAX(id) FROM gruppi;";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=rs.getInt(1)+1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int trovaCentralinista(int idCentralinista) {
		int ret = 0;
		
		String query = "SELECT * FROM centralinisti where id='"+idCentralinista+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int trovaAgente(String CF) {
		int ret = 0;
		
		String query = "SELECT * FROM agentidivendita where codicefiscale='"+CF+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static ArrayList<String> agentiDisponibili(){
		ArrayList<String> ret = new ArrayList<String>();
		
		try {
			
			ResultSet rs = DBConnectionManager.selectQuery("select codicefiscale from agentidivendita;");
			
			while(rs.next()) {
				
				ret.add(rs.getString("codicefiscale"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static int liberaTutti(int idGruppo) {
		int ret=0;
		String query = "UPDATE centralinisti SET gruppo=NULL WHERE gruppo='"+idGruppo+"';";
		try {
			ret = DBConnectionManager.updateQuery(query);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public static boolean checkListaAssegnata(int idLista) {
		boolean check = true;
		
		String query = "SELECT * FROM gruppi WHERE lista='"+idLista+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(!rs.next()) {
				check=false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static int trovaAppuntamento(int id) {
		int ret = 0;
		
		String query = "SELECT * FROM appuntamenti WHERE idappuntamenti='"+id+"';";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int ottieniLatestIDAppuntamento() {
		int ret = 0;
		
		String query = "SELECT MAX(idappuntamenti) FROM appuntamenti;";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=rs.getInt(1)+1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	public static int ottieniLatestIDTelefonata() {
		int ret = 0;
		
		String query = "SELECT MAX(id) FROM telefonate;";
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			if(rs.next()) {
				ret=rs.getInt(1)+1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}
	
	

	public ArrayList<DBGruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(ArrayList<DBGruppo> gruppi) {
		this.gruppi = gruppi;
	}

	public ArrayList<DBListaNumeriTelefonici> getListe() {
		return liste;
	}

	public void setListe(ArrayList<DBListaNumeriTelefonici> liste) {
		this.liste = liste;
	}

	public ArrayList<DBCentralinista> getCentralinisti() {
		return centralinisti;
	}

	public void setCentralinisti(ArrayList<DBCentralinista> centralinisti) {
		this.centralinisti = centralinisti;
	}

	public ArrayList<DBAppuntamento> getAppuntamenti() {
		return appuntamenti;
	}

	public void setAppuntamenti(ArrayList<DBAppuntamento> appuntamenti) {
		this.appuntamenti = appuntamenti;
	}

	public ArrayList<DBTelefonata> getTelefonate() {
		return telefonate;
	}

	public void setTelefonate(ArrayList<DBTelefonata> telefonate) {
		this.telefonate = telefonate;
	}
	
	
	
}
