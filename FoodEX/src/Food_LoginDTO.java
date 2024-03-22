

public class Food_LoginDTO {
	
	private String sid;
	private String spassword;
	private String sname;
	private String ownername;
	private String stel;
	private String slocation;
	
	public String getId() {
		return sid; 
	}
	public void setId(String id) {
		this.sid = id;
	}
	public String getPassword() {
		return spassword;
	}
	public void setPassword(String password) {
		this.spassword = password;
	}
	public String getName() {
		return sname;
	}
	public void setName(String name) {
		this.sname = name;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getTel() {
		return stel;
	}
	public void setTel(String tel) {
		this.stel = tel;
	}
	public String getLocation() {
		return slocation;
	}
	public void setLocation(String location) {
		this.slocation = location;
	}

	@Override
	public String toString() {
		return "Food_LoginDTO [id=" + sid + ", password=" + spassword + ", name=" + sname + ", ownername=" + ownername
				+ ", tel=" + stel + ", location=" + slocation + "]";
	}

}
