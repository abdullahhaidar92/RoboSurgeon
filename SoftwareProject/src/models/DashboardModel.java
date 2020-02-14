package models;

import java.util.ArrayList;

public abstract class DashboardModel<T> {



    private ArrayList<T> profiles;

    public DashboardModel(){
        profiles=loadProfiles();
    }

    public abstract ArrayList<T> loadProfiles();
    public abstract ArrayList<T> filter(String query);

    public T getProfile(int i){
        return profiles.get(i);
    }
    public void add(T p){
            profiles.add(p);
    }
    public void remove(T p){
        profiles.remove(p);
    }
    public void setProfiles(ArrayList< T> profiles){
        this.profiles=profiles;
    }
    public void create(T d) {
        // TODO Auto-generated method stub

    }
    public void edit(T d) {
        // TODO Auto-generated method stub

    }
    public ArrayList<T> getProfiles() {
        return profiles;
    }
}
