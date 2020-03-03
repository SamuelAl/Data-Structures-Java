import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class PQueue<T extends Comparable<T> > {
	
	private int heapSize = 0;
	private int heapCapacity = 0;
	
	private List<T> heap;
	
	private Map<T, TreeSet<Integer>> map = new HashMap<T, TreeSet<Integer>>();
	
	public PQueue()
	{
		this(1);
	}
	
	public PQueue(int size)
	{
		heap = new ArrayList<T>(size);
		heapCapacity = size;
	}
	
	public PQueue(T[] elements)
	{
		heapSize = heapCapacity = elements.length;
		heap = new ArrayList<T>(heapCapacity);
		
		for (int i = 0; i < heapSize; i++)
		{
			mapAdd(elements[i], i);
			heap.add(elements[i]);
		}
		
		for (int i = Math.max(0, (heapSize/2)-1); i >= 0; i--)
				sink(i);
	}
	
	public PQueue(Collection<T> elements)
	{
		for (T element : elements)
			add(element);
	}
	
	public boolean isEmpty()
	{
		return heapSize == 0;
	}
	
	public void clear()
	{
		for (int i = 0; i < heapCapacity; i++)
		{
			heap.set(i, null);
		}
		map.clear();
		heapSize = 0;
	}
	
	public int size()
	{
		return heapSize;
	}
	
	public T peek()
	{
		if (isEmpty()) throw new RuntimeException("Empty PQueue");
		return heap.get(0);
	}
	
	public T poll()
	{
		return removeAt(0);
	}
	
	public boolean contains(T element)
	{
		if (element == null) return false;
		return map.containsKey(element);
	}
	
	public void add(T element)
	{
		if (heapSize < heapCapacity)
		{
			heap.set(heapSize, element);
		}
		else
		{
			heap.add(element);
			heapCapacity++;
		}
		
		mapAdd(element, heapSize);
		swim(heapSize);
		heapSize++;
	}
	
	private boolean less(int i, int j)
	{
		if (i >= heapSize || j >= heapSize) throw new IllegalArgumentException();
		T element1 = heap.get(i);
		T element2 = heap.get(j);
		
		return element1.compareTo(element2) <= 0;
	}
	

	private void swim(int index) 
	{
		
		int parent = (index-1) / 2;
		
		while (index > 0 && less(index, parent))
		{
			swap(index, parent);
			index  = parent;
			parent = (index-1) / 2;
		}
		
	}
	
	private void sink(int index) 
	{
		
		while (true)
		{
			int left = (2 * index) + 1;
			int right = (2 * index) + 2;
			
			int smallest = left;
			
			if (right < heapSize && less(right, left))
				smallest = right;
			
			if (left < heapSize && less(smallest, index))
			{
				swap(index, smallest);
				index = smallest;
			}
			else break;
		}
	}


	private void swap(int index1, int index2) 
	{
		T element1 = heap.get(index1);
		T element2 = heap.get(index2);
		
		heap.set(index1, element2);
		heap.set(index2, element1);
		
		mapSwap(element1, element2, index1, index2);
		
	}


	public boolean remove(T element)
	{
		Integer index = mapGet(element);
		if (index != null) removeAt(index);
		return index != null;
	}
	
	public T removeAt(int index) {
		
		if(isEmpty()) throw new IllegalArgumentException();
		
		T removedElement = heap.get(index);
		heapSize--;
		swap(index, heapSize);
		heap.set(heapSize, null);
		mapRemove(removedElement, heapSize);
		
		if (index == heapSize) return removedElement;
		
		T element = heap.get(index);
		sink(index);
		if (heap.get(index).equals(element))
			swim(index);
		
		return removedElement;
		
	}

	private boolean isMinHeap(int index)
	{
		if (index >= heapSize) return true;
		
		int left = (2*index) + 1;
		int right = (2*index) + 2;
		
		if (left < heapSize && !less(index, left)) return false;
		if (right < heapSize && !less(index, right)) return false;
		
		return isMinHeap(left) && isMinHeap(right);
	}
	
	
	private void mapAdd(T element, int index) 
	{
		TreeSet<Integer> indexSet;
		
		if (map.containsKey(element))
		{
			indexSet = map.get(element);
			indexSet.add(index);
		}
		else
		{
			indexSet = new TreeSet<Integer>();
			indexSet.add(index);
			map.put(element, indexSet);
		}
	}
	
	private void mapRemove(T element, int index)
	{
		TreeSet<Integer> set = map.get(element);
		set.remove(index);
		if (set.isEmpty())
			map.remove(element);
	}
	
	private Integer mapGet(T element)
	{
		TreeSet<Integer> set = map.get(element);
		if (set != null) return set.last();
		return null;
	}

	private void mapSwap(T element1, T element2, int index1, int index2)
	{
		TreeSet<Integer> set1 = map.get(element1);
		TreeSet<Integer> set2 = map.get(element2);
		
		if (set1 == null || set2 == null)	throw new IllegalArgumentException();
		
		set1.remove(index1);
		set2.remove(index2);
		
		set1.add(index2);
		set2.add(index1);
		
	}
	
	

}
