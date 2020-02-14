package application;

import components.Dashboard;
import components.Portal;

public interface UserFactory {
    Portal getPortal();
    Dashboard getDashboard(String entity);
}
