package server;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * This class, implements the logic for GET, POST and PUT methods for the HTTP Protocol. Also, it implements
 * the correct header.
 */

public class HttpProtocolHandler {

    public String requestLine;
    public String[] requestLineSplitted;
    public String requestURI; //path to the resource
    public String httpVersion; // HTTP 1.1 ...
    public String requestMethodName; //GET,POST ...
    public int length;
    public String header = "";
    public String webServerRoot;
    public String getHost;

    private String temporaryLocation;
    private String httpStatusCode;
    private String defaultContentType="";

    //POST
    private byte[] byteArray; //Contains everything from the POST
    private String boundry;
    public String filename; //POST

    private ResponseStatusCode rs = new ResponseStatusCode();


    public HttpProtocolHandler (){

    }

    /**
     *
     * This method read everything that is needed for the Response Body and return it in a byte array.
     * @param getRootDir Takes the directory to the HTML files. And assign it to the variable webServerRoot.
     * @return the byte array that will contain the Response Body (HTML file)
     *
     */

    public byte[] readAll(String getRootDir){
        webServerRoot = getRootDir;
        System.out.println("HERE " +webServerRoot);
        readFile(getIndex(fileToSend()));

        if (httpStatusCode.contains("200")){
            return readFile(getIndex(fileToSend()));
        } else if (httpStatusCode.contains("302")){
            return rs.status302().getBytes();
        } else if (httpStatusCode.contains("403")){
            return rs.status403().getBytes();
        } else if(httpStatusCode.contains("404")){
            return rs.status404().getBytes();
        }
        return readFile(getIndex(fileToSend()));
    }



    public byte[] get500(){
        responseHeader("500 Internal Server Error",rs.status500().length(),"");
        String all = header + rs.status500();
        return all.getBytes();
    }

    /**
     *
     * This method will generate the Response Header. It also takes into consideration the content type.
     * @param statusCode HTTP response status code (e.g. 200 OK, 302 Found etc.)
     * @param contentLength HTTP response length of the HTML
     * @param location HTML file location
     *
     */

    public void responseHeader(String statusCode, int contentLength, String location){

        String contentType = defaultContentType;
        if (requestURI.contains(".png")){
            contentType = "image/png";
        } else if (requestURI.contains(".html")){
            contentType = "text/html";
        } else if(requestURI.contains(".jpg")){
            contentType = "image/jpg";
        }

        if (statusCode.contains("302")){
            header = "HTTP/1.1 "+ statusCode + "\r\n"
                    + "Location: "+ location +"\r\n"
                    + "\r\n";
        } else if (statusCode.contains("201")){
            header = "HTTP/1.1 "+ statusCode + "\r\n"
                    + "Content-Type: "+contentType + "\r\n"
                    + "Content-Location: "+ location +"\r\n"
                    + "\r\n";
        } else if (statusCode.contains("204")){
            header = "HTTP/1.1 "+ statusCode + "\r\n"
                    + "Content-Type: "+contentType + "\r\n"
                    + "Content-Length: "+ contentLength +"\r\n"
                    + "Content-Location: "+ location +"\r\n"
                    + "\r\n";
        } else {
            header = "HTTP/1.1 "+ statusCode + "\r\n"
                    + "Content-Type: "+contentType + "\r\n"
                    + "Content-Length: "+ contentLength +"\r\n"
                    + "\r\n";
        }

    }


    public String getResponseHeader(){

        return header;
    }

    //Read http

