package queue;

public interface I_Queue<T> {
    public void add(T obj);
    public T remove() throws EmptyQueueException;
    public boolean isEmpty();
}