import java.util.Iterator;

public class DoubleLinkedList <T> implements Iterable <T> {

	private int size;
	private Node <T> head, tail;
	private Node <T> trav;
	
	public DoubleLinkedList()
	{
		size = 0;
		head = tail = trav = null;
	}
	
	private class Node <T> {
		private T data;
		private Node <T> previous, next;
		
		public Node (T elem, Node previous, Node next)
		{
			data = elem;
			this.previous = previous;
			this.next = next;
		}
		
		@Override
		public String toString()
		{
			return data.toString();
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public void clear()
	{
		if (!isEmpty())
		{
			trav = head;
			while (trav != null)
			{
				Node<T> nextNode = trav.next;
				trav.previous = null;
				trav.next = null;
				trav.data = null;
				trav = nextNode;
			}
			// reset head and tail pointers
			head = tail = trav = null;
			size = 0;
		}
	}
	
	public void add(T elem)
	{
		addLast(elem);
	}
	
	public void addFirst(T elem)
	{
		if (isEmpty())
		{
			head = tail = new Node<T>(elem, null, null);
		}
		else
		{
			trav = head;
			head = new Node<T>(elem, null, trav);
			trav.previous = head;
		}
		size++;
	}
	
	public void addLast(T elem)
	{
		if(isEmpty())
		{
			head = tail = new Node<T>(elem, null, null);
		}
		else
		{
			tail.next = new Node<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	public T peekFirst()
	{
		if (isEmpty()) throw new RuntimeException("List is empty");
		
		return head.data;
	}
	
	public T peekLast()
	{
		if (isEmpty()) throw new RuntimeException("List is empty");
		
		return tail.data;
	}
	
	public T removeFirst()
	{
		if (isEmpty()) throw new RuntimeException("List is empty");
		T removedData = head.data;
		trav = head;
		head = head.next;
		head.previous = null;
		trav.next = null;
		trav.data = null;
		trav = null;
		size--;
		if (isEmpty())
		{
			tail = null;
		}
		return removedData;
	}
	
	public T removeLast()
	{

		if (isEmpty()) throw new RuntimeException("List is empty");
		T removedData = tail.data;
		trav = tail;
		tail = tail.previous;
		tail.next = null;
		trav.previous = null;
		trav.data = null;
		trav = null;
		size--;
		if (isEmpty())
		{
			head = null;
		}
		return removedData;
	}
	
	private T remove(Node<T> node)
	{
		if (isEmpty()) throw new RuntimeException("List is empty");
		T removedData = node.data;
		if (node.previous == null)	{removeFirst();}
		if (node.next == null)	{removeLast();}
		
		node.next.previous = node.previous;
		node.previous.next = node.next;
		node.data = null;
		size--;
		
		return removedData;
	}
	
	public T removeAt(int index)
	{
		if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid index");
		
		if (index == 0) return removeFirst();
		if (index == size - 1) return removeLast();
		
		if (index < size/2)
		{
			trav = head;
			for (int i = 0; i < index; i++)
			{
				trav = trav.next;
			}
		}
		else
		{
			trav = tail;
			for (int i = size - 1; i > index; i-- )
			{
				trav = trav.previous;
			}
		}
		return remove(trav);
	}
	
	public boolean removeObj(Object obj)
	{
		if (isEmpty()) return false;
		
		trav = head;
		if (obj == null)
		{
			while(trav != null)
			{
				if (trav.data == null) 
				{
					remove(trav);
					return true;
				}
				trav = trav.next;
			}
		}
		else
		{
			while(trav != null)
			{
				if (trav.data.equals(obj)) 
				{
					remove(trav);
					return true;
				}
				trav = trav.next;
			}
		}
		
		return false;
	}
	
	public int indexOf(Object obj)
	{
		if (isEmpty()) return -1;
		trav = head;
		int index = 0;
		if (obj == null)
		{
			while(trav != null)
			{
				if (trav.data == null) 
				{
					return index;
				}
				index++;
				trav = trav.next;
			}
		}
		else
		{
			while(trav != null)
			{
				if (trav.data.equals(obj)) 
				{
					return index;
				}
				index++;
				trav = trav.next;
			}
		}
		
		return -1;
	}
	
	public boolean contains(Object obj)
	{
		return indexOf(obj) != -1;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new java.util.Iterator <T>() {
			Node<T> trav = head;
			@Override
			public boolean hasNext()
			{
				return trav.next != null;
			}
			
			public T next()
			{
				return trav.data;
			}
		};
	}
	
	@Override
	public String toString()
	{
		String string = "";
		if (isEmpty()) return "[]";
		
		string += "[";
		trav = head;
		string += trav.data;
		trav = trav.next;
		while (trav != null)
		{
			string += ", " + trav.data.toString();
			trav = trav.next;
		}
		string += "]";
		
		return string;
	}

}
