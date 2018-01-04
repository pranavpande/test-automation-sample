package commonComponents;

import java.security.SecureRandom;
import java.math.BigInteger;

public final class RandomStringGenerator {
  private static SecureRandom random = new SecureRandom();

  public static synchronized String generateRandomString(int sizeOfString) {
	try{
		String randomString = new BigInteger(20480, random).toString(32).substring(0, sizeOfString);
	  	return randomString;
	}catch(IndexOutOfBoundsException e){
		String altRandomString = new BigInteger(130, random).toString(32).substring(0, 10); //approx size 10
		return altRandomString;
	}
  }
}