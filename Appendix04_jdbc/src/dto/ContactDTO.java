package dto;

// DTO : 데이터를 전달하는 역할.
// 각각의 클래스의 field에 필요한 값을 저장해 놓고, 다른 클래스에서 해당 값을 사용할때는 getter setter 메소드를 이용해서 메소드로 값을 수정하거나 호출 하는 방식을 사용한다.
public class ContactDTO {
	// 컬럼명을 필드로 선언하여 식별자로 사용(DB 컬럼명과 일치하지 않아도 된다.)
	private int contact_no;
	private String name;
	private String tel;
	private String email;
	private String address;
	// 기본 생성자
	public ContactDTO() { }
	// 매개변수가 있는 생성자(멤버변수화)
	public ContactDTO(int contact_no, String name, String tel, String email, String address) {
		super();
		this.contact_no = contact_no;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
	}
	// 각 멤버변수에 대한 getter & setter
	// DTO는 데이터를 담고, DAO가 데이터를 이용한다.
	public int getContact_no() {
		return contact_no;
	}
	public void setContact_no(int contact_no) {
		this.contact_no = contact_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	// 원하는 형식을 출력할 수 있도록 toString 메소드 오버라이딩.
	@Override
	public String toString() {
		return "ContactDTO [contact_no=" + contact_no + ", name=" + name + ", tel=" + tel + ", email=" + email
				+ ", address=" + address + "]";
	}
	
}