package no.kristiania.HTTPrequest;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpclientTest {

    @Test
    void shouldReadSuccessStatusCode() throws IOException {
        Httpclient httpClient = makeEchoRequest("/echo?status=200");
        assertEquals(200, httpClient.getResponseCode());
    }

    private Httpclient makeEchoRequest(String requestTarget) throws IOException {
        return new Httpclient("urlecho.appspot.com", 80, requestTarget);
    }

    @Test
    void shouldReadFailureResponseCode() throws IOException {
        Httpclient httpClient = makeEchoRequest("/echo?status=401");
        assertEquals(401, httpClient.getResponseCode());
    }

    @Test
    void shouldReadHeaders() throws IOException {
        Httpclient httpClient = makeEchoRequest("/echo?body=kristiania");
        assertEquals(10, httpClient.getResponseHeader("Content-length"));
    }

}