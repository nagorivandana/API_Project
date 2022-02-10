package com.cfs.stepdefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;

import com.cfs.util.Constants;
import com.cfs.util.Utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This class is used to glue the steps mentioned in the feature file
 * 
 * @author
 *
 */

public class CFSStepDefinition {
	private static Logger log = LogManager.getLogger(CFSStepDefinition.class.getName());
	private String origin;
	private String statusCode;
	private String translateText;

	/**
	 * This method send the api call to retrieve the origin of a word
	 * 
	 * @param word
	 */

	@Given("^I find the origin of word \"(.*)\"$")
	public void getOriginCode(String word) {
		RestAssured.baseURI = Constants.BASE_URI;
		log.info("Before calling the API setting the Base URI");
		Response resp = given().header(Constants.APP_ID, Constants.APP_ID_VALUE).header(Constants.APP_KEY, Constants.APP_KEY_VALUE)
				.queryParam(Constants.FIELDS, Constants.FIELDS_VALUE)
				.when()
				.get(Constants.RESOURCE_URI_ENTERIES + "/{" + Constants.SOURCE_LANG + "}/" + "{" + Constants.WORD_ID + "}", Constants.SOURCE_LANG_VALUE, Constants.WORD_VALUE)
				.then().extract().response();
		log.info("Response : " + resp.asString());
		JsonPath js = Utilities.rawToJson(resp);
		System.out.println(js.get("results.lexicalEntries.entries.etymologies").toString());
		if (null != js && null != js.get("results.lexicalEntries.entries.etymologies").toString()) {
			this.origin = js.get("results.lexicalEntries.entries.etymologies").toString();
		} else {
			log.info("No response from the api. Please check");
		}
	}
	/**
	 * This method send the api call to verify status is 404
	 *
	 * @param word
	 */

	@Given("^I provide the word \"(.*)\"$")
	public void getStatusResponseCode(String word) {
		RestAssured.baseURI = Constants.BASE_URI;
		log.info("Before calling the API setting the Base URI");
		int respCode = given().header(Constants.APP_ID,Constants.APP_ID_VALUE).header(Constants.APP_KEY,Constants.APP_KEY_VALUE)
				.when()
				.get(Constants.RESOURCE_URI_ENTERIES +"/{"+Constants.SOURCE_LANG +"}/"+"{"+Constants.WORD_ID+"}",Constants.SOURCE_LANG_VALUE, word)
				.then().extract().response().getStatusCode();
		this.statusCode= Integer.toString(respCode);
		//Assert.assertEquals("404",respCode.toString());
		log.info("Response : " +respCode);

	}
	/**
	 * This method send the api call to verify status is 400
	 *
	 * @param word
	 * @param grammaticalVal
	 */
	@Given("^I provide with word \"(.*)\" and grammatical feature \"(.*)\"$")
	public void getStatusCode(String word, String grammaticalVal) {
		RestAssured.baseURI = Constants.BASE_URI;
		log.info("Before calling the API setting the Base URI");
		int respCode = given().header(Constants.APP_ID,Constants.APP_ID_VALUE).header(Constants.APP_KEY,Constants.APP_KEY_VALUE)
				.queryParam(Constants.GRAMMATICAL_FEATURES,grammaticalVal)
				.when()
				.get(Constants.RESOURCE_URI_ENTERIES +"/{"+Constants.SOURCE_LANG +"}/"+"{"+Constants.WORD_ID+"}",Constants.SOURCE_LANG_VALUE, Constants.WORD_VALUE)
				.then().extract().response().getStatusCode();
		this.statusCode= Integer.toString(respCode);
		log.info("Response : " +respCode);

	}


	@Then("^I validate the word origin as \"(.*)\"$")
	public void originWord(String origin) {
		Assert.assertEquals(this.origin, origin);
	}

	@Then("^I validate the response status as \"(.*)\"$")
	public void responseValidation(String responseCode) {
		Assert.assertEquals(this.statusCode, responseCode);
	}

	@Then("^I validate the translation text as \"(.*)\"$")
	public void translationTextValidation(String translate_text) {
		Assert.assertEquals(this.translateText, translate_text);
	}
	/**
	 * This method send the api call to translate word to language
	 *
	 * @param word
	 * @param lang
	 */
	@Given("^I translate word \"(.*)\" to language \"(.*)\"$")
	public void getTranslation(String word, String lang) {
		RestAssured.baseURI = Constants.BASE_URI;
		log.info("Before calling the API setting the Base URI");
		Response resp = given()
				.header(Constants.APP_ID, Constants.APP_ID_VALUE).header(Constants.APP_KEY,Constants.APP_KEY_VALUE)
				.when()
				.get(Constants.RESOURCE_URI_TRANSLATIONS+"{/"+Constants.TRANSLATE_SOURCE_LANG+"}/{"+Constants.TARGET_LANG+"}/{"+Constants.WORD_ID+"}",Constants.TRANSLATE_SOURCE_LANG_VALUE, lang,word).then().extract().response();
		log.info("Response : " + resp.asString());

	}
	/**
	 * This method send the api call to provide the translation word
	 *
	 * @param word
	 */
	@Given("^I provide the translation word \"(.*)\"$")
	public void getTranslationStatusResponseCode(String word) {
		RestAssured.baseURI = Constants.BASE_URI;
		log.info("Before calling the API setting the Base URI");
		int respCode =given()
				.header(Constants.APP_ID, Constants.APP_ID_VALUE).header(Constants.APP_KEY,Constants.APP_KEY_VALUE)
				.queryParam(Constants.TARGET_QUERY_ID,Constants.TARGET_QUERY_ID_VALUE)
				.when()
				.get(Constants.RESOURCE_URI_TRANSLATIONS+"/{"+Constants.WORD_ID+"}",word)
				.then().extract().response().getStatusCode();
		this.statusCode= Integer.toString(respCode);
		//Assert.assertEquals("404",respCode.toString());
		log.info("Response : " +respCode);

	}

	/**
	 * This method send the api call to get status code
	 *
	 * @param word
	 * @param targetLang
	 * @param grammaticalVal
	 */
	@Given("^I provide with translation word \"(.*)\",language \"(.*)\" and grammatical feature \"(.*)\"$")
	public void getTranslationStatusCode(String word,String targetLang, String grammaticalVal) {
		RestAssured.baseURI = Constants.BASE_URI;
		int respCode = given().header(Constants.APP_ID,Constants.APP_ID_VALUE).header(Constants.APP_KEY,Constants.APP_KEY_VALUE)
				.queryParam(Constants.GRAMMATICAL_FEATURES,grammaticalVal)
				.when()
				.get(Constants.RESOURCE_URI_TRANSLATIONS+"{/"+Constants.TRANSLATE_SOURCE_LANG+"}/{"+Constants.TARGET_LANG+"}/{"+Constants.WORD_ID+"}",Constants.TRANSLATE_SOURCE_LANG_VALUE, targetLang,word)
				.then().extract().response().getStatusCode();
		this.statusCode= Integer.toString(respCode);
		log.info("Response : " +respCode);

	}

}
