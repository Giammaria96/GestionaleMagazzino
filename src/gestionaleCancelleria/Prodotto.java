package gestionaleCancelleria;

/**
 * @author Matteo Cal�
 * Questa classe descrive il prodotto di cancelleria
 *
 */
public class Prodotto {
	private int Id_Prodotto;
	private String nome;
	private String quantit�;
	private float prezzo;
	
	
	/**
	 * @param id_Prodotto Identificativo del prodotto
	 * @param nome Nome del prodotto
	 * @param quantit� Quantit� del prodotto
	 * @param prezzo Prezzo del prodotto unitario
	 */
	public Prodotto(int id_Prodotto, String nome, String quantit�, float prezzo) {
		super();
		Id_Prodotto = id_Prodotto;
		this.nome = nome;
		this.quantit� = quantit�;
		this.prezzo = prezzo;
	}


	
	/**
	 * Metodi Get e Set
	 */
	public int getId_Prodotto() {
		return Id_Prodotto;
	}


	public void setId_Prodotto(int id_Prodotto) {
		Id_Prodotto = id_Prodotto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getQuantit�() {
		return quantit�;
	}


	public void setQuantit�(String quantit�) {
		this.quantit� = quantit�;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
