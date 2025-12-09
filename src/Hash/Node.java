
package Hash;
public class Node<T> {
    public T data;
    public Node next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Node with data: " + data;
        return s; 
    }

    
    
    
    
    
}
