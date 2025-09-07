public class Resistor extends Circuit{
	private double resistance;

	public void setResistance(double resistance){
		this.resistance = resistance;
	}

	@Override
	public double getResistance(){
		return resistance;
	}
}