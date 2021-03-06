package gestionale.magazzino.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionale.magazzino.Querist;

public class Dipendente {
	static Querist que;

	/**
	 * 
	 * @param nome nome del dipendente
	 * @param cognome cognome del dipendente
	 * @param pass password del dipendente
	 * @param email email del dipendente
	 * @param tipo tipo del dipendente
	 */
	static public void inserisciDipendente(String nome, String cognome, String pass, String email, String tipo){
		que = new Querist();
		String query = "INSERT INTO Dipendente (nome,cognome,email,password,tipo,isActive) VALUES"+
				"('"+nome+"','"+cognome+"','"+email+"','"+pass+"','"+tipo+"','"+true+"')";
		//System.out.println(query);
		que.eseguiQueryUpdate(query);

	}
	
	/**
	 * Il metodo verifica le occorrenze dell'email fornita, per essere valida le occorrenze non devono essere presenti
	 * @param email l'email da confrontare
	 * @return ritorna una boolean coerente con la validita della email inserita
	 */
	static public boolean validateEmail (String email){
		que = new Querist();
		boolean valida = false;
		
		String query = "SELECT COUNT(D.email) AS occorrenze " +
					   "FROM Dipendente AS D " +
					   "WHERE D.email = '"+email+"'";
		ResultSet rs = que.eseguiQuery(query);
		int risultato = 0;

		try {
				risultato = rs.getInt("occorrenze");
				if(risultato == 1){
					valida = true;	
			}
		} catch (SQLException e) {
			valida = false;
		}
		return valida;
	}

	/**
	 * Questo metodo verifica la validita della password associata ad un email.
	 * @param email l'email da confrontare in combinazione con la password
	 * @param password la password da confrontare in combinazione con l'email
	 * @return true o false in base alla validitÓ della password associata all'email
	 */
	static public boolean validatePassword (String email, String password){
		boolean valida = false;
		que = new Querist();
		String query = "SELECT COUNT(D.email) AS occorrenze " +
				"FROM Dipendente D " +
				"WHERE D.email = '"+email+"' AND D.password = '"+ password +"'";
		ResultSet rs = que.eseguiQuery(query);
		int risultato = 0;

		try {
			risultato = rs.getInt("occorrenze");
			if(risultato == 1 && isActive(email)){
				valida = true;	 		
			}
		} catch (SQLException e) {
			valida = false;
		}
		return valida;
	}
	
	/**
	 * 
	 * @param email email del responsabile
	 * @param password password del responsabile
	 * @return ritorna la validitÓ del ruolo associata all'utente
	 */
	static public boolean validateResponsabile (String email, String password){
		boolean valida = false;
		que = new Querist();
		String query = "SELECT D.Tipo " +
				   	   "FROM Dipendente D " +
				   	   "WHERE D.email = '"+email+"' AND D.password = '"+ password +"' AND D.Tipo = 'responsabile'";
		System.out.println(query);
		ResultSet rs = que.eseguiQuery(query);
		String risultato = "";
		try {
			while(rs.next()){
				risultato = rs.getString("tipo");
				if(risultato.equals("responsabile")){
					valida = true;
				} 		
			}
		} catch (SQLException e) {
			valida = false;
		}
		return valida;
	}

