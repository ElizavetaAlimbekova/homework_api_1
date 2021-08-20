import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class APIReqresTest {

    @Test
    void create () {

        //     request https://reqres.in/api/users
        //      data {
        //      "name": "morpheus",
        //        "job": "leader"
        //            }
        //      response  {
        //       "name": "morpheus",
        //       "job": "leader",
        //       "id": "737",
        //       "createdAt": ""2021-08-20T17:40:23.431Z""
        //                 }
        //       status    201

        given ()
                .contentType (JSON)
                .body ("{\"name\": \"morpheus\"," +
                        "\"job\": \"leader\"}")
                .when ()
                .post ("https://reqres.in/api/users")
                .then ()
                .statusCode (201)
                .body ("name", is ("morpheus"), "job", is ("leader"));
    }

    @Test
    void createNegative () {
        given ()
                .contentType (JSON)
                .body ("{\"job\"}")
                .when ()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode (400);

    }

    @Test
    void registerSuссessful () {


        given ()
                .contentType (JSON)
                .body ("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when ()
                .post ("https://reqres.in/api/register")
                .then ()
                .statusCode (200)
                .body ( "token", is ("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void update(){

        given ()
                .contentType (JSON)
                .body ("{\"name\": \"morpheus\"," +
                        "\"job\": \"zion resident\"}")
                .when ()
                .put ("https://reqres.in/api/users/2")
                .then ()
                .statusCode (200)
                .body ( "name", is ("morpheus"), "job",is("zion resident"));

    }

    @Test
    void singleUser(){
        given ()
                .contentType (JSON)
                .when ()
                .get ("https://reqres.in/api/users/2")
                .then ()
                .statusCode (200);

    }

    @Test
    void delete(){
        given ()
                .contentType (JSON)
                .when()
                .delete ("https://reqres.in/api/users/2")
                .then ()
                .statusCode (204);
    }


}

