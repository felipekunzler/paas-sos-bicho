package com.sosbicho.service;

import com.sosbicho.domain.User;
import com.sosbicho.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class JpaUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JpaUserDetailsService jpaUserDetailsService;

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameThrowsExceptionIfNull() {
        doReturn(null).when(userRepository).findByUsernameIgnoreCase(any());
        jpaUserDetailsService.loadUserByUsername("any");
    }

    @Test
    public void ensureUserDetailsWhenLoadUserByUsernameIsValid() {
        User mockUser = new User("name", "pass");
        doReturn(mockUser).when(userRepository).findByUsernameIgnoreCase(any());
        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername("name");

        boolean passwordMatches = User.PASSWORD_ENCODER.matches("pass", userDetails.getPassword());
        assertTrue(passwordMatches);
        assertEquals("name", userDetails.getUsername());

        GrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        boolean hasProperAuthorities = userDetails.getAuthorities().contains(userRole);
        assertTrue(hasProperAuthorities);
    }

}
