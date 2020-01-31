package models;

public final class CurrentUser {
    private static CurrentUser user=null;
    private String name;
    private String id;
    private String role;
    private CurrentUser(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static CurrentUser getCurrentUser(){
        if(user==null)
            user=new CurrentUser();
        return user;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
