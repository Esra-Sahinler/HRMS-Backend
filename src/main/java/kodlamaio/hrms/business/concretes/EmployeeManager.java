package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService{

	@Override
	public DataResult<List<Employee>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
