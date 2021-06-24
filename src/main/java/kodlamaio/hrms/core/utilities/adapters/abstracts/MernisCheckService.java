package kodlamaio.hrms.core.utilities.adapters.abstracts;

import kodlamaio.hrms.entities.concretes.Candidate;

public interface MernisCheckService {
	public boolean checkIfRealPerson(Candidate candidate);
}
