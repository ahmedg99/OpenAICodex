package tn.spring.springboot.Services.Implementation;

import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceAlram;
import tn.spring.springboot.Services.Interfaces.IServiceCredential;
import tn.spring.springboot.repositories.AlarmRepository;
import tn.spring.springboot.repositories.CredentialRepository;

@Service
public class ServiceCredentialImpl implements IServiceCredential {

    CredentialRepository credentialRepository ;

    ServiceCredentialImpl(CredentialRepository credentialRepository){
        this.credentialRepository = credentialRepository ;
    }

    @Override
    public boolean existbyName(String name) {
        return credentialRepository.existsCredentialByUsername(name);
    }
}
