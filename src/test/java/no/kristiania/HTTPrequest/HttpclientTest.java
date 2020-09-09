package no.kristiania.HTTPrequest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpclientTest {

    @Test
    void shouldReadSuccessStatusCode() {
        Httpclient httpClient = new Httpclient("urlecho.appspot.com", 80, "/echo?status=200");
        assertEquals(200, httpClient.getResponseCode());
    }

}