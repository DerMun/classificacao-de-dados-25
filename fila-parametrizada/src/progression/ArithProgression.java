package progression;

public class ArithProgression extends Progression {
    protected long inc;


    public ArithProgression(){
        this(1);
    }

    public ArithProgression(long inc){
        this.inc = inc;
    }

    protected long nextValue(){
        return cur += inc;
    }

    @Override
    public String printProgression(int n){
        StringBuilder sb = new StringBuilder("" + firstValue());

        for(int i = 0; i < n-1; i++){
            sb.append(" " + nextValue());
        }

        return sb.toString();
    }
}