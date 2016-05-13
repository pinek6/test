package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import admin.navigation.LeftNav;
import admin.socialQandA.ListOfUnansweredQuestions;

public class SocialQandATests extends LoadBrowser {	
	
	private String SOCIAL_QandA_PAGE_HEADER = new LocaleStrings().getString("SOCIAL_Q&A_PAGE_HEADER");

	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Social Q&A Page displays")
	public void SocialQandAPageDisplays()
	{			
		/* Click Social Q & A in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		ListOfUnansweredQuestions listOfUnansweredQuestionsPage = leftnav.clickSocialQandA();
		
		/* Verify the correct page header appears and the table of Unanswered Questions appears */
		Assert.assertEquals(listOfUnansweredQuestionsPage.getHeader(), SOCIAL_QandA_PAGE_HEADER);
		Assert.assertTrue(listOfUnansweredQuestionsPage.isListOfUnansweredQuestionsTableDisplayed());
		
		/* Verify each cell in the List Of Unanswered Questions table is populated */
		for (int i = 1; i <=listOfUnansweredQuestionsPage.getCountOfUnansweredQuestionsDisplayed(); i++)
		{
			Assert.assertTrue(listOfUnansweredQuestionsPage.isNthRemindLinkDisplayed(i),"Remind link failed to display for row " + i + " in the List of Unanswered Questions");
			//Assert.assertTrue(listOfUnansweredQuestionsPage.isNthSubjectDisplayed(i),"Subject text failed to display for row " + i + " in the List of Unanswered Questions");
			//Assert.assertTrue(listOfUnansweredQuestionsPage.isNthSubjectAreaDisplayed(i),"Subject Area text failed to display for row " + i + " in the List of Unanswered Questions");
			Assert.assertTrue(listOfUnansweredQuestionsPage.isNthQuestionDateDisplayed(i),"Question date text failed to display for row " + i + " in the List of Unanswered Questions");
		}		
	}
	
	@Test(groups={"FullRegression"}, description="Verifies the Show 10 link works on the Social Q&A Page")
	public void Show10LinkOnSocialQandAPageFunctions()
	{			
		/* Click Social Q & A in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		ListOfUnansweredQuestions listOfUnansweredQuestionsPage = leftnav.clickSocialQandA();
		
		/* Click the Show 10 link to only show 10 questions */
		listOfUnansweredQuestionsPage.clickShow10();
		
		/* Get the count of currently showing questions */
		int countOfUnanswerQuestionsDisplayed = listOfUnansweredQuestionsPage.getCountOfUnansweredQuestionsDisplayed();
		
		/* Verify the count is 10 or less */
		Assert.assertTrue(countOfUnanswerQuestionsDisplayed<=10, "Expected number of questions showing should be 10 or less.  Actual number showing is "+ countOfUnanswerQuestionsDisplayed);
	
		/* Verify each cell in the List Of Unanswered Questions table is populated */
		for (int i = 1; i <=countOfUnanswerQuestionsDisplayed; i++)
		{
			Assert.assertTrue(listOfUnansweredQuestionsPage.isNthRemindLinkDisplayed(i),"Remind link failed to display for row " + i + " in the List of Unanswered Questions");
			Assert.assertTrue(listOfUnansweredQuestionsPage.isNthQuestionDateDisplayed(i),"Question date text failed to display for row " + i + " in the List of Unanswered Questions");
		}
	}
	
}

