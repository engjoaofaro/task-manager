package br.eng.joaofaro.taskmanager.services;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
@Service
@Slf4j
public class UserInfoService {

    public AccountUserBean getUserInfo() {
        return getUser();
    }

    private AccountUserBean getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User logged: {}", authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority role : authorities) {
            log.info("Roles: {}", role.getAuthority());
        }
        AccountUserBean userBean = new AccountUserBean();
        userBean.setUsername(authentication.getName());
        userBean.setRoles(authorities);

        return userBean;
    }
}
