package com.kingfisher.test.kdt.dictionary;

import com.kingfisher.test.kdt.BaseKeyword;
import com.kingfisher.test.kdt.MyProjectKeywordManager;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class CountriesKeywords extends BaseKeyword<MyProjectKeywordManager> {

    private static final String GET_ALL = "/get/all";
    private static final String GET_ISO_CODE = "/get/iso2code/{alpha2_code}";
    private static final String GET_COUNTRY_NAME = "search?text={countryName}";
    private static final String GET_ERROR_URI = "/get/$%%$#//error";

    public CountriesKeywords(MyProjectKeywordManager keywordManager, Logger log) {
        super(keywordManager, log);
    }

    @Step
    public MyProjectKeywordManager getAllCountriesCodes() {
        Response response = given()
                .when().get(GET_ALL);
        rememberRestAssuredResponse(response);
        return keywordManager;
    }

    @Step
    public MyProjectKeywordManager getIsoCode(String alpha2_code) {
        Response response = given()
                .when().get(GET_ISO_CODE, alpha2_code);
        rememberRestAssuredResponse(response);
        return keywordManager;
    }

    @Step
    public MyProjectKeywordManager getCountryByName (String countryName) {
        Response response = given()
                .when().get(GET_COUNTRY_NAME, countryName);
        rememberRestAssuredResponse(response);
        return keywordManager;
    }

    @Step
    public MyProjectKeywordManager getBrokenURI() {
        Response response = given()
                .when().get(GET_ERROR_URI);
        rememberRestAssuredResponse(response);
        return keywordManager;
    }

    @Step
    public MyProjectKeywordManager getResponseParsed(String countryName) {
        Response response = given()
                .when().get(GET_COUNTRY_NAME, countryName).as(Response.class);
        System.out.println(response.toString());
        //rememberRestAssuredResponse(response);


        return keywordManager;
    }






}
