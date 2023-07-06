package tn.spring.springboot.Services.Interfaces;




import tn.spring.springboot.entities.User;

import java.util.Date;
import java.util.List;

public interface IServiceUser {

    List<User> getAllUsers() ;
    User createUser(User user) ;

}
