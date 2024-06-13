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
testData = findTestData('Data Files/Test Data/TD-photos')


// Initialize properties that going to be checked for new posted album
newAlbumId = 1;
newTitle = 'new album';
newUrl =  "https://via.placeholder.com/600/92c952";
newThumbnail = 'https://via.placeholder.com/150/92c952';

// Send the POST request
response = WS.sendRequest(findTestObject('Object Repository/Obj-req-api/Obj-photos/POST photo'))

// Verify the response status code is 201
WS.verifyResponseStatusCode(response, 201)

// Verify the properties of the posted photo
WS.verifyElementPropertyValue(response, 'albumId', newAlbumId)
WS.verifyElementPropertyValue(response, 'title', newTitle)
WS.verifyElementPropertyValue(response, 'url', newUrl)
WS.verifyElementPropertyValue(response, 'thumbnailUrl', newThumbnail)

// Log verification success
KeywordUtil.logInfo('New posted photo properties verified successfully.')
