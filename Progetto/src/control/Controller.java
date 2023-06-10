package control;

import entity.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import exceptions.*;
public class Controller {
	
	public static int trovaLista(int id) throws ListaNonTrovata{
		int ret=0;
		//EntityListaNumeriTelefonici l = new EntityListaNumeriTelefonici();
		//ret = l.trovaLista(id);
		EntityCentralino c = new EntityCentralino();
		ret = c.trovaLista(id);
		if(!(ret>0)) {
			throw new ListaNonTrovata();
		}
		return ret;
		
	}
	
	public static int creaLista(String nome) {
		int ret=-1;
		//EntityListaNumeriTelefonici lista = new EntityListaNumeriTelefonici();
		//int id=lista.ottieniLatestID();
		
		EntityCentralino c = new EntityCentralino();
		int id = c.ottieniLatestIDLista();
		EntityListaNumeriTelefonici lista = new EntityListaNumeriTelefonici();
		ret = lista.ScriviSuDB(id, nome);
		if(ret>0) {
			//lista.setId(id);
			//lista.setNome(nome);
			ret=id;
		}
		return ret;
	}
	
	
	
	public static int aggiungiNumero(int idLista, String numero) {
		int ret=0;
			
		EntityListaNumeriTelefonici l = new EntityListaNumeriTelefonici(idLista);
			
		EntityNumeroTelefonico n = new EntityNumeroTelefonico(numero);
			
		if(!l.verificaPresenza(n)) {
			ret = n.aggiungiNumero(idLista, numero);
		} else {
			ret = -1;
		}
		return ret;
	}
	
	public static int rimuoviNumero(int idLista, String numero) {
		int ret=0;
		
		EntityListaNumeriTelefonici l = new EntityListaNumeriTelefonici(idLista);
		EntityNumeroTelefonico n = new EntityNumeroTelefonico(numero);
			
		if(l.verificaPresenza(n)) {
			ret = n.rimuoviNumero();
		}
		
		return ret;
	}
	
	public static int trovaGruppo(int id) throws GruppoNonTrovato{
		int ret=0;
		//EntityGruppo g = new EntityGruppo();
		//ret = g.trovaGruppo(id);
		
		EntityCentralino c = new EntityCentralino();
		ret = c.trovaGruppo(id);
		if(!(ret>0)) {
			throw new GruppoNonTrovato();
		}
		return ret;
	}
	
	public static int creaGruppo(String descrizione) {
		int ret=0;
		int id=0;
		
		EntityCentralino c = new EntityCentralino();
		id = c.ottieniLatestIDGruppo();
		
		EntityGruppo g = new EntityGruppo();
		
		ret = g.scriviSuDB(id,descrizione);
		if(ret>0) {
			ret=id;
		}
		return ret;
	}
	
	public static int inserisciCentralinistaGruppo(int idGruppo, int idCentralinista) {
		int ret=0;
		EntityCentralinista c = new EntityCentralinista();
		ret = c.assegnaGruppo(idGruppo, idCentralinista);
		return ret;
	}
	
	public static int rimuoviGruppo(int id) {
		int ret=0;
		
		EntityCentralino c = new EntityCentralino();
		if(c.liberaTutti(id)>0) {
			EntityGruppo g = new EntityGruppo();
			ret = g.rimuoviDaDB(id);
		}
		
		return ret;
	}
	
	public static int assegnaListaGruppo(int idLista, int idGruppo) {
		int ret=0;
		EntityCentralino c = new EntityCentralino();
		EntityGruppo g = new EntityGruppo();
		if(!(c.checkListaAssegnata(idLista))) {
			ret=g.assegnaLista(idLista, idGruppo);
		} else {
			System.out.println("Lista già assegnata!");
		}
		
		return ret;
	}
	
	public static ArrayList<EntityAppuntamento> ottieniAppuntamenti(String cf) {
		EntityAgentediVendita a = new EntityAgentediVendita(cf);
		return a.getAppuntamenti();
	}
	
	public static String visualizzaNoteChiamata(String cf, int idChiamata) {
		EntityAgentediVendita a = new EntityAgentediVendita(cf);
		ArrayList<EntityAppuntamento> l = a.getAppuntamenti();
		for(int i=0; i<l.size(); i++) {
			if(l.get(i).getTelefonata().getId()==idChiamata) {
				return l.get(i).getTelefonata().getNote();
			}
		}
		return "Chiamata non trovata";
	}
	
	
	public static String visualizzaDettagliAppuntamento(String cf, int idAppuntamento) {
		EntityAgentediVendita a = new EntityAgentediVendita(cf);
		ArrayList<EntityAppuntamento> l = a.getAppuntamenti();
		for(int i=0; i<l.size(); i++) {
			if(l.get(i).getId()==idAppuntamento) {
				return l.get(i).toString();
			}
		}
		return "Non Trovato";
		
	}
	
