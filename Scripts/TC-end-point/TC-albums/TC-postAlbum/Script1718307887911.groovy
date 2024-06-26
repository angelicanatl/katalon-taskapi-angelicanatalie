import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Load test data from CSV
testData = findTestData('Data Files/Test Data/TD-albums')

// Initialize properties that going to be checked for new posted album
newUserId = 1;
newTitle = 'new album';
newAlbumId = testData.getRowNumbers()+1;

// Send the POST request
response = WS.sendRequest(findTestObject('Object Repository/Obj-req-api/Obj-albums/POST album'))

// Verify the response status code is 201
WS.verifyResponseStatusCode(response, 201)

// Verify the properties of the created album
WS.verifyElementPropertyValue(response, 'userId', newUserId)
WS.verifyElementPropertyValue(response, 'title', newTitle)
WS.verifyElementPropertyValue(response, 'id', newAlbumId)

// Log verification success
KeywordUtil.logInfo('New posted album properties verified successfully.')
