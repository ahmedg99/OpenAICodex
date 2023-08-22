package tn.spring.springboot.services.Implementation;

import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IServiceCredential;
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
