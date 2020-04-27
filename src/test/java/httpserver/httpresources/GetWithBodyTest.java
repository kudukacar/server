package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class GetWithBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndBodyHelloworld() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        response.body = "Hello world";
        Resource resource = new GetWithBody();

        assertThat(response, samePropertyValuesAs(resource.act()));
    }
}