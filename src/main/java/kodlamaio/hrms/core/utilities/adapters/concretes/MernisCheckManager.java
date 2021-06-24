package kodlamaio.hrms.core.utilities.adapters.concretes;

import java.rmi.RemoteException;
import java.util.Locale;

import kodlamaio.hrms.core.utilities.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisCheckManager implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
		boolean result =true;
		try {
			result=client.TCKimlikNoDogrula(Long.parseLong(candidate.getIdentityNumber()), 
					candidate.getFirstName().toUpperCase(new Locale("tr")), 
					candidate.getLastName().toUpperCase(new Locale("tr")), candidate.getBirthYear().getYear());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
}
