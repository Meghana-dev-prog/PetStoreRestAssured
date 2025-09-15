package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class User_endpoints2 {

    static ResourceBundle get_URL(){
        ResourceBundle resource = ResourceBundle.getBundle("routes");
        return resource;
    }
    public static Response create_User(User payload){
        String post_url = get_URL().getString("post_url");
        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(payload).
                when().
                post(post_url);
        return response;
    }
    public static Response read_User(String Username){
        String get_url = get_URL().getString("get_url");
        Response response = given().
                pathParam("username", Username).
                when().
                get(get_url);
        return response;
    }
    public static Response update_User(User payload, String Username){
        String put_url = get_URL().getString("put_url");
        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(payload).
                pathParam("username", Username).
                when().
                put(put_url);
        return response;
    }
    public static Response delete_User(String Username){
        String delete_url = get_URL().getString("delete_url");
        Response response = given().
                pathParam("username", Username).
                when().
                delete(delete_url);
        return response;
    }
}
