package net.java.authService.domain;

	import javax.persistence.Column;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.NoArgsConstructor;

	@Entity
	@Table(name = "users")
	@AllArgsConstructor
	@NoArgsConstructor
	public class User {

	    @Id
	    @GeneratedValue
	    private Integer id;

	    
	    @Column(unique = true)
	    private String email;
	   

		@Column(unique = true)
	    private String password;
	    
	    @Column(unique = true)
	    private String userName;
	    
	    
	    private String firstName;
	    
		private String lastName;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		

	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		

	    public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		
	}


