package tn.spring.springboot.Services.Interfaces;


import tn.spring.springboot.entities.PasswordResetToken;

public interface IServicePasswordResetToken {

PasswordResetToken findByToken(String token);

  void   givePermissionToResetPassword(PasswordResetToken passwordResetToken) ;

}
