package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	@Override
	public DataResult<List<User>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
