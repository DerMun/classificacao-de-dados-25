import java.util.ArrayList;

public class Serial extends Resistor{
	ArrayList<Circuit> circuits;

	public Serial(){
		this.circuits = new ArrayList<>();
	}

	@Override
	public double getResistance(){
		return calculateEquivalentResistance();
	}

	//	Req = R1 + R2 + ... + RN;
	private double calculateEquivalentResistance(){
		if(circuits.isEmpty()) return 0;

		double req = 0;
		for(int i=0; circuits.size() > i; i++){
			req += circuits.get(i).getResistance();
		}

		return req;
	}
}