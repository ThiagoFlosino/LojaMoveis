package objetos;
import java.util.ArrayList;
import java.util.List;

public class Ambiente {
	
	private Long id;
	private int numParedes;
	private int numPortas;
	private float metragem;
	private List<ItemVenda> ItemVenda;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumParedes() {
		return numParedes;
	}

	public void setNumParedes(int numParedes) {
		this.numParedes = numParedes;
	}

	public int getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(int numPortas) {
		this.numPortas = numPortas;
	}

	public float getMetragem() {
		return metragem;
	}

	public void setMetragem(float metragem) {
		this.metragem = metragem;
	}

	public List<ItemVenda> listaItemVenda() {
		List<ItemVenda> itemVenda = new ArrayList<ItemVenda>();
		return itemVenda;
	}
	
	float custo(){
		
		return 0;
	}
	
}
