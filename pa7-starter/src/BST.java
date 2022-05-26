import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import javax.xml.namespace.QName;

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

	public Node<K, V> root;
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
	 * Adds the specified key, value pair to this BST
	 * Note: duplicate keys are not allowed
	 * 
	 * @param  key a key of the new entry.
	 * @param value a value of the new entry.
	 * 
	 * @return true if the key value pair was added to this BST
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if (key == null) { 
			throw new IllegalAccessError();
		}

		Node<K, V> recursiveResult = this.put(this.root, key, value);
		if (recursiveResult == null) {
			return false;
		} else {
			this.root = recursiveResult;
			return true;
		}
		
	}


	/**
	 * This is a helper method that is used recursively and adds
	 * a new key value pair to this BTS starting from the a certain node.
	 * 
	 * @param node  a starting node of a recursion.
	 * @param key   a key of the new entry.
	 * @param value a value of the new entry.
	 * 
	 * @return returns a new root type Node if successful
	 */
	private Node<K, V> put(Node<K,V> node,K key, V value){
		if (node == null) {
			this.size++;
			return new Node<>(key, value, null, null);
		}

		int comp = this.COMPARATOR.compare(node.key,key);

		if(comp == 0) {
			return null;
		}

		if(comp < 0) {
			node.right = this.put(node.right, key, value);
			return node;
		} else if (comp > 0) {
			node.left = this.put(node.right, key, value);
			return node;
		}

		return null;

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

		Node<K, V> recursiveResult = this.replace(this.root, key, newValue);

		if(!this.containsKey(key)) {
			return false;
		}

		if (recursiveResult == null) {
			return false;
		} else {
			this.root = recursiveResult;
			return true;
		}
	}

	/**
	 * This is a private recursive helper method that helps replacing the 
	 * value on a given key.
	 * 
	 * @param node 	a starting node of a recursion.
	 * @param key      The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return returns a new front node.
	 */
	private Node<K, V> replace(Node<K,V> node, K key, V newValue) {

		if (node == null) {
			return null;
		}

		int comp = this.COMPARATOR.compare(node.key, key);

		if (comp == 0) {
			node.setValue(newValue);
			return node;
		}

		if (comp < 0) {
			node.right = this.replace(node.right, key, newValue);
			return node;
		} else if (comp > 0) {
			node.left = this.replace(node.right, key, newValue);
			return node;
		}



		return null;
	}

	//TODO: add header
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		if (!this.containsKey(key)) {
			return false;
		} else {
			this.removeRecursively(key);
			return true;
		}
	}


	// TODO: add header
	private Node<K, V> remove(Node<K, V> node, K key) {

		if (node == null) {
			return null;
		}

		int comp = this.COMPARATOR.compare(node.key, key);

		if (comp > 0) {
			node.left = this.remove(node.left, key);
		} else if (comp < 0) {
			node.right = this.remove(node.right, key);
		} else {

			this.size--;
			// Case: node with only one child or no children
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}

			// Case: node with two children
			// Get minimum from right subtree, then remove it
			Node<K, V> nextLargest = nodeWithMinimumKey(node.right);
			node.key = nextLargest.key;
			node.value = nextLargest.value;

			// Remove nextLargest node
			node.right = this.remove(node.right, node.key);
		}

		return node;
	}



	// TODO: write a header
	Node<K, V> nodeWithMinimumKey(Node<K, V> root) {
		Node<K, V> minimum = this.root;
		while (this.root.left != null) {
			minimum = this.root.left;
			this.root = this.root.left;
		}
		return minimum;
	}


	// TODO: add header
	// This calls the above method to recursively remove a key. If the number of
	// keys in the map afterwards is the same, we did not find our key, so we
	// throw a NoSuchElementException. Otherwise, we decrement the size of the map.
	public void removeRecursively(K keyToRemove) {
		this.root = this.remove(this.root, keyToRemove);
		
		this.size--;
	}

	/**
	 * Adds the key, value pair to this BST if it is not present,
	 * otherwise, replaces the value with the given value.
	 * 
	 * @param key   a key of a new entry.
	 * @param value a value of a new entry.
	 * 
	 * 
	 * 
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }

		this.root = this.set(this.root, key, value);
		
	}

	/**
	 * This a private recursive helper method for set method().
	 * It 
	 * 
	 * @param node
	 * @param key   a key of a new entry.
	 * @param value a value of a new entry.
	 * 
	 * 
	 * 
	 * @return returns a new front node
	 */
	private Node<K, V> set(Node<K, V> node, K key, V value) {
		if (node == null) {
			this.size++;
			return new Node<K, V>(key, value, null, null);
		}

		int comp = this.COMPARATOR.compare(node.key, key);

		if (comp < 0) {
			node.right = this.set(node.right, key, value);
			return node;
		} else if (comp > 0) {
			node.left = this.set(node.right, key, value);
			return node;
		} else {
			node.setValue(value);
			return node;
		}
	}

	/**
	 * @return the value corresponding to the specified key,
	 * 			null if didn't find value
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }
		Node<K, V> recursiveResult = this.get(this.root, key);
		
		if(recursiveResult != null) {
			return recursiveResult.getValue();
		}
		return null;
	}

	/**
	 * This is a recursive helper method that returns a
	 * node with a given key.
	 * 
	 * @param node a starter node from which recursion starts
	 * @param key a desired key
	 * @return a node with a given key, otherwise null
	 */
	private Node<K, V> get(Node<K, V> node, K key) {
		if(node == null) {
			return null;
		}
		if(node.getKey() == key) {
			return node;
		}

		int comp = this.COMPARATOR.compare(node.key,key);

		if(comp > 0) {
			return this.get(node.left, key);
		} else if (comp < 0){
			return this.get(node.right, key);
		}

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
	 * This method determines if a key is present in a BST. 
	 * 
	 * @param key - a key this method searches for.
	 * 
	 * @return true if the specified key is in this BST.
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null) { throw new IllegalAccessError(); }


		return this.containsKey(this.root, key);
	}

	/**
	 * This is a private helper method for the containsKey()
	 * method. It uses the recursion. 
	 * 
	 * @param node - the node from which recursion starts.
	 * @param key - a key we are searching for.
	 * 
	 * @return true if the specified key is in this map, 
	 * 			otherwise false
	 */
	public boolean containsKey(Node<K, V> node, K key) {
		if (node == null) {
			return false;
		}

		int comp = this.COMPARATOR.compare(node.key, key);

		if (comp == 0) {
			return true;
		}

		if (comp < 0) {
			return this.containsKey(node.left, key);
		} else if (comp > 0) {
			return this.containsKey(node.right, key);
		}

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
		this.addKeys(this.root, listOfKeyKs);
		return listOfKeyKs;
	}

	// TODO: add header
	private void addKeys(Node<K, V>node, List<K> listOfKeyKs) {
		if (node == null) {
			return;
		}

		this.addKeys(node.left, listOfKeyKs);
		listOfKeyKs.add(node.key);
		this.addKeys(node.right, listOfKeyKs);

	}
	
	static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		K key;
		V value;

		Node<K, V> left;
		Node<K, V> right;

		public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.value = value;
			this.right = right;
			this.left = left;
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