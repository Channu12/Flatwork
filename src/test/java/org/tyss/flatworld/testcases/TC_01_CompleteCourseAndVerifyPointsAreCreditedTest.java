package org.tyss.flatworld.testcases;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.flatworld.genericutility.BaseClass;
import org.tyss.flatworld.genericutility.IConstants;
import org.tyss.flatworld.objectrepository.CurriculumPage;
import org.tyss.flatworld.objectrepository.DashboardPage;
import org.tyss.flatworld.objectrepository.IncentiveAdminHomePage;
import org.tyss.flatworld.objectrepository.KohlerNewPage;
import org.tyss.flatworld.objectrepository.NavigationTitleQuizPage;
import org.tyss.flatworld.objectrepository.PublicUserPage;
import org.tyss.flatworld.objectrepository.TrainingCoursePage;
import org.tyss.flatworld.objectrepository.TrainingPage;

@Listeners(org.tyss.flatworld.genericutility.ListenerImplementationClass.class)
public class TC_01_CompleteCourseAndVerifyPointsAreCreditedTest extends BaseClass {

	@Test
	public void completeCourseAndVerifyPointsAreCredited() {

		// Read data from excel
		Map<String, String> testCaseData = excelUtility.getEntireTcDataBasedOnTcId(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001");

		// Create objects for POM Pages
		DashboardPage dashboardPage = new DashboardPage(driver);
		IncentiveAdminHomePage incentiveAdminHomePage = new IncentiveAdminHomePage(driver);
		PublicUserPage publicUserPage = new PublicUserPage(driver);
		CurriculumPage curriculumPage = new CurriculumPage(driver);
		TrainingPage trainingPage = new TrainingPage(driver);
		TrainingCoursePage trainingCoursePage = new TrainingCoursePage(driver);
		NavigationTitleQuizPage navigationTitleQuizPage = new NavigationTitleQuizPage(driver);
		KohlerNewPage kohlerNewPage = new KohlerNewPage(driver);

		// Click on Menu icon
		webDriverUtility.clickElement(dashboardPage.getMenuIcon());

		// Click on Incentive admin Panel Link
		webDriverUtility.clickElement(dashboardPage.getInsentiveAdminPanelLink());

		// Verify Page Incentive_Admin_Home_Page url
		webDriverUtility.verifyPageUrlContainsGivenUrl(javaUtility.getValueFromTheMap(pageVerificationData, "Incentive_Admin_Home_Page"));

		// Click on Incentive admin Panel Link
		webDriverUtility.clickElement(incentiveAdminHomePage.getUserAdministrationMenu());

		// Click on Incentive public user option
		webDriverUtility.clickElement(incentiveAdminHomePage.getPublicUserOption());

		// Verify Page Public_User_Page url
		webDriverUtility.verifyPageUrlContainsGivenUrl(javaUtility.getValueFromTheMap(pageVerificationData, "Public_User_Page"));

		// Click on Incentive add user button
		webDriverUtility.clickElement(publicUserPage.getAddUserButton());

		// Get random number of range 5000
		int randomNumber = javaUtility.getRandomNumber(5000);

		// Enter dynamic inputs into the user fields
		String userId = "User_"+randomNumber;
		excelUtility.writeDataToCellBasedOnTcIdAndHeader(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001", "User Id", userId);
		webDriverUtility.enterTextIntoElement(publicUserPage.getUserIdTextfield(), userId);

		String firstName = "Swaraj_"+randomNumber;
		excelUtility.writeDataToCellBasedOnTcIdAndHeader(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001", "First Name", firstName);
		webDriverUtility.enterTextIntoElement(publicUserPage.getFirstNameTextfield(), firstName);

		String email = firstName+"@gmail.com";
		excelUtility.writeDataToCellBasedOnTcIdAndHeader(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001", "Email", email);
		webDriverUtility.enterTextIntoElement(publicUserPage.getEmailTextfield(), email);

		String lastName = "PT_"+randomNumber;
		excelUtility.writeDataToCellBasedOnTcIdAndHeader(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001", "Last Name", lastName);
		webDriverUtility.enterTextIntoElement(publicUserPage.getLastNameTextfield(), lastName);

		String organization = "Org_"+randomNumber;
		excelUtility.writeDataToCellBasedOnTcIdAndHeader(IConstants.EXCEL_FILE_PATH, "Kohler_Testdata", "TC001", "Organization", organization);
		webDriverUtility.enterTextIntoElement(publicUserPage.getOrganizationTextfield(), organization);

		// Select role as user from the picklist
		webDriverUtility.waitForSeconds(2);
		//		webDriverUtility.clickElement(publicUserPage.getRoleOrJobTitlePckList());
		webDriverUtility.doubleClickOnElement(publicUserPage.getRoleOrJobTitlePickListOption());

		// Select Account status as Active from the picklist
		webDriverUtility.waitForSeconds(2);
		webDriverUtility.clickOnElementUsingJs(publicUserPage.getAccountStatusPicklist());
		webDriverUtility.clickOnElementUsingJs(publicUserPage.getAccountStatusPicklistOption(javaUtility.getValueFromTheMap(testCaseData, "Account Status")));

		// Click on Save button
		webDriverUtility.clickElement(publicUserPage.getSaveButton());

		// Verify toaster message is displayed
		assertionUtility.assertTrue(webDriverUtility.checkElementIsDisplayed(publicUserPage.getAlertToasterMessage()), "Data saved successfully.");

		
		
	}
}