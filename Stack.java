import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable <T> {

	private LinkedList <T> stack = new LinkedList<T>();
	
	public Stack() {}
	
	public Stack(T elem)
	{
		stack.addLast(elem);
	}
	
	public int size()
	{
		return stack.size();
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public T pop()
	{
		if (isEmpty()) throw new EmptyStackException();
		
		return stack.getLast();
	}
	
	public void push(T elem)
	{
		stack.addLast(elem);
	}
	
	public T peek()
	{
		if (isEmpty()) throw new EmptyStackException();
		
		return stack.peekLast();
	}
	
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return stack.iterator();
	}
	
	@Override
	public String toString()
	{
		return stack.toString();
	}

}
