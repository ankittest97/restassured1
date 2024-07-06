package files;

public class payload {

	public static String AddPlace() // if u make this method as static u can call 
	//this method without creating object of this class , u can call via classname.methodname
	//if u don't make static u need to create object of payload class and using that object u need to call
	
	{
		return "{\r\n" +
				 "\"location\": {\r\n" +
				 "\"lat\": -38.383494,\r\n" +
				 "\"lng\": 33.427362\r\n" +
				 "},\r\n" +
				 "\"accuracy\": 50,\r\n" +
				 "\"name\": \"my test1.4\",\r\n" +
				 "\"phone_number\": \"(+91) 983 893 3937\",\r\n" +
				 "\"address\": \"259, side layout, cohen 09\",\r\n" +
				 "\"types\": [\r\n" +
				 "\"shoe park\",\r\n" +
				 "\"shop\"\r\n" +
				 "],\r\n" +
				 "\"website\": \"http://google.com\",\r\n" +
				 "\"language\": \"French-IN\"\r\n" +
				 "}";   
	}
	
}
