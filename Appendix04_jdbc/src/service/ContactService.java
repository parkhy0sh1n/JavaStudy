package service;

public interface ContactService {
	public void addContact();				// 연착처 추가하기.
	public void removeContact();            // 연락처 삭제하기.                                                    
	public void modifyContact();			// 연락처 수정하기.
	public void findContactsByName();		// 연락처 조회하기.
	public void findAllContacts();			// 전체 연락처 목록.
}