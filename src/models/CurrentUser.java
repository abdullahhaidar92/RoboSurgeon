package models;

public final class CurrentUser {
    private static CurrentUser user=null;
    private String name;
    private int id;
    private String role;
    private CurrentUser(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
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

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
