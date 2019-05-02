package util;

import java.util.Random;

public class RandomCodeUtil {
	public static int getRandomCode(){
		Random r = new Random();
		return r.nextInt((999 - 100) + 1) + 100;
	}
}
