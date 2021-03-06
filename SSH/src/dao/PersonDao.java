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
	//使用hibernate的数据操纵，它主要是使用了session进行
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 查询所有的人员信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Person> selectPersons(){
		@SuppressWarnings("unchecked")
		List<Person> res=this.getSession().createCriteria(Person.class).list();
		return res;
	}
	
	/**
	 * 添加一个人员信息
	 * @param p
	 */
	public void addPerson(Person p) {
		this.getSession().save(p);
	}
	
	/**
	 * 通过一个人员的id删除该人的信息
	 * @param id
	 */
	public void delPersonById(String id) {
		this.getSession().createQuery("delete Person where id=?").setParameter(0, id);
	}
	
	/**
	 * 更新一个人的信息
	 * @param p
	 */
	public void updatePerson(Person p) {
		this.getSession().update(p);
	}
	
	/**
	 * 通过一个人的id查询信息
	 * @param id
	 * @return
	 */
	public Person queryPersonById(String id) {
		return (Person) this.getSession().createQuery("from Person where id=?").setParameter(0, id).uniqueResult();
	}
}
