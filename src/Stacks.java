import java.util.*;

//’ª¿‡     
public class Stacks {
	private LinkedList<Object> list = new LinkedList<Object>();
	public int top = -1;

	public void push(Object value) {
		top++;
		list.addFirst(value);
	}

	public Object pop() {
		Object temp = list.getFirst();
		top--;
		list.removeFirst();
		return temp;
	}

	public Object top() {
		return list.getFirst();
	}

	public Iterator<Object> iterator(){
		Iterator<Object> itr = list.iterator();
		return itr;	
	}
}
