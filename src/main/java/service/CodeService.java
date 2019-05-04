package service;

import java.util.ArrayList;

public class CodeService {
	public static ArrayList<Integer> codeList = new ArrayList<>();

	public static boolean checkCode(int code) {
		return codeList.contains(code);
	}
}
