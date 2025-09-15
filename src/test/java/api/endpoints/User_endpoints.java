package api.endpoints;
import static io.restassured.RestAssured.given;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_endpoints {
    public static Response create_User(User payload){
        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(payload).
                when().
                post(Routes.post_url);
        return response;
    }
    public static Response read_User(String Username){
        Response response = given().
                pathParam("username", Username).
                when().
                get(Routes.get_url);
        return response;
    }
    public static Response update_User(User payload, String Username){
        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(payload).
                pathParam("username", Username).
                when().
                put(Routes.put_url);
        return response;
    }
    public static Response delete_User(String Username){
        Response response = given().
                pathParam("username", Username).
                when().
                delete(Routes.delete_url);
        return response;
    }

}
