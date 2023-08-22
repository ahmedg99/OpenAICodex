package tn.spring.springboot.services.Implementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IServicePasswordResetToken;
import tn.spring.springboot.entities.PasswordResetToken;
import tn.spring.springboot.repositories.PasswordResetTokenRepository;


@Service
@Slf4j
public class PasswordResetTokenImp implements IServicePasswordResetToken {


    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void givePermissionToResetPassword(PasswordResetToken passwordResetToken) {
        PasswordResetToken passwordResetToken1 = passwordResetTokenRepository.findByToken(passwordResetToken.getToken());
        passwordResetToken1.setCanUpdatePassword(true);
        passwordResetTokenRepository.save(passwordResetToken);
    }


}
