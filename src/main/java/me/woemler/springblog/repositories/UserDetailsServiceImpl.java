package me.woemler.springblog.repositories;



import me.woemler.springblog.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("searching for user");
		System.out.println(username);
		System.out.println(userRepository.findByUsername(username));
		User user = userRepository.findByUsername(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	//	HashSet<String> Role=  new HashSet<>(Arrays.asList(User.ROLE_USER, User.ROLE_ADMIN));
		for (String role : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}
