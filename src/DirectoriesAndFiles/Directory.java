package DirectoriesAndFiles;
import java.util.Date;
import Hash.SeparateChainHash;

public class Directory extends FileManagement{ 
    
    FileManagement[] storage;//array to store contents
    SeparateChainHash<FileManagement> names=new SeparateChainHash(26);
    SeparateChainHash<FileManagement> numbers=new SeparateChainHash(9);
    int N = 0;
    
    public Directory(Directory up,String name){//if up is null give NullPointerException
            this.up = up;//set parent directory
            this.TreeLvl = up.TreeLvl + 1;//set child depth
            this.name = name;
            storage = new FileManagement[10];
            up.addToStorge(this);//place child in parent
            
    }
    
    Directory(String name){
        this.name = name;
        up = null;
        storage = new FileManagement[10];

    }
    
    public void addToStorge(FileManagement fileType){
        resize();
        for(int i=0;i<storage.length;i++){
            if(storage[i]==null){
                storage[i]=fileType;
                N++;
                break;
            }
        }
        String name=fileType.name;
        stringCheck(name,fileType);
        
        if(fileType instanceof File){
            File f=(File)fileType;
            String ext=f.extension;
            stringCheck(ext,fileType);
        }
    }
    
    private void stringCheck(String name,FileManagement fileType){
        if(97<=(int)name.charAt(0)&&(int)name.charAt(0)<=122){
            int nameInt=name.charAt(0)%97;
            names.insert(fileType,nameInt);
        }
        else if(65<=(int)name.charAt(0)&&(int)name.charAt(0)<=90){
            int nameInt=name.charAt(0)%65;
            names.insert(fileType,nameInt);
        }
        else if(48<=(int)name.charAt(0)&&(int)name.charAt(0)<=57){
            int nameInt=name.charAt(0)%48;
            numbers.insert(fileType,nameInt);
        }
    }
    
    private boolean resize(){
        FileManagement[] tmpM;
        if(N<storage.length/2){
            tmpM=new FileManagement[storage.length/2];
            for(int i=0;storage[i]!=null;i++){
                tmpM[i]=storage[i];
            }
            storage=tmpM;
            return true;
        }
        else if(N==storage.length){
            tmpM=new FileManagement[storage.length*2];
            for(int i=0;i<storage.length;i++){
                tmpM[i]=storage[i];
            }
            storage=tmpM;

            return true;
        }
        return false;
    }
    
    public FileManagement[] getStorage(){
        return storage;
    }
    
    public void delete(int scnInt){
        storage[scnInt]=null;
        N--;
        for(int i=scnInt;i<N;i++){
            storage[i]=storage[i+1];
            storage[i+1]=null;
        }
    }
    
    public FileManagement getStorageElement(int ix){
        return storage[ix];
    }
    
    public int getN(){
        return N;
    }
    
    public FileManagement search(String s){
        if(97<=(int)s.charAt(0)&&(int)s.charAt(0)<=122){
            int nameInt=s.charAt(0)%97;
            //System.out.println(s+" "+s.charAt(0)+" "+nameInt);
            return names.searchName(s, nameInt);
        }
        else if(65<=(int)s.charAt(0)&&(int)s.charAt(0)<=90){
            int nameInt=s.charAt(0)%65;
            return names.searchName(s, nameInt);
        }
        int nameInt=s.charAt(0)%48;
        return numbers.searchName(s, nameInt);
    }
}
