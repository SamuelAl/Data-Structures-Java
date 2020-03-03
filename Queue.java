import java.util.Iterator;

public class Queue<T> implements Iterable<T>{

	private java.util.LinkedList<T> queue = new java.util.LinkedList<T>();
	
	public Queue()
	{
		
	}
	
	public Queue(T firstElement)
	{
		queue.addFirst(firstElement);
	}
	
	public int size()
	{
		return queue.size();
	}
	
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
	
	public T peekFirst()
	{
		if (isEmpty()) throw new RuntimeException("Empty Queue");
		return queue.peekLast();
	}
	
	public T poll()
	{
		if (isEmpty()) throw new RuntimeException("Empty Queue");
		return queue.getLast();
	}
	
	public void offer(T element)
	{
		queue.addFirst(element);
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return queue.listIterator();
	}

}
