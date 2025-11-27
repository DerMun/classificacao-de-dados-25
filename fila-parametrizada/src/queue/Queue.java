package queue;

import list.List;
import list.EmptyListException;

public class Queue<T> extends List<T> implements I_Queue<T> {

    public Queue() {
        super("queue");
    }

    public Queue( String queueName ) {
        super(queueName);
    }

    public void add(T obj){
        super.insertAtBack(obj);
    }

    public T remove() throws EmptyQueueException {
        try {
            return super.removeFromFront();
        } catch (EmptyListException e){
            throw new EmptyQueueException();
        }
    }

    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }

}