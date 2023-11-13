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

    /**
     * Наша задача — написать цикл, которая будет создавать запросы в цикле,
     * каждый раз читая URL для редиректа из нужного заголовка.
     * И так, пока мы не дойдем до ответа с кодом 200..
     */
    @Test
    public void longRedirect() {

        String url = "https://playground.learnqa.ru/api/long_redirect";
        while (url != null) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .get(url)
                    .andReturn();
            String location = response.getHeader("Location");
            int statusCode = response.getStatusCode();
            url = location;
            System.out.println("Ссылка на редирект - " + url + ", статус код " + statusCode);

        }

    }
}

