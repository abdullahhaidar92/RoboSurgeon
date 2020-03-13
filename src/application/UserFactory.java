package application;

import controllers.Dashboard;
import components.Portal;

/*
* defines the interface for the concrete factories
* getPortal() : creates the portal from where the user can access his dashboards
* getDashboard(String entity) : creates a dashboard for the user to manage the entity,
* the dashboard is different for each user
* */
public interface UserFactory {
    Portal getPortal();
    Dashboard getDashboard(String entity);
}
