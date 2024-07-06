package restassured1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

//import files.Jsonpayload;



public class Base {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json")	
				//EqualTo is under hamcrest package
				.then().assertThat().statusCode(200).body("scope" , equalTo("APP")).
				header("server" , "Apache/2.4.52 (Ubuntu)")
	//in postman when u go to headers in response section, u will get server name
	// Add place--> update place with new address --> get place to validate if new address is present in response
		//equalTo comes under static package and eclipse will not show suggestion for static package
		//equalto is coming from hamcrest package //header used above is like o/p validation and on top its i/p validation.
		.extract().response().asString();
		
		System.out.println(response);
		
		//now we want to extract the response and do further validation
		//so how to extract json , there is a clas called JsonPath // it is a class which take string as input and converts to json
		//we need to parse the json and parsed value are now stored in js variable.
		JsonPath js = new JsonPath(response); //for parsing json //it wil take input as string i.e. response
		String placeid = js.getString("place_id"); //we are storing placeid value in a string placeid is in responce and we need to make use of that
		
		System.out.println(placeid); // we need to make use of placeid that's why we are storing in a variable called place id
		
		//update place
		String newAddress = "703 winter walk, USA";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"     //here we are executing the variable stored in placeid,since we need to validate with placeid so place id stored above will come here.
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
				
					// get place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString(); //jsonpath is a class that accepts string arguments
		//getplaceresponse stores json as string format		
		
		//there is class called json path, this class accepts string as an input, so that it has ability to
		//convert and parse into json  , js object can parse the convertd json
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress); //newAddress as stated above should match with actual address.
			
	
	}

}
