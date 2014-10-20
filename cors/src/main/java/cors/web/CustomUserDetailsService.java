package cors.web;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cors.domain.User;
import cors.repository.UserRepository;
  
/**  
 * 一个自定义的类用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService  
 *   
 * @author   
 *   
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {   
  
    @Autowired  
    private UserRepository repository;   
  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {   
  
        UserDetails user = null;   
          try {   
        	User t = repository.findByUsername(username).get(0);   
            user = new org.springframework.security.core.userdetails.User(t.getUsername(), t.getPassword(), t.isEnabled(), true, true, true, getAuthorities());   
                
                
        } catch (Exception e) {   
            throw new UsernameNotFoundException("异常处理：检索用户信息未通过！");   
        }   
            
        return user;   
    }   
  
    /**  
     * 获得访问角色权限列表  
     *   
     * @param access  
     * @return  
     */  
    public Collection<GrantedAuthority> getAuthorities() {   
         List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();   
       authList.add(new SimpleGrantedAuthority("ROLE_USERS"));   
        return authList;   
    }   
} 