	public static String ottieniNoteAppuntamento(String cf, int idAppuntamento) {
		String s="";
		EntityAgentediVendita a = new EntityAgentediVendita(cf);
		ArrayList<EntityAppuntamento> l = a.getAppuntamenti();
		for(int i=0; i<l.size(); i++) {
			if(l.get(i).getId()==idAppuntamento) {
				s = l.get(i).getNote();
			}
		}
		return s;
	}
	
	public static int modificaNoteAppuntamento(String cf, int idAppuntamento, String nuoveNote) {
		int ret=0;
		EntityAgentediVendita a = new EntityAgentediVendita(cf);
		ArrayList<EntityAppuntamento> l = a.getAppuntamenti();
		for(int i=0; i<l.size(); i++) {
			if(l.get(i).getId()==idAppuntamento) {
				l.get(i).setNote(nuoveNote);
				ret = l.get(i).aggiornaSuDB();
			}
		}
		
		return ret;
	}
	
	public static ArrayList<EntityNumeroTelefonico> numeriDaChiamare(int idCentralinista) {
		
		EntityCentralinista c = new EntityCentralinista(idCentralinista);
		EntityListaNumeriTelefonici l = new EntityListaNumeriTelefonici(c.getGruppo().getLista().getId());
		ArrayList<EntityNumeroTelefonico> nums = l.getNumeri();
		return nums;
	}
	
	public static int registraEsitoChiamata(String data, String ora, String note, int esito, int idCentralinista) {
		
		int ret=0;
		EntityCentralino c = new EntityCentralino();
		int id = c.ottieniLatestIDTelefonata();
		EntityTelefonata t = new EntityTelefonata();
		t.setId(id);
		t.setData(data);
		t.setOra(ora);
		t.setNote(note);
		t.setEsito(esito);
		EntityCentralinista ce = new EntityCentralinista(idCentralinista);
		t.setCentralinista(ce);
		
		ret = t.salvaInDB();
		
		if(ret>0) {
			ret=id;
		}
		return ret;
	}
	
	public static int creaAppuntamento(String data, String ora, String note, int esito, String agente, int idTelefonata) {
		int ret=0;
		EntityCentralino c = new EntityCentralino();
		int id = c.ottieniLatestIDAppuntamento();
		EntityAppuntamento a = new EntityAppuntamento();
		a.setId(id);
		a.setData(data);
		a.setOra(ora);
		a.setNote(note);
		a.setEsito(esito);
		a.setAgente(new EntityAgentediVendita(agente));
		a.setTelefonata(new EntityTelefonata(idTelefonata));
		
		ret = a.salvaInDB();
		
		if(ret>0) {
			ret=id;
		}
		
		return ret;
	}
	
	public static int trovaAppuntamento(int id) {
		int ret=0;
		
		//EntityAppuntamento a = new EntityAppuntamento();
		//ret = a.trovaAppuntamento(id);
		
		EntityCentralino c = new EntityCentralino();
		ret = c.trovaAppuntamento(id);
		
		return ret;
	}
	
	public static int referenziaAppuntamento(int idVecchio, int idNuovo) {
		int ret=0;
		
		EntityAppuntamento a = new EntityAppuntamento(idNuovo);
		EntityAppuntamento b = new EntityAppuntamento(idVecchio);
		if(b.getEsito()==1) {
			a.setPrecedente(idVecchio);
			ret = a.referenziaInDB();
		} //else throw AppuntamentoNonFallito
		
			
		
		return ret;
	}
	
	public static boolean isDataValida(String dataStringa) throws DataNonValida{
        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false); // Impedisce la conversione di date non valide

        try {
            formatoData.parse(dataStringa);
            return true;
        } catch (ParseException e) {
            throw new DataNonValida();
        }
    }
	public static boolean isOraValida(String oraStringa) throws OraNonValida {
        DateFormat formatoOra = new SimpleDateFormat("HH:mm");
        formatoOra.setLenient(false); // Impedisce la conversione di orari non validi

        try {
            formatoOra.parse(oraStringa);
            return true;
        } catch (ParseException e) {
            throw new OraNonValida();
        }
    }
	
	public static boolean isNumeroTelefonoValido(String numeroTelefono) throws NumeroNonValido{
        // Definisci l'espressione regolare per un numero di telefono a 10 cifre
        String regex = "^\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroTelefono);

        if(matcher.matches()) {
        	return true;
        } else {
        	throw new NumeroNonValido();
        }
    }
	
}