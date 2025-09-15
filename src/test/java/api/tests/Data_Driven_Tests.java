package api.tests;

import api.endpoints.User_endpoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import api.utilities.DataProviders;
@Listeners(api.utilities.ExtentReportManager.class)
public class Data_Driven_Tests {
    @Test(priority = 1, dataProvider = "data", dataProviderClass = DataProviders.class )
    public void testPostUser(String UserID, String UserName, String FirstName, String Lastname, String Email, String Password, String Phone){
        User payload = new User();
        payload.setId(Integer.parseInt(UserID));
        payload.setUsername(UserName);
        payload.setFirstName(FirstName);
        payload.setLastName(Lastname);
        payload.setEmail(Email);
        payload.setPassword(Password);
        payload.setPhone(Phone);
        Response response = User_endpoints.create_User(payload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2, dataProvider = "Usernames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String UserName){
        Response response = User_endpoints.delete_User(UserName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
