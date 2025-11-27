package progression;

public class GeomProgression extends Progression {
    protected long base;


    public GeomProgression(){
        this(2);
    }

    public GeomProgression(long base){
        this.base = base;
        this.first = 1;
        this.cur = first;
    }

    protected long nextValue(){
        return cur *= base;
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