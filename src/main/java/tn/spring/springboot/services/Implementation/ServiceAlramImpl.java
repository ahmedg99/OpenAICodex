package tn.spring.springboot.services.Implementation;

import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IServiceAlram;
import tn.spring.springboot.repositories.AlarmRepository;

@Service
public class ServiceAlramImpl implements IServiceAlram {

    AlarmRepository alarmRepository ;

    ServiceAlramImpl(AlarmRepository alarmRepository){
        this.alarmRepository = alarmRepository ;
    }


    @Override
    public boolean existbyName(String name) {
        return alarmRepository.existsByName(name);
    }
}
