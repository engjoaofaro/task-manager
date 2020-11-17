package br.eng.joaofaro.taskmanager.service;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.services.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author João Faro    contato@joaofaro.eng.br on 16/11/20
 * @version 1.0.0
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserManagerServiceTests {

    @Mock
    private UserInfoService userInfoService;

    private AccountUserBean userBean;
    private AccountUserBean superUserBean;

    @Before
    public void init() {
        userBuilder();
        superUserBuilder();

    }

    @Test
    public void whenUserIsCommomUser_thenReturnUsernameAndRoleUser() {
        when(userInfoService.getUserInfo()).thenReturn(userBean);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        AccountUserBean userInfo = userInfoService.getUserInfo();
        assertEquals(grantedAuthorities, userInfo.getRoles());
        assertEquals("common-user1", userInfo.getUsername());
    }

    @Test
    public void whenUserIsSuperUser_thenReturnUsernameAndRoleSuperUserAndUser() {
        when(userInfoService.getUserInfo()).thenReturn(superUserBean);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_USER"));

        AccountUserBean userInfo = userInfoService.getUserInfo();
        assertEquals(grantedAuthorities, userInfo.getRoles());
        assertEquals("itau-unibanco", userInfo.getUsername());
    }

    private void superUserBuilder() {
        List<GrantedAuthority> grantedSuperUserAuthorities = new ArrayList<>();
        grantedSuperUserAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        grantedSuperUserAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_USER"));

        superUserBean = new AccountUserBean();
        superUserBean.setUsername("itau-unibanco");
        superUserBean.setRoles(grantedSuperUserAuthorities);
    }

    private void userBuilder() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        userBean = new AccountUserBean();
        userBean.setUsername("common-user1");
        userBean.setRoles(grantedAuthorities);
    }
}
