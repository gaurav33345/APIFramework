package apiAutomationeleven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.api.Util.ExtentReport;
import com.automation.api.Util.Helper;
import com.automation.api.Util.ReadTestData;
import com.automation.api.Util.configReader;
import com.googleAddPlacePayLoadPOJO.AddPlacePayload;
import com.googleAddPlacePayLoadPOJO.Location;
import com.googleAddPlaceResponsePOJO.ResponseBody;

import core.BaseTest;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class GoogleAddPlaceTest extends BaseTest {
	@Test(priority=1,description = "Test1")
	@Parameters("fname")
	  public void AddPlace(String fname) throws Exception {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
				" validate post method");
	
		  Location lc = new Location();
		  lc.setLat(-38.383494);
		  //lc.setLat(ReadTestData.getTCData("testdata.json","test1.urigetuser"));
		  lc.setLng(33.427362);
		  AddPlacePayload add = new AddPlacePayload();
		  add.setLocation(lc);
		  add.setAccuracy(50);
		  add.setName(ReadTestData.getTCData(fname,"test2.Name"));
		  add.setPhone_number(ReadTestData.getTCData(fname,"test2.Phonenumber"));
		  add.setAddress(ReadTestData.getTCData(fname,"test2.Address"));
		  List<String>type = new ArrayList<String>();
		  type.add(ReadTestData.getTCData(fname,"test2.type1"));
		  type.add(ReadTestData.getTCData(fname,"test2.type2"));
		  add.setTypes(type);
		  add.setWebsite(ReadTestData.getTCData(fname,"test2.website"));
		  add.setLanguage(ReadTestData.getTCData(fname,"test2.language"));
		 Response resp = given().header("Content-Type","application/json").queryParam("key", "qaclick123").body(add).expect().defaultParser(Parser.JSON)
				 .when().post(configReader.baseURI+"/maps/api/place/add/json");
		ResponseBody res = resp.as(ResponseBody.class);
		System.out.println("=====================================Response========================================================");
		System.out.println("Status: "+res.getStatus());
		System.out.println("Place ID: "+res.getPlace_id());
		System.out.println("Scope: "+res.getScope());
		System.out.println("Reference: "+res.getReference());
		System.out.println("ID: "+res.getId());
	  }
	@Test
	@Parameters("fname")
	public void Print(String fname) throws Exception, ParseException {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
				" Print");
		//System.out.println(ReadTestData.getTestData("urigetuser"));
		 //String Path="G:\\Sidharth Session\\APIProject\\apiAutomationeleven\\Resources\\TestData\\testdata.json";
		//String body = ReadTestData.readJson(Path);
		System.out.println("USERID: "+ReadTestData.getTCData(fname,"test1.urigetuser"));
		System.out.println("Name: "+ReadTestData.getTCData(fname,"test2.Name"));
		System.out.println("Number: "+ReadTestData.getTCData(fname,"test2.Phonenumber"));
		System.out.println("Address: "+ReadTestData.getTCData(fname,"test2.Address"));
		System.out.println("Type1: "+ReadTestData.getTCData(fname,"test2.type1"));
		System.out.println("Type2: "+ReadTestData.getTCData(fname,"test2.type2"));
		System.out.println("Website: "+ReadTestData.getTCData(fname,"test2.website"));
		System.out.println("Language: "+ReadTestData.getTCData(fname,"test2.language"));
	}
	@Test
	@Parameters("fname")
	public void SpecBuilder(String fname) throws Exception, ParseException {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
				" Print");
		 Location lc = new Location();
		  lc.setLat(-38.383494);
		  lc.setLng(33.427362);
		  AddPlacePayload add = new AddPlacePayload();
		  add.setLocation(lc);
		  add.setAccuracy(50);
		  add.setName(ReadTestData.getTCData(fname,"test2.Name"));
		  add.setPhone_number(ReadTestData.getTCData(fname,"test2.Phonenumber"));
		  add.setAddress(ReadTestData.getTCData(fname,"test2.Address"));
		  List<String>type = new ArrayList<String>();
		  type.add(ReadTestData.getTCData(fname,"test2.type1"));
		  type.add(ReadTestData.getTCData(fname,"test2.type2"));
		  add.setTypes(type);
		  add.setWebsite(ReadTestData.getTCData(fname,"test2.website"));
		  add.setLanguage(ReadTestData.getTCData(fname,"test2.language"));
		  RequestSpecification res=given().spec(Helper.requestBuilder(configReader.baseURI))
				  .body(add);
		  Response response =res.when().post(configReader.addPlaceBasePath);
		  
		  ResponseBody resbody = response.as(ResponseBody.class);
		System.out.println("=====================================Response========================================================");
		System.out.println("Status: "+resbody.getStatus());
		System.out.println("Place ID: "+resbody.getPlace_id());
		System.out.println("Scope: "+resbody.getScope());
		System.out.println("Reference: "+resbody.getReference());
		System.out.println("ID: "+resbody.getId());
		assertEquals(response.getStatusCode(),201);
	}
}
