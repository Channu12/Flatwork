package org.tyss.flatworld.testcases;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.tyss.flatworld.genericutility.ExcelUtility;

public class Test {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelUtility e = new ExcelUtility();
		Map<String, String> map = e.getEntireTcDataBasedOnTcId(".\\src\\test\\resources\\TestDataFiles\\Kohler_TestData.xlsx", "Kohler_Testdata", "TC001");

		System.out.println(map);
	}

}
