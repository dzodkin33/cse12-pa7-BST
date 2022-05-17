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
}
