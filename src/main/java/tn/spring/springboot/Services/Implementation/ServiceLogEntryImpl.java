package tn.spring.springboot.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceDeviceAI;
import tn.spring.springboot.Services.Interfaces.IServiceLogEntry;
import tn.spring.springboot.entities.DeviceAI;
import tn.spring.springboot.entities.LogEntry;
import tn.spring.springboot.repositories.DeviceAIRepository;
import tn.spring.springboot.repositories.LogEntyRepository;

import java.util.List;

@Service
public class ServiceLogEntryImpl implements IServiceLogEntry {

    LogEntyRepository logEntyRepository ;

    ServiceLogEntryImpl(LogEntyRepository logEntyRepository){
        this.logEntyRepository = logEntyRepository ;
    }


    @Override
    public LogEntry addLogEntry(LogEntry logEntry) {
        return logEntyRepository.save(logEntry);
    }
}
