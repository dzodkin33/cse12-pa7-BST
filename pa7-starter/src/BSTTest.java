import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {

	BST<Integer, Integer> testTree;

	@Before
	public void before() {
		testTree = new BST<Integer, Integer>();
	}
	
	// Testing constructor method
	// TODO: add header comments
	@Test
	public void constructorTest() {
		assertNotNull(testTree);
	}
	
	// !Testing put method! //
	@Test
	public void testPut() {
		int expectedSize = 1;
		assertTrue(testTree.put(1, 2));

		assertEquals(expectedSize, testTree.size());
	}

	@Test
	public void testPutDublicateKeys(){
		int expectedSize = 1;
		testTree.put(1, 3);

		assertFalse(testTree.put(1, 2));
		assertEquals(expectedSize, testTree.size());
	}

	@Test 
	public void testPutCorrectPlacement() {

	}
	// ! Finish testing exception
	// @Test 
	// public void testPutNullKey() {
	// 	String expectedMessage = new IllegalAccessError().getMessage();
	// 	assertThrows(testTree.put(null, 2).compareTo())

	// }

	// !Testing isEmpty method! //
	@Test
	public void testIsEmptyEmpty(){
		assertTrue(testTree.isEmpty());
	}
	
	@Test
	public void testIsEmptyNotEmpty() {
		testTree.put(1, 1);
		assertFalse(testTree.isEmpty());
	}

	// !Testing get() method! //
	@Test 
	public void testGet() {
		testTree.put(1, 2);
		Integer expected = 2;
		assertEquals(expected, testTree.get(1));
	}

	@Test
	public void testGetNonExistent() {
		testTree.put(1, 2);

		assertNull(testTree.get(2));
	}

	@Test
	public void testGetEmptyTree() {
		assertNull(testTree.get(2));
	}

	@Test
	public void testGetNullValue() {
		testTree.put(1, null);
		assertNull(testTree.get(1));
	}
}
