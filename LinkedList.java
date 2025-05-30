package Problems;


	import java.util.function.Predicate;

	import java.util.ArrayList;

	import java.util.Comparator;

	import java.util.List;



	public class LinkedList<T> {

	    private Node<T> head;



	    public void add(T data) {

	        Node<T> newNode = new Node<>(data);

	        if (head == null) head = newNode;

	        else {

	            Node<T> current = head;

	            while (current.next != null) current = current.next;

	            current.next = newNode;

	        }

	    }



	    public boolean remove(Predicate<T> predicate) {

	        if (head == null) return false;



	        if (predicate.test(head.data)) {

	            head = head.next;

	            return true;

	        }



	        Node<T> current = head;

	        while (current.next != null) {

	            if (predicate.test(current.next.data)) {

	                current.next = current.next.next;

	                return true;

	            }

	            current = current.next;

	        }

	        return false;

	    }



	    public List<T> toList() {

	        List<T> list = new ArrayList<>();

	        Node<T> current = head;

	        while (current != null) {

	            list.add(current.data);

	            current = current.next;

	        }

	        return list;

	    }



	    public void sort(Comparator<T> comparator) {

	        List<T> list = toList();

	        for (int i = 1; i < list.size(); i++) {

	            T key = list.get(i);

	            int j = i - 1;

	            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {

	                list.set(j + 1, list.get(j));

	                j--;

	            }

	            list.set(j + 1, key);

	        }



	        head = null;

	        for (T item : list) add(item);

	    }

	}



