package application;

import models.Assistant;
import models.Doctor;
import org.junit.jupiter.api.Test;
import views.DashboardView;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void getDashboard() {
        UserFactory factory=new UserProducer().getUserFactory("admin");
        assertThrows(IllegalArgumentException.class,()->factory.getDashboard("surgeries"));
        assertThrows(IllegalArgumentException.class,()->factory.getDashboard("patients"));
        assertThrows(IllegalArgumentException.class,()->factory.getDashboard(null));
    }
}