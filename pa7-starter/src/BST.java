import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */

	Node<K, V> root;
	int size;

	// TODO: add header
	final Comparator<K> COMPARATOR = new Comparator<K>() {

		@Override
		public int compare(K o1, K o2) {
			o1.compareTo(o2);
			return o1.compareTo(o2);
		}
		
	};

	// TODO: add constructor method

	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if (key == null) { 
			throw new IllegalAccessError();
		}

		return this.put(this.root, key, value);
	}

	private boolean put(Node<K,V> node,K key, V value){
		if (node == null) {
			this.size++;
			node = new Node<>(key, value);
			return true;
		}

		int comp = this.COMPARATOR.compare(node.key,key);

		if(comp < 0) {
			return this.put(node.right, key, value);
		} else if (comp > 0) {
			return this.put(node.left, key, value);
		} 


		return false;
	}

	/**
	 * Replaces the value that maps to the key if it is present
	 * 
	 * @param key      The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * 
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the value corresponding to the specified key
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return The number of (key, value) pairs in this DefaultMap
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * 
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * @return true if the specified key is in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		// TODO Auto-generated method stub
		return false;
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	/**
	 * 
	 * @return an array containing the keys of this DefaultMap. If this DefaultMap
	 *         is
	 *         empty, returns array of length zero.
	 */
	@Override
	public List<K> keys() {
		ArrayList<K> listOfKeyKs = new ArrayList<>();
		return null;
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		K key;
		V value;

		Node<K, V> left;
		Node<K, V> right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * 
		 * @return The key of this node.
		 */
		@Override
		public K getKey() {
			return this.key;
		}

		/**
		 * 
		 * @return The value of this node.
		 */
		@Override
		public V getValue() {
			return this.value;
		}

		/**
		 * Sets a new value to the node.
		 * 
		 * @param value new value of this node
		 */
		@Override
		public void setValue(V value) {
			this.value = value;
			
		}
		
		
	}
	 
}