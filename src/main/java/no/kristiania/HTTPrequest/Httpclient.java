package no.kristiania.HTTPrequest;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Httpclient {

    private final int responseCode;
    private Map<String, String> responseHeaders = new HashMap<>();

    public Httpclient(String hostname, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(hostname, port);

        // Format as specified in the HTTP specification
        // Each line is separated by \r\n (CRLF)
        // The request ends with an empty line (\r\n\r\n)

        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + hostname + "\r\n\r\n";
        // Writes data to the server

        socket.getOutputStream().write(request.getBytes());
        String line = readLine(socket);

        System.out.println(line);
        String[] responseLineParts = line.toString().split(" ");
        responseCode = Integer.parseInt(responseLineParts[1]);

        String headerLine;
        while (!(headerLine = readLine(socket)).isEmpty()){
            System.out.println(headerLine);
            int colonPos = headerLine.indexOf(':');
            String name = headerLine.substring(0, colonPos);
            String value = headerLine.substring(colonPos+1).trim();
            responseHeaders.put(name, value);
        }
    }

        // Reads one BYTE at a time, until there is nothing more to read
        // (c = socket.getInputStream().read()) != -1 means
        // Assign the next value of "read()" to c and check if it´s not -1
        // (-1 means end of data)

    private String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            // Stop reading at newline
            if (c == '\n') {
                socket.getInputStream().read();
                break;
            }
            // Treat each byte as a character ("(char)") and print it to the response
            line.append((char) c);
        }
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        String hostname = "urlecho.appspot.com";
        int port = 80;
        String requestTarget = "/echo?status=200&body=Hello%20world!";
        new Httpclient(hostname, port, requestTarget);

    }

    public int getResponseCode() {

        return responseCode;
    }

    public String getResponseHeader(String headerName) {
        return responseHeaders.get(headerName);
    }
}