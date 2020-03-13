package application;

import exceptions.UnAuthorizedUserException;

public class UserProducer {

    /*
    * instantiates and returns the factory for each user
    * */
    public UserFactory getUserFactory(String role) {
        if(role!=null)
            if (role.equals("admin"))
                return new AdminFactory();
            else if (role.equals("doctor"))
                return new DoctorFactory();
            else if (role.equals("assistant"))
                return new AssistantFactory();
        throw new UnAuthorizedUserException();

    }
}
