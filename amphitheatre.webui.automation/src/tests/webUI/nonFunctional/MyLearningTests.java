package tests.webUI.nonFunctional;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.mylearning.currentActivities.CurrentActivities;
import webUI.home.mylearning.progress.Progress;

public class MyLearningTests extends LoadBrowser{

	/* Page Headers */
	private String progressPageHeader = new LocaleStrings().getString("PROGRESS_PAGE_HEADER");
	private String myLearningPageHeader = new LocaleStrings().getString("MY_LEARNING_PAGE_HEADER");
	private String currentActivitiesPageHeader = new LocaleStrings().getString("CURRENT_ACTIVITIES_PAGE_HEADER");
	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void beforeMethod()
    {
		getDriverAndLogin();
    }
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the My Learning page loads successfully")
	public void MyLearningPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverMyLearning();		
		navigation.clickMyLearning();
	
		CurrentActivities myLearningPage = new CurrentActivities(getDriver());
		
		Assert.assertEquals(myLearningPage.getHeader(),myLearningPageHeader,"Incorrect header on the My Learning page");
		Assert.assertTrue(myLearningPage.isTitleDisplayed(),"Guided Learning Paths title failed to load");
		Assert.assertTrue(myLearningPage.isCurrentActivitesTabDisplayed(),"Current Activities tab is not displayed");
		Assert.assertTrue(myLearningPage.isCurrentActivitesTabSelected(),"Current Activities tab is not selected");
		Assert.assertTrue(myLearningPage.isProgressTabDisplayed(),"Progress tab is not displayed");
		Assert.assertFalse(myLearningPage.isProgressTabSelected(),"Progress tab is selected in error");
		//Assert.assertTrue(myLearningPage.getCountOfGuidedLearningPaths()>0,"No Guided Learning Paths found");
		
		/* Loop through each of the Guided Learning Paths and verify UI elements appear */
		for (int i = 1; i <=myLearningPage.getCountOfGuidedLearningPaths(); i++)
		{
			Assert.assertTrue(myLearningPage.isGuidedLearningPathTitleDisplayed(i),"Title failed to display for Guided Learning Path #" + i);
			Assert.assertTrue(myLearningPage.isGuidedLearningPathModuleCountDisplayed(i),"Module Count failed to display for Guided Learning Path #" + i);
			Assert.assertTrue(myLearningPage.isGuidedLearningPathPercentageCompleteDisplayed(i),"Percentage Complete failed to display for Guided Learning Path #" + i);
		}
	}

	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Current Activities page loads successfully")
	public void CurrentActivitiesPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverMyLearning();		
		CurrentActivities currentActivitiesPage = navigation.clickCurrentActivities();

		Assert.assertEquals(currentActivitiesPage.getHeader(),currentActivitiesPageHeader,"Incorrect header on the Current Activities page");
	}

	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Progress page loads successfully")
	public void ProgressPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverMyLearning();
		Progress progressPage = navigation.clickProgress();
	
		Assert.assertEquals(progressPage.getHeader(),progressPageHeader,"Incorrect header on the Progress page");
		Assert.assertTrue(progressPage.isCurrentActivitesTabDisplayed(),"Current Activities tab is not displayed");
		Assert.assertFalse(progressPage.isCurrentActivitesTabSelected(),"Current Activities tab is selected in error");
		Assert.assertTrue(progressPage.isProgressTabDisplayed(),"Progress tab is not displayed");
		Assert.assertTrue(progressPage.isProgressTabSelected(),"Progress tab is not selected");
		Assert.assertTrue(progressPage.isPrintIconDisplayed(),"Print icon not displaying");
	}
}
