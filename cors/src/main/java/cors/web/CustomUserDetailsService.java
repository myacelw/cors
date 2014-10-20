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
 * һ���Զ���������������ݿ���в���. ���Ժ�����Ҫͨ�����ݿⱣ��Ȩ��.����Ҫ���Ǽ̳�UserDetailsService  
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
            throw new UsernameNotFoundException("�쳣���������û���Ϣδͨ����");   
        }   
            
        return user;   
    }   
  
    /**  
     * ��÷��ʽ�ɫȨ���б�  
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