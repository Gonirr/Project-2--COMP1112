package Hash;

import DirectoriesAndFiles.FileManagement;

public class SeparateChainHash<T> {

    int M;
    LinkedList<T>[] table;

    public SeparateChainHash(int M) {
        table = new LinkedList[M];
        for (int ix = 0; ix < M; ix++) {
            table[ix] = new LinkedList<T>();
            //putting a linked list for every slot in the array
        }
        this.M = M;
    }

    public void insert(T t,int ix) {
        table[ix].insertFirst(t);
    }

    public boolean contains(T t,int ix) {
        Node<T> n = table[ix].search(t);
        return n == null;
    }
    
    public FileManagement searchName(String s,int ix) {
        //System.out.println(this.toString());
        FileManagement n = table[ix].search(s);
        return n;
    }
    
    @Override
    public String toString() {
        String s = "";
        
        for (int i = 0; i < M; i++) {
            System.out.println(i + ": " + table[i]);
        }
        return s;
    }
    

}