    /**
     *
     * This method, will split the Request Message into a String array. By doing that we can separate each part of the Message.
     * The Request Line will contain  : request-method-name request-URI HTTP-version (e.i. GET /test.html HTTP/1.1)
     * Request Line will be split to get the different part of the Request Line.
     * getHost will contain the host name like localhost.
     *
     * For the POST, it will take the filename, which is the name of the image. And the boundary.
     * @param url Takes the Request Message from the client (e.g. Browser) in a String.
     * @param byteArray Takes the Request Message from the client in a byte array.
     *
     */
    public void readHttpGetRequestLine(String url, byte[] byteArray){
        //protocol://hostname:port/path
        this.byteArray = byteArray; //to setup for POST

        if (url.contains(" ")){
            String [] urlSplitted = url.split("\n");
            for (int i = 0; i<urlSplitted.length;i++){
                //System.out.println(urlSplitted[i]);
                if (urlSplitted[i].contains("GET") || urlSplitted[i].contains("POST") || urlSplitted[i].contains("PUT")){
                    requestLine = urlSplitted[i]; // Put first into requestLine GET /http/html HTTP1.1
                } else if (urlSplitted[i].contains("Host")){
                    getHost = urlSplitted[i];
                    getHostName(getHost); //get the domain name e.g. localhost:8080
                } else if(urlSplitted[i].contains("Disposition")){
                    //POST
                    filename = getContentDispositionFilename(urlSplitted[i]); //get
                } else if (urlSplitted[i].contains("boundary")){
                    //POST
                    String boundary = urlSplitted[i];
                    boundry = getBoundary(boundary.split(";")[1].trim());

                }
            }
        }

        requestLineSplitted = requestLine.split(" "); //split first line
        requestMethodName = requestLineSplitted[0]; //GET
        requestURI = requestLineSplitted[1]; //http/html
        httpVersion = requestLineSplitted[2]; //HTTP1.1

    }


    private String getHostName(String name){
        String[] getHostSplitted;
        String getHostDomainName;
        getHostSplitted = name.split(" ");
        getHostDomainName = getHostSplitted[1].trim();

        return getHostDomainName;
    }


    /**
     * --------------------------------------------------------
     * ---------------------  GET  ---------------------------
     * --------------------------------------------------------
     */

    /**
     * The method will combine webServerRoot (directory where HTML files are located) and requestURI (file location)
     * @return the file location.
     */
    //Put director together
    public File fileToSend(){
        // Path currentRelativePath = Paths.get("");
        //webServerRoot = currentRelativePath.toAbsolutePath().toString();
        //System.out.println("MY WEBSERVER LOCATION : "+webServerRoot);

        String fullRoot = webServerRoot + requestURI;
        //System.out.println("REQUEST URI " + requestURI + "; " + " ROOT "+ fullRoot);

        return new File(fullRoot);
    }

    /**
     * This method goal is to read every file and return a byte array of the file. Before doing that, it checks the location with
     * the method checkLocationFiles() which will help to know where the file is located for example for the status code 302.
     * @param file HTML file to read
     * @return byte array that contains the HTML file data, it returns the file.
     */

    public byte[] readFile(File file){
        File checkFile = checkLocationFiles(file, file.getName());

        byte[] fileContent = new byte[ (int) checkFile.length()]; //Read file and send array
        try {
            FileInputStream fin = new FileInputStream(checkFile);
            fin.read(fileContent);
            fin.close();
            // if (length == 0) throw new IOException(); //to test 500
        } catch (IOException io){
            //httpStatusCode = "500";
            //responseHeader("500 Internal Server Error",rs.status500().length(),"");
        }
        //length = fileContent.length ;
        //System.out.println("File content length :" +fileContent.length);

        length = fileContent.length;


        return fileContent;
    }


    /**
     * This method checks if the the path given is a directory, if it is, it will check if it contains the index.html or index.htm
     * and return one of these 2 files if they exist. Otherwise if it is not a directory it will just return the asked file.
     * @param fileToCheck Takes the path returned by the method fileToSend() which will be checked if it is a directory or not
     * @return the correct file to read if it is an index or something else.
     */
    //GET INDEX.HTML CORRECTLY
    public File getIndex(File fileToCheck){

        if (fileToCheck.isDirectory()){

            String[] files = fileToCheck.list(); //index.html,

            for (String file : files){
                if (file.contains("index.html") || file.contains("index.htm")){
                    fileToCheck = Paths.get(fileToSend().toString()+"/"+file).toFile(); //
                    defaultContentType = "text/html";
                }
            }
        }

        return fileToCheck;
    }

