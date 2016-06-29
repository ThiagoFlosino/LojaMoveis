package objetos;

import java.util.ArrayList;
import java.util.List;

public class Contrato {
	
	private Long id;
	private float comissao;
	private List<ItemVenda> ItemVenda;
	
	
	public List<ItemVenda> listaItemVenda() {
		List<ItemVenda> itemVenda = new ArrayList<ItemVenda>();
		return itemVenda;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getComissao() {
		return comissao;
	}

	public void setComissao(float comissao) {
		this.comissao = comissao;
	}

	float valorContrato() {
		return 0;
	}
	
	int prazo(){
		
		return 0;
	}
	
}
