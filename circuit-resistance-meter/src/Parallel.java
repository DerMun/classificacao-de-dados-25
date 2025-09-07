import java.util.ArrayList;

public class Parallel extends Resistor{
	ArrayList<Circuit> circuits;

	public Parallel(){
		this.circuits = new ArrayList<>();
	}

	@Override
	public double getResistance() {
		return calculateEquivalentResistance();
	}

	//	1/Req = 1/R1 + 1/R2 + ... + 1/RN
	private double calculateEquivalentResistance(){
		if(circuits.isEmpty()) return 0;

		double req = 0;
		for(int i=0; circuits.size() > i; i++){
			req += 1.0 / circuits.get(i).getResistance();
		}

		return 1 / req;
	}
}