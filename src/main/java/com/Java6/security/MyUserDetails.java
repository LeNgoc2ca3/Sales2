package com.Java6.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.Java6.entity.Account;
import com.Java6.entity.Authority;
import com.Java6.service.AuthorityService;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
//	Trong quá trình xác thực, nếu xác thực người dùng thành công,
//	chúng ta sẽ gửi lại một đối tượng Authentication được khởi tạo đầy đủ.
//	Ngược lại , khi xác thực thất bại AuthentiactionException sẽ được đưa ra.

	private String name;
	private String password;
	private List<SimpleGrantedAuthority> authorities;

	@Autowired
	private AuthorityService authorityService;

	public MyUserDetails(Account account) {
		name=account.getUsername();
		password=account.getPassword();
//		authorities= account.getAuthorities().stream()
//				.map(er -> er.getRole().getId())
//				.map(SimpleGrantedAuthority :: new)
//				.collect(Collectors.toList()).toArray(new String[0]);

//		String[] roles = accounts.getRole().split(",");
//		authorities = new ArrayList<>();
//		for (String role : roles) {
//			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
//			authorities.add(authority);
//            System.out.println(authorities);
//            tạo danh sách các quyền từ chuỗi quyền của tài khoản, bằng cách sử dụng phương thức split()
//            để tách chuỗi thành các phần tử, sau đó tạo đối tượng SimpleGrantedAuthority cho mỗi phần tử và
//            thêm vào danh sách authorities
//		}

		String[] roles = account.getAuthorities().stream()
				.map(er -> er.getRole().getName())
				.collect(Collectors.toList()).toArray(new String[0]);
		authorities = new ArrayList<>();
		for(String role: roles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			System.out.println(role);
			authorities.add(authority);
        }
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
		return name;
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
		return true;
	}

}
