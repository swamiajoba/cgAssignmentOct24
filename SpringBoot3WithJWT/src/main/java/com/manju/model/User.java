package com.manju.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="users2")

//Inverse Side
public class User {
	@Id
	//@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(unique=true)
	private String username;
	
	
	@Column(unique=true,nullable = false)
	private String password;
	
	@Column(unique=true,nullable = false)
	@Email(message="Invalid Email")
	private String email;
	
	@Column(nullable = false)
	private String fullName;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	
    @Column(length = 100000)
    private byte[] pic;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	Set<Account> accounts=new HashSet<>();
	
	public User() {
		
	}
	

	public User(String username, String password, @Email(message = "Invalid Email") String email, String fullName,
			Role role, LocalDate createdAt) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.role = role;
		this.createdAt = createdAt;
	}


	public byte[] getPic() {
		return pic;
	}


	public void setPic(byte[] pic) {
		this.pic = pic;
	}


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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
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
		return "User [username=" + username + ", email=" + email + ", fullName=" + fullName + ", role=" + role
				+ ", createdAt=" + createdAt + ", pic=" + Arrays.toString(pic) + "]";
	}




	
	

}
