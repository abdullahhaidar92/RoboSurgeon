package application;

import exceptions.UnAuthorizedUserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProducerTest {

    @Test
    void getUserFactory() {
        UserProducer producer=new UserProducer();
        assertThrows(UnAuthorizedUserException.class,()->producer.getUserFactory("patient"));
        assertThrows(UnAuthorizedUserException.class,()->producer.getUserFactory("nurse"));
        assertThrows(UnAuthorizedUserException.class,()->producer.getUserFactory(null));
    }
}