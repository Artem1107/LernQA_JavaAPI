import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;


public class HelloWordTest {

    @Test
    public void testHelloWord() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.print();
    }

    /**
     * Наша задача вывести текст второго сообщения.
     */
    @Test
    public void printSecondMassage() {

        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        List<String> messages = response.getList("messages.message");
        String message2 = messages.get(1);
        System.out.println(message2);
    }

    /**
     * Наша задача — распечатать адрес, на который редиректит указанные URL.
     */
    @Test
    public void redirect() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String location = response.getHeader("Location");
        System.out.println("Ссылка на редирект - " + location);

    }

}
