//package z_blog.app.Entity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserPrincipal implements UserDetails {
//
//    private Visitor visitor;
//
//    public UserPrincipal(Visitor visitor){
//        this.visitor=visitor;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(
//                new SimpleGrantedAuthority("ROLE_" + visitor.getRoles()));
//    }
//    @Override
//    public String getPassword() {
//        return visitor.getPassword();
//    }
//    @Override
//    public String getUsername() {
//        return visitor.getEmail();
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
