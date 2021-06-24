package kodlamaio.hrms.core.utilities.adapters.concretes;

import java.util.UUID;

import kodlamaio.hrms.core.utilities.adapters.abstracts.VerificationCodeService;

public class VerificationCodeManager implements VerificationCodeService{

	@Override
	public void sendVerificationLink(String email) {
		UUID uuid = UUID.randomUUID();
		String verificationLink = "https://hrmsverificationemail/" + uuid.toString();
		System.out.println("Doğrulama bağlantısı gönderildi: " + email);
		System.out.println("Hesabınızı doğrulamak için lütfen bağlantıya tıklayın: " + verificationLink);
	}

	@Override
	public String sendCode() {
		UUID uuid = UUID.randomUUID();
		String verificationCode = uuid.toString();
		System.out.println("Aktivasyon kodunuz: " + verificationCode);
		return verificationCode;
	}
}
