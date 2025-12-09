/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hash;

import DirectoriesAndFiles.File;
import DirectoriesAndFiles.FileManagement;


/**
 *
 * @author gameb
 */
public class LinkedList<T> {

    public Node<T> first;
    public Node<T> last;

    public void insertFirst(T x) {
        Node newNode = new Node(x);
        //System.out.println(x.toString());
        newNode.next = first;
        if (last == null) { // means the list is empty
            last = newNode;
        }
        first = newNode;
    }

    public void insertLast(int x) {
        Node newNode = new Node(x);
        if (last == null) { // means the list is empty
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public void insertafter(int x, Node prev) { // assuming prev is in the list
        Node newNode = new Node(x);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    public Node search(T x) {
        Node tmp = first;
        while (tmp != null) {
            if (tmp.data == x) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }
    
    public FileManagement search(String x) {
        Node tmp = first;
        /*
        if(tmp==null){
            System.out.println("LinkedList,search tmp is null");
        }*/
        while (tmp != null) {
            FileManagement f=(FileManagement)tmp.data;
            //System.out.println(f.getname()+" "+x);
            if (f.getname().equals(x)) {
                return f;
            }
            else if(f instanceof File){
                File file=(File)f;
                if(((File) f).getExt().equals(x)){
                    return f;
                }
            }
            tmp = tmp.next;
        }
        return null;
    }

    public void removeFirst() {
        if (first == null) {
            System.out.println("The list is already empty");
            return;
        }
        first = first.next;
    }

    public void removeLast() {
        if (first == null) {
            System.out.println("The list is already empty");
            return;
        }
        Node tmp = first.next;
        Node prev = first;
        while (tmp.next != null) { // stops when tmp is the last element
            tmp = tmp.next;
            prev = prev.next;
        }
        prev.next = null;
        last = prev;
    }

    public void removeAfter(Node prev) { // assuming prev is in the list
        if (first == null) {
            System.out.println("The list is already empty");
            return;
        }
        if (prev.next == null) {
            System.out.println("There are no elements after the prev");
            return;
        }
        prev.next = prev.next.next;
    }

    @Override
    public String toString() {
        String s = "";
        Node tmp = first;
        while (tmp != null) {
            FileManagement f=(FileManagement)tmp.data;
            s += f.getname() + "->";
            tmp = tmp.next;
        }
        s += "Null";
        return s;
    }

}
