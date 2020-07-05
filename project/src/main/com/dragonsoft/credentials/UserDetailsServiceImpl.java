package main.com.dragonsoft.credentials;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            String password = user.getPassword();
            boolean enabled = user.isEnabled();
            boolean accountNonExpired = user.isAccountNonExpired();
            boolean credentialsNonExpired = user.isCredentialsNonExpired();
            boolean accountNonLocked = user.isAccountNonLocked();

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            for (UserAuthority r : user.getUserAuthority()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getAuthority().getName().name()));
            }
            org.springframework.security.core.userdetails.User securedUser = new org.springframework.security.core.userdetails.User(
                    username, password, enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, authorities);
            return securedUser;
        } else {
            throw new UsernameNotFoundException(
                    "Unable to find user with username provided!!");
        }
    }
    
    

}