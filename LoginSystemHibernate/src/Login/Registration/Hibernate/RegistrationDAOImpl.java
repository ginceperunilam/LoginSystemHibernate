package Login.Registration.Hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Login.Registration.HibernetUtils.HibernateUtil;

public class RegistrationDAOImpl implements RegistrationDAO
{
	@Override
	public Boolean insertUser(Users user) throws Exception 
	{
		Session session = null;
		Transaction tr = null;
		try 
		{
			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			session = sessFact.getCurrentSession();
			tr = session.beginTransaction();
			session.save(user);
			tr.commit();
			return true;
		} 
		catch (Exception e)
		{
			tr.rollback();
			System.out.println("Failed insertUser in RegistrationDAOImpl :"+e);
			throw e;
 		}
		finally
		{
			session.close();
		}
	}

	@Override
	public Users getUser(String userName, String password) throws Exception 
	{
		Users users = new Users();
		Session session = null;
		Transaction tr = null;
		try 
		{
			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			session = sessFact.getCurrentSession();
			
			tr = session.beginTransaction();
			
//			users = session.get(Users.class, 1l);
			System.out.println("RegistrationDAOImpl getUser 1");
			
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.eq("userName", userName));
			criteria.add(Restrictions.eq("password", password));
			criteria.setMaxResults(1);
			users=(Users)criteria.uniqueResult();
			
			tr.commit();

		} 
		catch (Exception e) 
		{
			System.out.println("Failed getUser in RegistrationDAOImpl :"+e);
			throw e;		
		}
		finally
		{
			session.close();
		}
		return users;
	}

}
