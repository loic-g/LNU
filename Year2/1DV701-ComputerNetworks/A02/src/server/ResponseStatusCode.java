package server;

/**
 * This class, creates the status code HTML to send if an error happens.
 */
public class ResponseStatusCode {

    public ResponseStatusCode(){

    }

    public String status302(){
        String status = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>302 Error</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>302 Found</h1>\n" +
                "<h3>The location has temporarily moved to this location :</h3>\n" +
                "</body>\n" +
                "</html>";

        return status;
    }

    public String status403(){
        String status = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>403 Forbidden</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>403 Forbidden</h1>\n" +
                "<h3>You don't have permission to access /admin on this server</h3>\n" +
                "</body>\n" +
                "</html>";

        return status;

    }

    public String status404(){
        String status = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>404 Not Found</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>404 Not Found</h1>\n" +
                "<h3>File Not Found</h3>\n" +
                "</body>\n" +
                "</html>";
        return status;
    }

    public String status500(){
        String status = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>500 Error</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>500 Internal Server Error</h1>\n" +
                "<h3>The server encountered an internal error or misconfiguration and was unable to complete your request.</h3>\n" +
                "</body>\n" +
                "</html>";
        return status;
    }
}


