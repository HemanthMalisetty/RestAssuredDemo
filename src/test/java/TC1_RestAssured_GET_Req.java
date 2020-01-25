import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC1_RestAssured_GET_Req {
    @Test
    public void getWeatherDetails(){

        //Specify Base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        //Creating Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println("Response body is : " + responseBody);


        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is incorrect");
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Status line is incorrect");
    }
    @Test
    public void postRequest(){
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        RequestSpecification httpRequest = RestAssured.given();

        //Request Payload sending along with POST Request
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Hemanth");
        requestParams.put("LastName", "Kumar");
        requestParams.put("UserName", "Hemanth");
        requestParams.put("Password", "HemanthXYZ");
        requestParams.put("Email", "hemanth@TestMail.com");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString());


        Response response = httpRequest.request(Method.POST, "/register");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not matching");
        System.out.println(response.jsonPath().get("SuccessCode"));
        Assert.assertEquals(response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS", "Success message is incorrect");
    }
    @Test
    public void getRequestDemoTwo(){
        RestAssured.baseURI = "https://reqres.in";

        RequestSpecification request = RestAssured.given();

        Response response = request.request(Method.GET, "/api/users?page=2");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Incorrect status code");
    }
    @Test
    public void getDemoThree(){

        RestAssured.baseURI = "https://maps.googleapis.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Incorrect status code");
        System.out.println(response.getHeader("Content-Encoding"));
        Assert.assertEquals(response.getHeader("Content-Encoding"), "gzip", "Incorrect Contest-Encoding in response header");
        System.out.println(response.getHeader("Content-Type"));
        Assert.assertEquals(response.getHeader("Content-Type"), "application/xml; charset=UTF-8", "Incorrect Content type in response header");
        System.out.println(response.getHeader("Server"));
        Assert.assertEquals(response.getHeader("Server"), "scaffolding on HTTPServer2", "Incorrect server in response header");
    }
}
