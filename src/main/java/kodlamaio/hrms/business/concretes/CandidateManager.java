package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.MailCheckService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{
	private CandidateDao candidateDao;
	private MernisCheckService mernisCheckService;
	private MailCheckService mailCheckService;
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, MernisCheckService mernisCheckService, MailCheckService mailCheckService, VerificationCodeService verificationCodeService) {
		super();
		this.candidateDao = candidateDao;
		this.mernisCheckService = mernisCheckService;
		this.mailCheckService = mailCheckService;
		this.verificationCodeService = verificationCodeService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"İş arayanlar listelendi");
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByEmail(email));
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByIdentityNumber(identityNumber));
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getFirstName() == null || candidate.getLastName() == null || candidate.getBirthYear() == null 
				|| candidate.getIdentityNumber() == null || candidate.getEmail() == null || candidate.getPassword() == null )  {
			return new ErrorResult("Boş alan bırakmayınız");
			
		}else if(!mernisCheckService.checkIfRealPerson(candidate)) {
			return new ErrorResult("Mernis Doğrulaması yapılamadı");
			
		}else if(!checkEmail(candidate.getEmail())) {
			return new ErrorResult("Email kullanılmaktadır");
		}else if(!checkNationalId(candidate.getIdentityNumber())) {
			return new ErrorResult("TC Kimlik Numarası kullanılmaktadır");
		}else {
			//mailCheckService.checkEmail(candidate.getEmail());
			this.candidateDao.save(candidate);
			this.verificationCodeService.sendCode();
			return new SuccessResult("Aday eklendi");
		}
	}
	
	private  boolean checkEmail(String email) {
		if (this.candidateDao.findByEmail(email) == null) {
			return true;
		}
		return false;
		
	}
	
	private boolean checkNationalId(String nationalId) {
		if(this.candidateDao.findByIdentityNumber(nationalId) == null) {
			return true;
		}
		return false;
	}
}
