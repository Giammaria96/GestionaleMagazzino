package modelsCancelleria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionaleCancelleria.Querist;

public class Aggiornamento {
	Querist que;
	
	/**
	 * La query non vede inserita la data in quanto in fase di progettazione 
	 * del database � stata impostata CURRENT_TIMESTAMP
	 * @param idDipendente id del dipendente
	 * @param idProdotto id del prodotto
	 * @param qta quantit� aggiunta
	 */
	public void inserisciAggiornamento(int idDipendente, int idProdotto, int qta){
		String query = "INSERT INTO Aggiornamento(idDipendente,idProdotto,qta) VALUES"+
				"("+idDipendente+","+idProdotto+","+qta+")";
		que.eseguiQueryUpdate(query);
	}
}