	/**
	 * Questo metodo disattiva un utente in base all'email
	 * @param email email dell'utente da disattivare
	 */
	static public void disattivaDipendente(String email){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET isActive = 'false' " +
					   "WHERE email = '"+email+"'";
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	/**
	 * Questo metodo attiva un utente in base all'email
	 * @param email email dell'utente da attivare
	 */
	static public void attivaDipendente(String email){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET isActive = 'true' " +
					   "WHERE email = '"+email+"'";
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}

	/**
	 * Questo metodo restituisce tutti i prodotti in database
	 * @return ritorna un arraylist di prodotti, in caso di errore un null
	 */
	static public ArrayList<gestionale.magazzino.Dipendente> visualizzaDipendenti(){
		que = new Querist();
		ArrayList<gestionale.magazzino.Dipendente> risultato = new ArrayList<gestionale.magazzino.Dipendente>();
		String query = "SELECT * FROM Dipendente";
		ResultSet rs = que.eseguiQuery(query);
		try {
			while(rs.next()){
				boolean isActive = Boolean.parseBoolean(rs.getString("isActive"));
				gestionale.magazzino.Dipendente dip = new gestionale.magazzino.Dipendente(rs.getInt("idDipendente"),rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("password"), rs.getString("tipo"), isActive);
				risultato.add(dip);
			}
		} catch (SQLException e) {
			gestionale.magazzino.Dipendente dip = null;
			risultato.add(dip);
			return risultato;
		}
		return risultato; 
	}

	/**
	 * 
	 * @param email email del dipendente di cui verificare l'attivazione o meno
	 * @return un valore boolean in base all'attivazione dell'utente
	 */
	static public boolean isActive(String email){
		boolean isActive = false;
		que = new Querist();
		String query = "SELECT D.isActive " +
				"FROM Dipendente D " +
				"WHERE D.email = '"+email+"'";
		ResultSet rs = que.eseguiQuery(query);
		System.out.println(query);
		try {
			while(rs.next()){
				isActive = Boolean.parseBoolean(rs.getString("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isActive;
	}
	
	/**
	 * Questo metodo restituisce la descrizione di un dipendente.
	 * @param idDipendente identificativo del dipendente di cui si vogliono visualizzare i dati
	 * @return un Dipendente con i dati relativi
	 */
	static public gestionale.magazzino.Dipendente visualizzaDipendente(String email,String password){
		que = new Querist();
		String query = "SELECT * " +
					   "FROM Dipendente D " +
					   "WHERE D.email = '"+email+"' AND D.password = '"+ password +"'";
		ResultSet rs = que.eseguiQuery(query);
		System.out.println(query);
		gestionale.magazzino.Dipendente dip;
		try{
			boolean isActive = Boolean.parseBoolean(rs.getString("isActive"));
			dip = new gestionale.magazzino.Dipendente(rs.getInt("idDipendente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("password"), rs.getString("tipo"), isActive);
		}catch(SQLException e){
			dip = null;
		}
		
		return dip;
	}
	
	/**
	 * Questo metodo visualizza un singolo dipendente da visualizzare
	 * @param idDipendente identificativo del dipendente da visualizzare
	 * @return un oggetto Dipendente
	 */
	static public gestionale.magazzino.Dipendente visualizzaDipendente(int idDipendente){
		que = new Querist();
		String query = "SELECT * " +
					   "FROM Dipendente D " +
					   "WHERE D.idDipendente = "+idDipendente;
		ResultSet rs = que.eseguiQuery(query);
		System.out.println(query);
		gestionale.magazzino.Dipendente dip;
		try{
			boolean isActive = Boolean.parseBoolean(rs.getString("isActive"));
			dip = new gestionale.magazzino.Dipendente(rs.getInt("idDipendente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("password"), rs.getString("tipo"), isActive);
		}catch(SQLException e){
			dip = null;
		}

		return dip;
	}
	
	/**
	 * Questo metodo cancella un dipendente dal database
	 * @param idDipendente identificativo del dipendente da cancellare
	 */
	static public void cancellaDipendente(int idDipendente){
		que = new Querist();
		String query = "DELETE " +
				       "FROM Dipendente " +
				       "WHERE Dipendente.idDipendente = '"+idDipendente+"'";
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	public static void reindexTable(){
		que = new Querist();
		String query = "REINDEX 'Dipendente'";
		que.eseguiQueryUpdate(query);
	}

	/**
	 * Questo metodo modificherÓ il tipo di dipendente in responsabile
	 * @param idDipendente identificativo del dipendente
	 */
	public static void setResponsabile(int idDipendente){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET tipo = 'responsabile' " +
					   "WHERE idDipendente = "+idDipendente;
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	/**
	 * Questo metodo modificherÓ il tipo di responsabile in dipendente
	 * @param idDipendente identificativo del dipendente
	 */
	public static void setDipendente(int idDipendente){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET tipo = 'dipendente' " +
					   "WHERE idDipendente = "+idDipendente;
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	/**
	 * Questo metodo permette di cambiare l'email di un dipendente
	 * @param idDipendente identificativo del dipendente
	 * @param email nuova email
	 */
	public static void cambiaEmail (int idDipendente, String email){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET email = '" +email+"' "+
					   "WHERE idDipendente = "+idDipendente;
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	/**
	 * Questo metodo permette di cambiare il cognome di un dipendente
	 * @param idDipendente identificativo del dipendente
	 * @param cognome nuovo cognome
	 */
	public static void cambiaCognome (int idDipendente, String cognome){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET cognome = '" +cognome+"' "+
					   "WHERE idDipendente = "+idDipendente;
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
	
	/**
	 * Questo metodo permette di cambiare il nome di un dipendente
	 * @param idDipendente identificativo del dipendente
	 * @param nome nuovo nome
	 */
	public static void cambiaNome (int idDipendente, String nome){
		que = new Querist();
		String query = "UPDATE Dipendente " +
					   "SET nome = '" +nome+"' "+
					   "WHERE idDipendente = "+idDipendente;
		System.out.println(query);
		que.eseguiQueryUpdate(query);
	}
}
