package application;

public class User {


	private String email;
	private String fName;
	private String lName;
	private String phone;
	private String pass;
	
	
	
		public User(String email, String fName, String lName, String phone, String pass) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.pass = pass;
	}



		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}


		public String getfName() {
			return fName;
		}
		public void setfName(String fName) {
			this.fName = fName;
		}
		
		
		public String getlName() {
			return lName;
		}
		public void setlName(String lName) {
			this.lName = lName;
		}



		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}

		
		
}
