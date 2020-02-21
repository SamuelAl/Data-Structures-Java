import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class ArrayTest {

	@Test
	void testSize() {
		MyArray<Integer> intArraySimple = new MyArray<Integer>();
		MyArray<Integer> intArrayGeneral = new MyArray<Integer>(20);
		
		assertEquals("Initialize Simple", 0 , intArraySimple.size());
		assertEquals("Initialize General" ,0 , intArrayGeneral.size());
		
		intArrayGeneral.add(5);
		assertEquals("Add first element" ,1, intArrayGeneral.size());
		intArrayGeneral.add(7);
		assertEquals("Add second element", 2, intArrayGeneral.size());
		intArrayGeneral.removeAt(1);
		assertEquals("Remove second element", 1, intArrayGeneral.size());
	}

	@Test
	void testIsEmpty() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		
		assertTrue("Empty", array.isEmpty());
		array.add(1);
		assertFalse("Add 1 element", array.isEmpty());
		array.removeAt(0);
		assertTrue("Remove 1 element", array.isEmpty());
	}

	@Test
	void testGet() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		array.add(5);
		array.add(7);
		
		assertEquals(5, (int)array.get(0));
		assertEquals(7, (int)array.get(1));
	}

	@Test
	void testSet() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		array.add(5);
		array.add(7);
		
		assertEquals(5, (int)array.get(0));
		assertEquals(7, (int)array.get(1));
		
		array.set(1, 2);
		assertEquals(2, (int)array.get(1));
	}

	@Test
	void testClear() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		array.add(5);
		array.add(7);
		array.clear();
		
		assertEquals(0, array.size());
	}

	@Test
	void testAdd() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		array.add(5);
		array.add(7);
		
		assertEquals(5, (int)array.get(0));
		assertEquals(7, (int)array.get(1));
		assertEquals(2, (int)array.size());
	}

	@Test
	void testRemoveAt() {
		MyArray<Integer> array = new MyArray<Integer>(20);
		array.add(5);
		array.add(7);
		
		assertEquals("remove return value",5, (int)array.removeAt(0));
		assertEquals("new size" ,1, array.size());
		assertEquals("remaining value", 7, (int) array.get(0));
	}

}
