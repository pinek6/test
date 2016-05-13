package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import admin.learningAdministration.LearningAdministration;
import admin.learningAdministration.LearningAdministrationCourses;
import admin.learningAdministration.LearningAdministrationCurricula;
import admin.learningAdministration.LearningAdministrationCurriculumDetails;
import admin.navigation.LeftNav;

public class LearningAdministrationTests extends LoadBrowser {	
	
	private String LEARNING_ADMINISTRATION_PAGE_HEADER = new LocaleStrings().getString("LEARNING_ADMINISTRATION_PAGE_HEADER");
	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Content Administration page displays")
	public void LearningAdministrationPageDisplays()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();
		
		/* Verify the correct page header appears */
		Assert.assertEquals(learningAdministrationPage.getHeader(), LEARNING_ADMINISTRATION_PAGE_HEADER);			
	}
	
	@Test(description="Verifies curricula appear in the CURRICULA tab of Learning Administration")
	public void CirriculaPageDisplays()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();		

		/* Click the Curricula tab */
		LearningAdministrationCurricula learningAdministrationCirriculaPage =learningAdministrationPage.clickCurriculaTab();
		
		/* Click go */
		learningAdministrationCirriculaPage.clickGo();
		
		/* Get the count of curricula currently displayed */
		int countOfCurriculaDisplayed = learningAdministrationCirriculaPage.getCountOfCurriculaInCurriculaTable();
		
		/* Verify >0 curricula are displayed */
		Assert.assertTrue(countOfCurriculaDisplayed>0, "No curricula displayed");
		
		/* Verify each cell of the table is displayed */
		for (int i = 1;i <= countOfCurriculaDisplayed; i++)
		{
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthCheckboxInCurriculaTableDisplayed(i), "Checkbox failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthTitleInCurriculaTableDisplayed(i), "Title failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthVersionInCurriculaTableDisplayed(i), "Version failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthStatusInCurriculaTableDisplayed(i), "Status failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthTypeInCurriculaTableDisplayed(i), "Type failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthSequencedInCurriculaTableDisplayed(i), "Sequenced failed to display for row " + i + " of the Curricula table");
			Assert.assertTrue(learningAdministrationCirriculaPage.isNthDescriptionInCurriculaTableDisplayed(i), "Description failed to display for row " + i + " of the Curricula table");
		}
		
		/* Verify UI elements appear */
		Assert.assertTrue(learningAdministrationCirriculaPage.isSearchForDisplayed(),"Search For field failed to display on Curricula page");
		Assert.assertTrue(learningAdministrationCirriculaPage.isGoButtonDisplayed(),"go button failed to display on Curricula page");
		Assert.assertTrue(learningAdministrationCirriculaPage.isResultsPerPageDisplayed(),"Results Per Page dropdown failed to display on Curricula page");
	}
	
	@Test(description="Verifies curricula can be searched for in the CURRICULA tab of Learning Administration")
	public void CirriculaSearchFunctions()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();		

		/* Click the Curricula tab */
		LearningAdministrationCurricula learningAdministrationCirriculaPage =learningAdministrationPage.clickCurriculaTab();
		
		/* Click go */
		learningAdministrationCirriculaPage.clickGo();			
		
		/* Get the count of curricula currently displayed */
		int countOfCurriculaDisplayed = learningAdministrationCirriculaPage.getCountOfCurriculaInCurriculaTable();
		
		/* Verify >0 curricula are displayed */
		Assert.assertTrue(countOfCurriculaDisplayed>0, "No curricula displayed");
		
		String firstCurricula = learningAdministrationCirriculaPage.getNthTitleInCurriculaTable(1);

		/* Click Learning Administration in the left hand navigation */
		learningAdministrationPage = leftnav.clickLearningAdministration();		

		/* Click the Curricula tab */
		learningAdministrationCirriculaPage =learningAdministrationPage.clickCurriculaTab();

		learningAdministrationCirriculaPage.enterSearchFor(firstCurricula);
		learningAdministrationCirriculaPage.clickGo();
		
		/* Get the count of curricula currently displayed */
		countOfCurriculaDisplayed = learningAdministrationCirriculaPage.getCountOfCurriculaInCurriculaTable();
		
		/* Verify >0 curricula are displayed */
		Assert.assertTrue(countOfCurriculaDisplayed>0, "No curricula displayed");		
	}
	
	@Test(description="Verify Curriculum page displays")
	public void CurriculumPageDisplays()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();		

		/* Click the Curricula tab */
		LearningAdministrationCurricula learningAdministrationCurriculaPage =learningAdministrationPage.clickCurriculaTab();
		
		/* Click go */
		learningAdministrationCurriculaPage.clickGo();			
		
		/* Click the first curricula in the table */
		String firstCurricula = learningAdministrationCurriculaPage.getNthTitleInCurriculaTable(1);
		LearningAdministrationCurriculumDetails learningAdministrationCurriculumDetailsPage = learningAdministrationCurriculaPage.clickNthTitleInCurriculaTable(1);		
		
		/* Verify UI elements appear */
		Assert.assertTrue(learningAdministrationCurriculumDetailsPage.isEnrollButtonDisplayed(), "Enroll button failed to display on Curriculum page");
		Assert.assertTrue(learningAdministrationCurriculumDetailsPage.isBackButtonDisplayed(), "Back button failed to display on Curriculum page");
		Assert.assertTrue(learningAdministrationCurriculumDetailsPage.isCurriculumNameDisplayed(), "Curriculum Name failed to display on Curriculum page");
		Assert.assertTrue(learningAdministrationCurriculumDetailsPage.getCurriculumName().startsWith(firstCurricula),"Incorrect curriculum name, expected: " + firstCurricula + " but found:" + learningAdministrationCurriculumDetailsPage.getCurriculumName());
	}
	
	@Test(description="Verifies courses appear in the COURSES tab of Learning Administration")
	public void CoursesPageDisplays()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();
		
		/* Click the Courses tab */
		LearningAdministrationCourses learningAdministrationCoursesPage =learningAdministrationPage.clickCoursesTab();
		
		/* Click go */
		learningAdministrationCoursesPage.clickGo();
		
		/* Get the count of courses currently displayed */
		int countOfCoursesDisplayed = learningAdministrationCoursesPage.getCountOfCoursesInCoursesTable();
		
		/* Verify >0 courses are displayed */
		Assert.assertTrue(countOfCoursesDisplayed>0, "No courses displays");	
		for (int i = 1;i <= countOfCoursesDisplayed; i++)
		{
			Assert.assertTrue(learningAdministrationCoursesPage.isNthCheckboxInCoursesTableDisplayed(i), "Checkbox failed to display for row " + i + " of the Courses table");
			Assert.assertTrue(learningAdministrationCoursesPage.isNthTitleInCoursesTableDisplayed(i), "Title failed to display for row " + i + " of the Courses table");
			Assert.assertTrue(learningAdministrationCoursesPage.isNthStatusInCoursesTableDisplayed(i), "Status failed to display for row " + i + " of the Courses table");
			Assert.assertTrue(learningAdministrationCoursesPage.isNthOrganizationInCoursesTableDisplayed(i), "Organization failed to display for row " + i + " of the Courses table");
		}
		
		/* Verify UI elements appear */
		Assert.assertTrue(learningAdministrationCoursesPage.isSearchForDisplayed(),"Search For field failed to display on Courses page");
		Assert.assertTrue(learningAdministrationCoursesPage.isGoButtonDisplayed(),"go button failed to display on Courses page");
		Assert.assertTrue(learningAdministrationCoursesPage.isResultsPerPageDisplayed(),"Results Per Page dropdown failed to display on Courses page");
	}

	@Test(description="Verifies courses can be searched for in the COURSES tab of Learning Administration")
	public void CoursesSearchFunctions()
	{			
		/* Click Learning Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministration learningAdministrationPage = leftnav.clickLearningAdministration();
		
		/* Click the Courses tab */
		LearningAdministrationCourses learningAdministrationCoursesPage =learningAdministrationPage.clickCoursesTab();
		
		/* Click go */
		learningAdministrationCoursesPage.clickGo();
		
		/* Get the count of courses currently displayed */
		int countOfCoursesDisplayed = learningAdministrationCoursesPage.getCountOfCoursesInCoursesTable();
		
		/* Verify >0 curricula are displayed */
		Assert.assertTrue(countOfCoursesDisplayed>0, "No courses displayed");
		
		String firstCourse = learningAdministrationCoursesPage.getNthTitleInCoursesTable(1);

		/* Click Learning Administration in the left hand navigation */
		learningAdministrationPage = leftnav.clickLearningAdministration();		

		/* Click the Curricula tab */
		learningAdministrationCoursesPage =learningAdministrationPage.clickCoursesTab();

		learningAdministrationCoursesPage.enterSearchFor(firstCourse);
		learningAdministrationCoursesPage.clickGo();
		
		/* Get the count of curricula currently displayed */
		countOfCoursesDisplayed = learningAdministrationCoursesPage.getCountOfCoursesInCoursesTable();
		
		/* Verify >0 curricula are displayed */
		Assert.assertTrue(countOfCoursesDisplayed>0, "No courses displayed");
	}
	
}

