package io.garden.project.model.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Austr0s
 *
 */
public class CustomUserDetails implements UserDetails {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 2348580937411627782L;

	/**
	 * Username.
	 */
	private String userName;

	/**
	 * User Password.
	 */
	private String password;

	/**
	 * User Account State.
	 */
	private boolean active;

	/**
	 * User GrantedAuthorities.
	 */
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getActive();
		this.authorities = user.getRoles().stream() //
				.map(r -> r.getCode()) //
				.map(SimpleGrantedAuthority::new) //
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
