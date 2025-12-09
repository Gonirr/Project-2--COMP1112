package DirectoriesAndFiles;
import java.util.Date;

public class File extends FileManagement{
    
    String extension;
    String lastModString;
    String access;
    
    public File(Directory up,String name,String extension){
        this.extension=extension;
        this.name=name;
        this.up=up;
        lastMod=new Date();
        up.addToStorge(this);
        
    }
    
    public File(String name,String extension,String date,int size,String accessLvlString){
        this.extension=extension;
        this.name=name;
        this.size=size;
        lastModString=date;
        accessLvl=AccessLevelToBoolean(accessLvlString);
    }
    
    public String getExt(){
        return extension;
    }
    
    @Override
    public String toString(){
        return "name: "+name+", extension: "+size+", size: "+size;
    }
    
}