    /**
     * This method, will check the location of the file before reading it and decide which file is correct to send.
     * It will also set up at the end the different status code depending if a file exist, found, has access or not.
     * @param file Path of the file and checks if it is a directory, and if the file exists.
     * @param fileNameToLookUp Will be used to redirect the file name to look up.
     * @return the path to the correct file.
     */
    public File checkLocationFiles(File file, String fileNameToLookUp){

        if (!file.isDirectory()){
            if (!file.exists()){
                //Now  we need to search for other directories to find the file.
                String directory = file.getParent();
                System.out.println("This is a directory : "+ directory);

                File[] directories = new File(directory).listFiles(File::isDirectory); //List all directories

                String tryRedirect = redirect(directories,fileNameToLookUp); // Return path

                file = new File(tryRedirect);

                if (file.exists()){
                    httpStatusCode = "302";
                    responseHeader("302 Found",(int)file.length(),temporaryLocation);
                    defaultContentType = "text/html";
                } else {
                    httpStatusCode = "404";
                    responseHeader("404 Not Found",rs.status404().length(),"");
                }

            } else if(hasAccess(requestURI)){
                httpStatusCode = "200";
                responseHeader("200 OK", length,"");
            } else {
                httpStatusCode = "403";
                responseHeader("403 Forbidden",rs.status403().length(),"");
            }
        } else {

        }

        return file;
    }


    /**
     * This method of type boolean will verify is the URI asked for is something forbidden or not.
     * It will return false if it contains 'admin'.
     * @param requestURI Takes the URI as String
     * @return true
     */
    public boolean hasAccess(String requestURI){
        if (requestURI.contains("admin")){
            return false;
        }
        return true;
    }


    /**
     * This method of type String will check all directories and make sure to find the correct path to the file that
     * the client is looking for.
     * @param directories array of File which contains all available directories.
     * @param fileNameToLookUp String file name to look up
     * @return path as String that is the redirected
     */
    public String redirect(File[] directories, String fileNameToLookUp){
        String redirect = "";
        for (File dir : directories){
            String[] listSub = dir.list();
            for (int i = 0; i<listSub.length;i++){
                if (listSub[i].contains(fileNameToLookUp)){

                    temporaryLocation = "http://"+ getHostName(getHost) +"/"+ dir.getName()+ "/"+ fileNameToLookUp ;

                    redirect += dir.getAbsolutePath() + "/" + fileNameToLookUp;
                }
            }
        }
        return redirect;

    }


    /**
     * --------------------------------------------------------
     * ---------------------  POST  ---------------------------
     * --------------------------------------------------------
     */


    /**
     * Calls method : readPOSTFile()
     * @param getRootDir String of HTML file directory from args.
     */
    public void addFileToServer(String getRootDir){
        webServerRoot = getRootDir;
        readPOSTFile();
    }

    /**
     * Separate the boundary boundary=-------Numbers
     * @param boundary
     * @return boundary of the file in String -----
     */
    public String getBoundary(String boundary){
        String boundarySplit = boundary.split("=")[1]; // get only the boundary ------Number without =
        return boundarySplit;
    }


    public String getPostContentType(String postContentType){
        String[] getContentSplitted = postContentType.split(" ");
        String contentTypeReturn = getContentSplitted[1];

        return contentTypeReturn;
    }

    /**
     * Method to take the name of the image
     * @param postDisposition
     * @return
     */
    public String getContentDispositionFilename(String postDisposition){
        String[] getDisposition = postDisposition.split(";");
        String filename = "";

        for (String i : getDisposition){
            if (i.contains("filename")){
                filename = i.substring(11, i.length()-2); //get rid of filename="" and take only the actual name
                //System.out.println(filename);
            }
        }

        return filename;
    }


    /**
     * Creates Directory upload if it does not exist. That is where the uploaded file will be stored.
     */
    public void createUploadDir(){
        File file = new File(webServerRoot + "/" + "upload");
        if (!file.exists()){
            file.mkdir();
        }
    }

    /**
     * This method will check if the image already exists if it does not, it will add ++ to the name.
     *
     * @param path Type of Path
     * @return a path to the file where the image is going to be stored.
     */
    public File fileToSubmit(Path path){
        int count = 1;
        File uploadFile = new File(webServerRoot + "/"+ "upload" +"/"+path.toString());
        File uploadFileMore;
        //String dir = uploadFile.getParentFile();
        String[] allFiles = uploadFile.getParentFile().list();
        String[] fileNameMore = path.toString().split("[.]");

        try {
            for (int i = 0; i<allFiles.length;i++){
                if (allFiles[i].contains(fileNameMore[0])){
                    count++;
                }
            }
        }catch (Exception e){
             //System.out.println("nothing");
            get500();
        }


        String finalName = "";
        int test = fileNameMore.length;
        for (int i = 0;i<fileNameMore.length;i++){

            if (test>2){
                finalName+=fileNameMore[i] + ".";
                test--;
            } else if(test == 2){
                finalName+=fileNameMore[i] + count + ".";
                test--;
            } else {
                finalName+=fileNameMore[i];

            }

        }

        System.out.println(finalName);
        if (uploadFile.exists()){
            uploadFileMore = new File(webServerRoot + "/"+ "upload" +"/"+finalName);

            return uploadFileMore;

        }
        return uploadFile;
    }


