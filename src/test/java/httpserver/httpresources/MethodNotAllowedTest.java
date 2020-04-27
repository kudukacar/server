package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class MethodNotAllowedTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus405AndAllowHeaders() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "405 Method Not Allowed";
        response.headers.add("Allow: HEAD, OPTIONS");
        Resource resource = new MethodNotAllowed();

        assertThat(response, samePropertyValuesAs(resource.act()));
    }
}