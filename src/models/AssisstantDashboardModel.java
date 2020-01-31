package models;

import java.util.ArrayList;

public class AssisstantDashboardModel extends DashboardModel<Assistant> {
    @Override
    public ArrayList<Assistant> loadProfiles() {
        return new ArrayList<Assistant>();
    }

    @Override
    public ArrayList<Assistant> filter(String query) {
        return new ArrayList<Assistant>();
    }
}
