package DirectoriesAndFiles;
import java.util.Date;

public class FileManagement {
    

    int TreeLvl;//how deep is the file?
    Directory up;//upperDirectory
    String name;
    Date lastMod;//last modified
    int size;
    boolean accessLvl = false;//false=userLvl, true=systemLvl
    

    public String getAccessLvlAsString(){
        if(accessLvl==false){

            return "USER";
        }
        return "SYSTEM";
    }
    
    public boolean AccessLevelToBoolean(String lvl){
    if (lvl == null) {
        throw new IllegalArgumentException("Access level can't be null.");
    }
    

    switch (lvl.toUpperCase()) {//explicitly checks for both system and user inputs


        case "USER":
            return false;
        case "SYSTEM":
            return true;
        default:
            throw new IllegalArgumentException("Invalid access level. Your input: " + lvl);
    }
    }
    
    

    //getters


    public String getname(){
        return name;
    }
    
    public Directory getUp(){
        return up;
    }
    
    public int getTreeLvl(){
        return TreeLvl;
    }
}
