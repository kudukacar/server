package httpserver;

public class HttpRouter implements Router {

    private final Action getWithBody;
    private final Action getWithoutBody;
    private final Action methodNotAllowed;

    public HttpRouter(Action getWithBody, Action getWithoutBody, Action methodNotAllowed) {
        this.getWithBody = getWithBody;
        this.getWithoutBody = getWithoutBody;
        this.methodNotAllowed = methodNotAllowed;
    }

    public HttpResponse route(String request) {
        if(request.equals("/simple_get")) {
            return getWithoutBody.act();
        } else if(request.equals("/simple_get_with_body")) {
            return getWithBody.act();
        } else {
            return methodNotAllowed.act();
        }
    }
}
