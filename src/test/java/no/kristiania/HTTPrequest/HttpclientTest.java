package no.kristiania.HTTPrequest;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpclientTest {

    @Test
    void shouldReadSuccessStatusCode() throws IOException {
        Httpclient httpClient = new Httpclient("urlecho.appspot.com", 80, "/echo?status=200");
        assertEquals(200, httpClient.getResponseCode());
    }

    @Test
    void shouldGetFailureResponseCode() throws IOException {
        Httpclient client = new Httpclient("urlecho.appspot.com", 80, "/echo?status=401");
        assertEquals(401, client.getStatusCode());
    }

}