    /**
     *
     * This method reads the image data and and uploads it on /upload
     * If filename is empty it will return a status code 500
     *
     */
    public void readPOSTFile(){
        Path pathFilename = Paths.get(filename);
        if (filename.equals("")){
            get500();
        }
        createUploadDir();
        File uploadFile = fileToSubmit(pathFilename);

        try {
            byte[] fileContent = getBodyByte();

            FileOutputStream fop = new FileOutputStream(uploadFile);
            fop.write(fileContent);
            fop.flush();
            fop.close();
        } catch (IOException io){
            get500();
        }

    }

    /**
     * Method to get a byte array of the image file by only taking what is inside the boundaries.
     * It will first remove the header of the image and then removes the header of the meta data of the image to
     * only keep the part that will be used to create the image
     *
     * @return byte array
     */
    public byte[] getBodyByte(){
        int count=0;
        int indexStart=0;
        byte[] boundaryByte = boundry.getBytes(); //Get the boundary


        // To get the body between both boundary which will include every data about the file uploaded

        for(int i =0;i<byteArray.length-1-boundaryByte.length;i++){
            if(byteArray[i] == boundaryByte[0]){
                int check = 1;

                for(int j=1;j<boundaryByte.length;j++){
                    if (byteArray[i+j]==boundaryByte[j]){
                        //System.out.println(new String(byteArray));
                        check++;
                    } else {
                        break;
                    }
                }

                if(check == boundaryByte.length){
                    if(count<2){
                        indexStart = i + boundaryByte.length;
                        System.out.println(indexStart);
                        count++;
                    }
                }
            }
        }
        int indexEnd = (byteArray.length-boundaryByte.length);
        //TO Get just the image content
        for(int i= indexStart;i<=indexEnd;i++){

            if (byteArray[i]=='\n') {
                if(byteArray[i+1]=='\r'){
                    if(byteArray[i+2]=='\n') {
                        indexStart = i + 3;
                        break;
                    }
                }
            }
        }

        return Arrays.copyOfRange(byteArray,indexStart,indexEnd);
    }


    /**
     * -------------------------------------------------------
     * ---------------------  PUT  ---------------------------
     * -------------------------------------------------------
     */

    /**
     *
     * @param getRootDir Takes args of the HTML file directory
     */
    public void updateCreateFile(String getRootDir){
        webServerRoot = getRootDir;
        readPUTFile();
    }

    /**
     *
     *  This method reads the image data and and uploads it on /upload
     *  If filename is empty it will return a status code 500.
     *  It will take care of the status code 201 and 204
     */
    public void readPUTFile(){
        System.out.println("here");
        Path pathFilename = Paths.get(filename);
        if (filename.equals("")){
            get500();
        }
        System.out.println(pathFilename.toAbsolutePath());
        createUploadDir();
        File uploadFile = new File(webServerRoot + "/"+ "upload" +"/"+pathFilename.toString());

        try {
            byte[] fileContent = getBodyByte();
            length = fileContent.length;

            String[] allFiles = uploadFile.getParentFile().list();
            if (checkFileExist(allFiles,pathFilename.toString())){
                defaultContentType = "img/png";
                responseHeader("204 No Content",length,"/upload/"+pathFilename.toString());
            } else {
                defaultContentType = "img/png";
                responseHeader("201 Created",length,"/upload/"+pathFilename.toString());
            }

            FileOutputStream fop = new FileOutputStream(uploadFile);
            fop.write(fileContent);
            fop.flush();
            fop.close();


        }catch (IOException io){
            get500();
        }

    }

    /**
     *
     * This method, will check if the image already exist or not.
     * @param allFiles String array to see if the file exists. This will be useful to setup the status code.
     * @param name name of the file to check
     * @return boolean false or true
     */
    public boolean checkFileExist(String[] allFiles, String name){
        boolean fileExist = false;

        for (String file : allFiles){
            if(file.contains(name)){
                fileExist = true;
                break;
            }
        }

        return fileExist;
    }


}
