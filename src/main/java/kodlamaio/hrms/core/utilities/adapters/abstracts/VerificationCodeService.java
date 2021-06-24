package kodlamaio.hrms.core.utilities.adapters.abstracts;

public interface VerificationCodeService {
	void sendVerificationLink(String email);
	String sendCode();
}
