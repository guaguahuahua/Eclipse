package dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import entity.Person;

@Repository
public class PersonDao {
	
	@Resource private SessionFactory sessionFactory;
	//ʹ��hibernate�����ݲ��ݣ�����Ҫ��ʹ����session����
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * ��ѯ���е���Ա��Ϣ
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Person> selectPersons(){
		@SuppressWarnings("unchecked")
		List<Person> res=this.getSession().createCriteria(Person.class).list();
		return res;
	}
	
	/**
	 * ����һ����Ա��Ϣ
	 * @param p
	 */
	public void addPerson(Person p) {
		this.getSession().save(p);
	}
	
	/**
	 * ͨ��һ����Ա��idɾ�����˵���Ϣ
	 * @param id
	 */
	public void delPersonById(String id) {
		this.getSession().createQuery("delete Person where id=?").setParameter(0, id);
	}
	
	/**
	 * ����һ���˵���Ϣ
	 * @param p
	 */
	public void updatePerson(Person p) {
		this.getSession().update(p);
	}
	
	/**
	 * ͨ��һ���˵�id��ѯ��Ϣ
	 * @param id
	 * @return
	 */
	public Person queryPersonById(String id) {
		return (Person) this.getSession().createQuery("from Person where id=?").setParameter(0, id).uniqueResult();
	}
}