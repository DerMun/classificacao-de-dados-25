package progression;

public class FibonacciProgression extends Progression {
    protected long prev;


    public FibonacciProgression(){
        this(0,1);
    }

    public FibonacciProgression(long v1, long v2){
        this.first = v1;
        this.prev = v2 - v1;
    }

    protected long nextValue(){
        long temp = prev;
        this.prev = cur;
        this.cur += temp;
        return cur;
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