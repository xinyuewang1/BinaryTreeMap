package core;

import java.util.Iterator;

import impl.BinarySearchTree;

/**
 * @author XinyueWang
 *
 * @param <K> Key Type
 * @param <V> Value Type
 * 
 * Generic Explain: Binary Search Tree needs the element extends from comparable interface. 
 * Meanwhile, in TreeEntry node, the real comparison is between keys. 
 * Therefore, the following generic structure is implemented.
 *  
 */
public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

	private int size;
	private BinarySearchTree<TreeEntry<K, V>> tree;

	//	Create an inner class to store the entries called TreeEntry. Include any mandatory method and a constructor 
	//	that  initialises  an  entry  object  with  an  initial  key  and  value.  Also  include  a  toString() method  that  returns 
	//	{<key>,<value>}. 
	private class TreeEntry<K extends Comparable<K>, V> implements Comparable<TreeEntry<K, V>>, Entry<K, V> {
		K key;
		V value;

		//Constructor
		public TreeEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(TreeEntry<K, V> otherEntry) {
			if (this.key.equals(otherEntry.key)) {
				return 0;
			}
			else if (this.key.compareTo(otherEntry.key) > 0) {
				return 1;
			}
			else {
				return -1;
			}
		}

		//getters
		public K key() {
			return key;
		}

		public V value() {
			return value;
		}

		@Override
		public String toString() {
			return "{" + key + "," + value + "}";
		}


	}

	//Constructor
	public TreeMap() {
		tree = new BinarySearchTree<TreeEntry<K, V>>();
		size = 0;
	}

	//(b) Implement the size(), isEmpty() and toString() methods – one line of code each. Then implement the put(), get() 
	//	and remove() methods – no more than 5 lines of code each. 
	public int size() {
		return size;
	};

	public boolean isEmpty() {
		return size == 0;
	};

	// create a new TreeEntry node with params, insert it into tree.
	public V put(K k, V v) {
		TreeEntry<K, V> te = new TreeEntry<K, V>(k, v);
		tree.insert(te);
		size++;
		return (V) te.value;
	};

	// return value with key k.
	public V get(K k) {
		for(TreeEntry<K, V> te: tree) {
			if (te.key.compareTo(k) == 0) return (V) te.value;
		}
		return null;
	};

	// remove the TreeEntry with key k. Return the removed TreeEntry.
	//If this TreeEntry doesn't exsit, return null.
	@SuppressWarnings("unchecked")
	public V remove(K k) {
		for (TreeEntry<K, V> te: tree) {
			if (te.key.compareTo(k) == 0) {
				size--;
				return (V) tree.remove(te);
			}
		}
		return null;
	};

	// Logic of Iterator:
	// Basically using iterator in Binary Search Tree with structure of iterator from Abstract Binary Tree.
	// Iterator itself is iterator from BinarySearchTree, implemented with two function hasNext() to tell is there more elements,
	// and next() which return the element that is looping.
	public Iterator<K> keys() {
		return new Iterator<K>() {
			Iterator<TreeMap<K, V>.TreeEntry<K, V>> iterator = new TreeMap<K, V>().tree.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public K next() {
				return iterator.next().key;
			}
		};

	};

	public Iterator<V> values(){
		return new Iterator<V>() {
			Iterator<TreeMap<K, V>.TreeEntry<K, V>> iterator = new TreeMap<K, V>().tree.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public V next() {
				return iterator.next().value;
			}
		};
	};

	public Iterator<Entry<K,V>> entries(){
		return new Iterator<Entry<K, V>>() {
			Iterator<TreeMap<K, V>.TreeEntry<K, V>> iterator = new TreeMap<K, V>().tree.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Entry<K, V> next() {
				return iterator.next();
			}
		};
	};

	@Override
	public String toString() {
		return tree.toString();
	}

	public static void main(String[] args) {
		TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
		System.out.println(tm);
		//		tm.put(24, "Hello");
		tm.put(24, "");
		System.out.println(tm);
		tm.put(12,"");
		System.out.println(tm);
		tm.put(36,"");
		System.out.println(tm);
		tm.put(5,"");
		System.out.println(tm);
		tm.put(7,"");
		System.out.println(tm);
		tm.put(2,"");
		System.out.println(tm);
		tm.put(76,"");
		System.out.println(tm);
		tm.remove(24);
		System.out.println(tm);
		tm.put(18,"");
		System.out.println(tm);
		tm.put(24,""); 
		System.out.println(tm);
	}
}
