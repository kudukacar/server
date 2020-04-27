package httpserver.httpresources;

import httpserver.HttpResponse;
import httpserver.Resource;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

class GetWithoutBodyTest {
    @Test
    void ItReturnsAnHttpResponseWithStatus200AndEmptyBody() {
        HttpResponse response = new HttpResponse();
        response.responseLine += "200 Ok";
        Resource resource = new GetWithoutBody();

        assertThat(response, samePropertyValuesAs(resource.act()));
    }
}