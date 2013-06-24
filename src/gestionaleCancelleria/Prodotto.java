package gestionaleCancelleria;

/**
 * @author Matteo Cal�
 * Questa classe descrive il prodotto di cancelleria
 *
 */
public class Prodotto {
	private int idProdotto;
	private String nome;
	private String qta;
	private float prezzo;
	
	
	/**
	 * @param id_Prodotto Identificativo del prodotto
	 * @param nome Nome del prodotto
	 * @param quantit� Quantit� del prodotto
	 * @param prezzo Prezzo del prodotto unitario
	 */
	public Prodotto(int id_Prodotto, String nome, String quantit�, float prezzo) {
		super();
		idProdotto = id_Prodotto;
		this.nome = nome;
		this.qta = quantit�;
		this.prezzo = prezzo;
	}


	
	/**
	 * Metodi Get e Set
	 */
	public int getId_Prodotto() {
		return idProdotto;
	}


	public void setId_Prodotto(int id_Prodotto) {
		idProdotto = id_Prodotto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getQuantit�() {
		return qta;
	}


	public void setQuantit�(String quantit�) {
		this.qta = quantit�;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
