package Login.Registration.Hibernate;

public interface RegistrationDAO 
{
	public Boolean insertUser(Users user) throws Exception;
	public Users getUser(String userName, String password) throws Exception;
}
