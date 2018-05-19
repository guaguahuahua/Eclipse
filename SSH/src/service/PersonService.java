package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PersonDao;
import entity.Person;

/**
 * ֱ�ӵ������ݷ��ʲ�ķ����������ݲ���
 * @author Administrator
 *
 */
@Service
public class PersonService {

	@Autowired private PersonDao personDao;
	
	public List<Person> selectPersons(){
		return personDao.selectPersons();
	}
	
	public void addPerson(Person p) {
		personDao.addPerson(p);
	}
	
	public void delPersonById(String id) {
		personDao.delPersonById(id);
	}
	
	public void updatePerson(Person p) {
		personDao.updatePerson(p);
	}
	
	public Person queryPersonById(String id) {
		return personDao.queryPersonById(id);
	}
}