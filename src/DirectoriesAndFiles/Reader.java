package DirectoriesAndFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {
    BufferedReader reader;//comp1112_semester_summary.pdf page 71
    String refLine="\\";
    char refChar=refLine.charAt(0);

    public Reader(InputStream IS) {
        reader=new BufferedReader(new InputStreamReader(IS));
    }
    
    public Directory readFile(){
        String readLine;
        boolean type=true;
        Directory curr=null;
        Directory root=null;
        int it=1;
        try{
            readLine=reader.readLine();
            while(readLine!=null){
                int level=0;
                /*
                System.out.println("");
                System.out.println(it+++"-)");
                System.out.println("--------------------------------------------------------------");
                System.out.println("line: "+readLine);
                */
                char[] charLine=new char[readLine.length()];
                for(int i=0;i<readLine.length();i++){
                    if(readLine.charAt(i)=='	'){level++;}
                    if(readLine.charAt(i)==refChar){type=true;}
                    if(readLine.charAt(i)=='#'){type=false;}
                    //System.out.println(charLine[i]);
                    charLine[i]=readLine.charAt(i);
                }
                
                if(type==true){
                    Directory dr=isDirectory(charLine);
                    dr.TreeLvl=level;
                    if(dr.TreeLvl==0){
                        root=dr;
                        curr=root;
                    }
                    else if(curr.TreeLvl<dr.TreeLvl){
                        curr.addToStorge(dr);
                        Directory tmp=curr;
                        curr=dr;
                        curr.up=tmp;
                    }
                    else if(curr.TreeLvl==dr.TreeLvl){
                        curr.up.addToStorge(dr);
                        dr.up=curr.up;
                        curr=dr;
                    }
                    else if(curr.TreeLvl>dr.TreeLvl){
                        int dif=curr.TreeLvl-dr.TreeLvl;
                        Directory tmp=curr;
                        for(int i=0;i<dif;i++){
                            curr=tmp.up;
                            tmp=curr;
                        }
                        curr.up.addToStorge(dr);
                        dr.up=curr.up;
                        curr=dr;
                    }
                }
                
                else{
                    File file=moreFileDetails(charLine);
                    if(curr.TreeLvl<level){
                        curr.addToStorge(file);
                        file.up=curr;
                    }
                    else if(curr.TreeLvl==level){
                        curr.up.addToStorge(file);
                        file.up=curr.up;
                    }
                    file.TreeLvl=level;
                }
                /*
                System.out.println("drlevel="+level);
                System.out.println("curr="+curr.name);
                if(curr.up!=null){
                    System.out.println("uplevel="+curr.up.TreeLvl);
                    System.out.println("curr.up="+curr.up.name);
                    if(curr.up.up!=null){
                    System.out.println("curr.up.up="+curr.up.up.name);
                    }
                }
                */
                readLine=reader.readLine();
            }
        }catch(IOException e){System.out.println(e);}
        catch(NullPointerException e){System.out.println(e);}
        return root;
    }
    
    private Directory isDirectory(char[] charLine){
        String name="";
        for(int i=0;i<charLine.length;i++){
            if(charLine[i]=='	'){}
            else if(charLine[i]==refChar){}
            else{
                name=name+charLine[i];
            }
        }
        Directory dr=new Directory(name);
        return dr;
    }
    
    private String[] fileDetails(int i,int k,char[] charLine,  String[] array){
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
    
    private File moreFileDetails(char[] charLine){
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
}
