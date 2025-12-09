package DirectoriesAndFiles;

import java.io.InputStream;
import java.util.Scanner;
import myfile.MyFile;
import static myfile.MyFile.actionChoice;

public class test {
    public static void main(String[] args) {
        InputStream IS=MyFile.class.getResourceAsStream("/myfile/filesystem.txt");
        Reader read=new Reader(IS);
        Directory root=read.readFile();
        File file=new File(root,"gogo","exe");
        printTree(root);
        FileManagement f=root.search("exe");
        System.out.println(f.toString());
    }
    
    public static char[] stringtoChar(String s){
        char[] c=new char[s.length()];
        for(int i=0;i<s.length();i++){
            c[i]=s.charAt(i);
        }
        return c;
    }
    
    public static String[] fileDetails(int i,int k,char[] charLine,  String[] array){
        String string="";
        String ext="";
        while(k<charLine.length){
            if(charLine[k]=='#'){
                k++;
                break;
            }
            if(charLine[k]=='	'){}
            else{
                string=string+charLine[k];
            }
            k++;
        }
        array[i]=string;
        if(k>=charLine.length){return array;}
        fileDetails(i+1,k+1,charLine,array);
        return array;
    }
    
    public static void isDirectory(char[] charLine){
        String name="";
        for(int i=0;i<charLine.length;i++){
            if(charLine[i]==' '||charLine[i]=='/'){}
            else{
                name=name+charLine[i];
            }
        }
        System.out.println(name);
    }
    public static File moreFileDetails(char[] charLine){
        String[] detailString=new String[5];
        String extension="";
        detailString=fileDetails(0,0,charLine,detailString);

        //[finding extension]
        //detailString={0:name+extension,1:date,2:size(in string),3:accessLvl,4:null}
        int extensionIndex=detailString[0].length()-3;
        extension=extension+detailString[0].charAt(extensionIndex++)+detailString[0].charAt(extensionIndex++)+detailString[0].charAt(extensionIndex++);
        detailString[4]=extension;
        //extension is the last three indexes of the detailString[0] string

        //[taking out the extension from the name]
        String s="";
        for(int i=0;i<extensionIndex-4;i++){
            s=s+detailString[0].charAt(i);
        }
        detailString[0]=s;

        //[converting size from string to int]
        int size=0;
        int length=detailString[2].length();
        String sizeS=detailString[2];
        for(int i=0;i<length;i++){
            char ch=sizeS.charAt(i);
            // int='0'=48
            int number=(ch%48)*(int)(Math.pow(10, length-(i+1)));
            size=size+number;
        }

        //detailString={0:name,1:date,2:size(in string),3:accessLvl,4:extension}
        File file=new File(detailString[0],detailString[4],detailString[1],size,detailString[3]);
        return file;
        //current.addToStorge(file);
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
}
