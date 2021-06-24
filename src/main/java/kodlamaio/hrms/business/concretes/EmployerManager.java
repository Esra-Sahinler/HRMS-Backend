package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.MailCheckService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	private EmployerDao employerDao;
	private MailCheckService mailCheckService;
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, MailCheckService mailCheckService, VerificationCodeService verificationCodeService) {
		super();
		this.employerDao = employerDao;
		this.mailCheckService = mailCheckService;
		this.verificationCodeService = verificationCodeService;
	}
	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"İş verenler listelendi");
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email));
	}

	@Override
	public Result add(Employer employer) {
		var domain = employer.getEmail().split("@")[1];
        var result = employer.getWebAddress().equals(domain);
		if(employer.getWebAddress() == null || employer.getCompanyName() == null || employer.getPhoneNumber() == null
				|| employer.getEmail() == null || employer.getPassword() == null) {
			return new ErrorResult("Boş alan bırakmayınız");
		}else if(!checkEmail(employer.getEmail())) {
			return new ErrorResult("Email kullanılmaktadır.");
		}else if(!result) {
            return new ErrorResult("Email adresi ile web sitesi eşleşmiyor");
        }
		//mailCheckService.checkEmail(employer.getEmail());
		this.employerDao.save(employer);
		this.verificationCodeService.sendCode();
		return new SuccessResult("İş veren eklendi");
	}
	
	private  boolean checkEmail(String email) {
		if (this.employerDao.findByEmail(email) == null) {
			return true;
		}
		return false;
		
	}

}
