package myfile;

import DirectoriesAndFiles.Directory;
import DirectoriesAndFiles.File;
import DirectoriesAndFiles.FileManagement;
import DirectoriesAndFiles.Reader;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyFile {

    public static void main(String[] args) {
        
        try{
            Scanner scn=new Scanner(System.in);
            int scnInt;
            InputStream IS=MyFile.class.getResourceAsStream("/myfile/filesystem.txt");
            Reader read=new Reader(IS);
            Directory root=read.readFile();
            actionChoice(root);
        }catch(ArrayIndexOutOfBoundsException e){System.out.println(e);}
        catch(NullPointerException e){System.out.println(e);}
    }
    
    public static void actionChoice(Directory dr){
        printTree(dr);
        System.out.println("(true: continue navigating)");
        System.out.println("(false: action)");
        Scanner scn=new Scanner(System.in);
        try{
            boolean scnBoo=scn.nextBoolean();
            if(scnBoo==true){
                System.out.println("(0 to go back to previous directory)");
                System.out.println("enter integer:");
                int scnInt=scn.nextInt();
                selection(dr,scnInt-1);
            }
            else{
                secondChoice(dr);
            }
        }
        catch(InputMismatchException e){
            System.out.println("Input Mismatch: try again");
            actionChoice(dr);
        }
        
        
    }
    
    public static void secondChoice(Directory dr){
        System.out.println("1-) Add new directory to current directory");
        System.out.println("2-) Add new file to current directory");
        System.out.println("3-) Delete directoy from current directory");
        System.out.println("4-) Delete file from current directory");
        System.out.println("5-) Search a file by name from current directory");
        System.out.println("6-) Search a file by extension from current directory");
        System.out.println("7-) Search a file or directory by name from current directory");
        
        Scanner scn=new Scanner(System.in);
        int scnInt=scn.nextInt();
        String scnString;
        FileManagement f;
        switch(scnInt){
            case 1:
                System.out.print("Enter directory name:");
                scnString=scn.next();
                Directory tmpD= new Directory(dr,scnString);
            break;

            case 2:
                System.out.print("Enter file name:");
                String scnString1=scn.next();
                System.out.print("\nEnter file extension:");
                scnString=scn.next();
                File tmpF=new File(dr,scnString1,scnString);
            break;

            case 3:
                System.out.print("Enter the index of the directory: ");
                scnInt=scn.nextInt();
                if(dr.getStorageElement(scnInt-1) instanceof Directory){
                    dr.delete(scnInt-1);
                }
                else{
                    System.out.println("Not a directory");
                }
                
            break;
            
            case 4:
                System.out.print("Enter the index of the file: ");
                scnInt=scn.nextInt();
                if(dr.getStorageElement(scnInt-1) instanceof File){
                    dr.delete(scnInt-1);
                }
                else{
                    System.out.println("Not a file");
                }
            break;
            
            case 5:
                scnString=scn.next();
                f=dr.search(scnString);
                if(f instanceof File){
                    File file=(File)f;
                    System.out.println(f.toString());
                }
                else{//f is null
                    System.out.println("Couldn't find the wanted file.");
                }
            break;
            
            case 6:
                scnString=scn.next();
                f=dr.search(scnString);
                if(f instanceof File){
                    File file=(File)f;
                    System.out.println(f.toString());
                }
                else{//f is null
                    System.out.println("Couldn't find the wanted file.");
                }
            break;
            
            case 7:
                scnString=scn.next();
                f=dr.search(scnString);
                if(f instanceof File){
                    File file=(File)f;
                    System.out.println(f.toString());
                }
                else if(f instanceof Directory){
                    System.out.println("Going to the directory found");
                    actionChoice((Directory)f);
                }
                else{//f is null
                    System.out.println("Couldn't find the wanted file or directory.");
                }

            break;
            default: 
                System.out.println("No such option exists.");
        }
        actionChoice(dr);
    }
    
    public static void printTree(Directory dr){
        System.out.println("Contancts List:");
        FileManagement[] s=dr.getStorage();
        System.out.println("/"+dr.getname());
        for(int i=0;i<dr.getN()&&s[i]!=null;i++){
            
            if(s[i] instanceof Directory){
                System.out.println((i+1)+"/"+s[i].getname());
            }
            else if(s[i] instanceof File){
                File tmp=(File)s[i];
                System.out.println((i+1)+"#"+s[i].getname()+"."+tmp.getExt());
            }
        }
    }
    
    public static void selection(Directory dr,int scnInt){
        FileManagement[] s=dr.getStorage();
        if(scnInt==-1){
            if(dr.getUp()==null){
                actionChoice((Directory)dr);
            }
            actionChoice((Directory)dr.getUp());
        }
        else if(s[scnInt] instanceof Directory){
            actionChoice((Directory)s[scnInt]);
        }
        else{
            actionChoice((Directory)dr);
        }
    }
    
    
}
