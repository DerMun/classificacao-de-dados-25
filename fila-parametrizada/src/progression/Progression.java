package progression;

public abstract class Progression {
    protected long first;
    protected long cur;


    public Progression(){
        this.cur = 0;
        this.first = 0;
    }

    protected long firstValue(){
        return cur = first;
    }

    protected long nextValue(){
        return ++cur;
    }
    
    /* print the first n values */
    public abstract String printProgression(int n);
}