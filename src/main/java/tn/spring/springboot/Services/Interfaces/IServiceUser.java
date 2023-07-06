package tn.spring.springboot.Services.Interfaces;




import org.springframework.data.domain.Page;
import tn.spring.springboot.entities.User;

import java.util.Date;
import java.util.List;

public interface IServiceUser {

    List<User> getAllUsers() ;
    Page<User> getAllUsers(int offset , int pageSize  ) ;
    User createUser(User user) ;

}
