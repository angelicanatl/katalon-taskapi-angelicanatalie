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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.Keys as Keys

// Load test data from CSV
testData = findTestData('Data Files/Test Data/TD-photos')

// Send GET request
response = WS.sendRequest(findTestObject('Object Repository/Obj-req-api/Obj-photos/GET photos'))

// Verify the status code is 200
WS.verifyResponseStatusCode(response, 200)

// Parse the JSON response
jsonResponse = new JsonSlurper().parseText(response.getResponseText())

// Loop through each row in the test data
for (int i = 1; i <= testData.getRowNumbers(); i++) {
	// Get data from the current row
	id = testData.getValue('id', i)
	expectedAlbumId = testData.getValue('albumId', i)
	expectedTitle = testData.getValue('title', i)
	expectedUrl = testData.getValue('url', i)
	expectedThumbnail = testData.getValue('thumbnailUrl', i)
	
	KeywordUtil.logInfo("Testing photo with id ${id}")

	// Find the index of the photo in the JSON response
	photoIndex = jsonResponse.findIndexOf { photo -> photo.id == id.toInteger() }

	if (photoIndex != -1) {
		// Verify userId
		WS.verifyElementPropertyValue(response, "[$photoIndex].albumId", expectedAlbumId.toInteger())
		
		// Verify title
		WS.verifyElementPropertyValue(response, "[$photoIndex].title", expectedTitle)
		
		// Verify url
		WS.verifyElementPropertyValue(response, "[$photoIndex].url", expectedUrl)
		
		// Verify thumbnail url
		WS.verifyElementPropertyValue(response, "[$photoIndex].thumbnailUrl", expectedThumbnail)

		KeywordUtil.logInfo("Photo properties verified successfully for photo id ${id}.")
	} else {
		KeywordUtil.logInfo("Photo with id ${id} not found in response.")
	}
}

