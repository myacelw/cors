package cors.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="users")
public class User {
	@Id
	private String username;
	private String password;
	private String role;
	
	private boolean enabled = true;

	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	

}