import java.util.LinkedList;

public class LRUCache<K> {
	private class Node<K> {
		final K current;

		Node(K node) {
			this.current = node;
		}
	}
	LinkedList<Node<K>> values;
	int limit;

	LRUCache(int limit) {
		this.values = new LinkedList<Node<K>>();
		this.limit = limit;
	}

	Node add(K value) {
		Node checkNode = this.exists(value);
		if (checkNode == null) {
			Node newNode = new Node<K>(value);
			this.values.addLast(newNode);
			if (this.values.size() > this.limit) {
				this.values.removeFirst();
			}
			return newNode;
		} else {
			this.putLast(checkNode);
			return checkNode;
		}
	}

	Node exists(K value) {
		for (Node<K> access : this.values) {
			if (access.current == value) {
				return access;
			}
		}
		return null;
	}

	Node putLast(Node node) {
		this.values.remove(node);
		this.values.addLast(node);
		return node;
	}

	Node get(K value) {
		Node checkNode = this.exists(value);
		if (checkNode == null) {
			return checkNode;
		} else {
			this.putLast(checkNode);
			return checkNode;
		}
	}

	void printList() {
		for (Node<K> node : this.values) {
			System.out.print(node.current + " ");
		}
	}

	public static void main(String[] args) {
		String[] testList = new String[]{"A", "W", "A", "R", "E"};
		int testLimit = 3;
		LRUCache<String> testCache = new LRUCache<String>(testLimit);
		for (String i : testList) {
			testCache.add(i);
		}
		testCache.printList();
	}
}