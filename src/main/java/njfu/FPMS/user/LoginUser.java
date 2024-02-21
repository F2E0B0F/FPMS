package njfu.FPMS.user;

import java.time.LocalDateTime;

public class LoginUser {
    private String name;
    private String shadow;
    private String userGroup;
    private LocalDateTime createDate;

    public LoginUser (String name, String shadow, String userGroup, LocalDateTime createDate){
        this.name = name;
        this.shadow = shadow;
        this.createDate = createDate;
        this.userGroup = userGroup;
    }

    public LoginUser (){
        this.name = "";
        this.shadow = "";
        this.createDate = null;
        this.userGroup = "";
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getuserGroup(){
        return this.userGroup;
    }
    public void setuserGroup(String userGroup){
        this.userGroup = userGroup;
    }

    public String getShadow(){
        return this.shadow;
    }
    public void setShadow(String shadow){
        this.shadow = shadow;
    }


    public LocalDateTime getCreateDate(){
        return this.createDate;
    }
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User {" + 
        "name:'" + name + '\'' +
        "shadow:'" + shadow + '\'' +
        "userGroup:'" + userGroup + '\'' +
        "createDate:'" + createDate + '\'' +
        '}';
    }
}
