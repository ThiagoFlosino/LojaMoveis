package objetos;
import java.util.ArrayList;
import java.util.List;


public class Quarto extends Comodo{
	public long id;
	public String tipoComodo;
	public List<Mobilia> mobilias;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public List<Mobilia> getMobilias() {
		return mobilias;
	}


	public void setMobilias(List<Mobilia> mobilias) {
		this.mobilias = mobilias;
	}


	@Override
	public List<Mobilia> listaMobiliaDisponivel() {
		List<Mobilia> mobilias = new ArrayList<Mobilia>();
		return mobilias;
	}
	
}
