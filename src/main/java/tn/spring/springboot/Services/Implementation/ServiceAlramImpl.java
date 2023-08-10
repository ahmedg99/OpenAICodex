package tn.spring.springboot.Services.Implementation;

import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceAlram;
import tn.spring.springboot.Services.Interfaces.IServiceLogEntry;
import tn.spring.springboot.entities.LogEntry;
import tn.spring.springboot.repositories.AlarmRepository;
import tn.spring.springboot.repositories.LogEntyRepository;

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
