package util;

import org.apache.log4j.Logger;

import java.util.Random;

public class RandomCodeUtil {
	final static Logger logger = Logger.getLogger(RandomCodeUtil.class);
	public static int getRandomCode() {
		logger.trace("generation of random code");
		Random r = new Random();
		return r.nextInt((999 - 100) + 1) + 100;
	}
}
