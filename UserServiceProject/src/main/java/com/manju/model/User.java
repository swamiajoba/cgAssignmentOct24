package com.manju.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customers")
public class User {
	@Id
	//@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(unique=true)
	private String username;
	
	
	@Column(unique=true,nullable = false)
	@Size(min = 8)
	private String password;
	
	@Column(unique=true,nullable = false)
	@Email(message = "Invalid Email")
	private String email;
	
	@Column(nullable = false)
	private String fullName;
	

	
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "users_roles",
	            joinColumns = @JoinColumn(name = "username"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	            )
	  private Set<Role> roles = new HashSet<>();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", fullName=" + fullName
				+ ", role=" + roles + ", createdAt=" + createdAt + "]";
	}


	
	

}
