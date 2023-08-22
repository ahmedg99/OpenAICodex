package tn.spring.springboot.services.Implementation;

import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IServiceLogEntry;
import tn.spring.springboot.entities.LogEntry;
import tn.spring.springboot.repositories.LogEntyRepository;

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
