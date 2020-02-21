import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyArray <T> implements Iterable <T> 
{
	private T[] arr;
	private int length; //Length showed to the user
	private int capacity; //Actual capacity of the MyArray

	public MyArray()
	{
		this(16);
	}

	public MyArray(int capacity)
	{
		if (capacity <= 0) throw new IllegalArgumentException("Illegal capacity for array");
		this.capacity = capacity;
		this.length = 0;
		arr = (T[]) new Object[capacity];
	}

	public int size() {return length;}

	public boolean isEmpty() {return length == 0;}

	public T get(int index) 
	{
		if (index >= length || index < 0) throw new IllegalArgumentException("Index does not exist");
		return arr[index];
	}

	public void set(int index, T element)
	{
		if (index >= length || index < 0) throw new IllegalArgumentException("Index does not exist");
		arr[index] = element;
	}

	public void clear()
	{
		for (int i = 0; i < length; i++)
		{
			arr[i] = null;
		}
		length = 0;
	}

	// Append element
	public void add(T element)
	{
		if (length == capacity) 
		{
			// need to expand array
			if (capacity == 0)
				capacity = 1;
			else
				capacity *= 2;
			T[] newArray = (T[]) new Object[capacity];
			for (int i = 0; i < length; i++)
			{
				newArray[i] = arr[i];
			}
			arr = newArray;
		}
		arr[length++] = element; 
	}

	public T removeAt(int index)
	{
		
		if (index >= length || index < 0) throw new IllegalArgumentException("Index does not exist");
		T removedElement = arr[index];
		//make new smaller array
		T[] newArray = (T[]) new Object[length-1];
		for (int i = 0, j = 0; i < length; i++, j++)
		{
			if (j == index && j == i)
			{
				j--;
			}
			else
				newArray[j] = arr[i];
		}
		arr = newArray;
		length--;
		capacity = length;
		return removedElement;
	}
	
	public T remove(Object object)
	{
		T removedObject = null;
		
		for (int i = 0; i < length; i++)
		{
			if (arr[i].equals(object))
				removedObject = removeAt(i);
				return removedObject;
		}
		return null;
	}
	
	public int indexOf(Object object)
	{
		for (int i = 0; i < length; i++)
		{
			if (arr[i].equals(object))
				return i;
		}
		return -1;
	}
	
	public boolean contains(Object object)
	{
		for (int i = 0; i < length; i++)
		{
			if (arr[i].equals(object))
				return true;
		}
		return false;
	}
	

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			public boolean hasNext() {
				return index < length;
			}			
			public T next() {
				return arr[index++];
			}
		};
	}

	public String toString()
	{
		String string = "";
		if (length == 0)
		{
			return "[]";
		}
		string += "[";
		for (int i = 0; i < length; i++)
		{
			string += "" +  arr[i];
			if (i!=0)
				string += ", ";
		}
		string += "]";
		return string;
	}

}
