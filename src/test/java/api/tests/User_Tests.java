package api.tests;

import api.endpoints.User_endpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User_Tests {
    Faker faker;
    User user_payload;
    public Logger logger;
    @BeforeClass
    public void setUp(){
        faker = new Faker();
        user_payload = new User();
        user_payload.setId(faker.number().numberBetween(1, 1000000));
        user_payload.setUsername(faker.name().username());
        user_payload.setFirstName(faker.name().firstName());
        user_payload.setLastName(faker.name().lastName());
        user_payload.setEmail(faker.internet().safeEmailAddress());
        user_payload.setPassword(faker.internet().password(5,10));
        user_payload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void testPostUser(){
        logger.info("Starting testPostUser execution");
        Response response = User_endpoints.create_User(user_payload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.debug("Payload created successfully");
    }
    @Test(priority = 2)
    public void testGetUser() {
        logger.info("Starting testGetUser execution");
        Response response = User_endpoints.read_User(this.user_payload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("Starting testUpdateUser execution");
        user_payload.setFirstName(faker.name().firstName());
        user_payload.setLastName(faker.name().lastName());
        user_payload.setEmail(faker.internet().emailAddress());
        Response response = User_endpoints.update_User(this.user_payload,this.user_payload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("Starting testDeleteUser execution");
        Response response = User_endpoints.delete_User(this.user_payload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
