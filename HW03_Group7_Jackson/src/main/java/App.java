import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The purpose of this program is to show examples of serializing and deserializing XML files through the use of
 * the Jackson dependency. The program takes both local XML files and XML files from the internet and will
 * serialize a new XMl file (take an instance of an object and surround variables with tags in a newly made file)
 * or deserialize the provided XML file (create a new instance of the objet and remove the surrounding tags from relevant
 * fields within the file). The program demonstrates serializing and deserializing a simple XML file, serializing
 * and deserializing a nested XML file, and deserializing an array expressed as XMl.
 *
 * @author: Group 7
 * @since: February 2022
 *
 */

public class App {
    public static void main(String[] args) {

        System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Serializing and Deserializing a simple XMl File -=-=-=-=-=-=-=-=-=-");
        serializeSimple();
        deserializeSimple();
        System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Serializing and Deserializing a nested XML File -=-=-=-=-=-=-=-=-=-");
        serializeNested();
        deserializeNested();
        System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Deserializing an array expressed as XML -=-=-=-=-=-=-=-=-=-");
        deserializeState();
    }//end of main

    //simple XML file
    public static void serializeSimple() {
        try {
            System.out.println("\nIn serializeSimple...");
            XmlMapper xmlMapper = new XmlMapper();

            // serialize our Object into XML string
            String xmlString = xmlMapper.writeValueAsString(new Credentials("missouriwestern.edu", "3306", "student", "1234ABCD"));


            // write to the console
            System.out.println("The XML File has been sucessfully serialized!");
            System.out.println("The contents of the new XML file are as follows:");
            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File("simpleSerialized.xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (Exception e) {
            // handle exception
            System.out.println(e);
        }
    }//end of serializedSimple

    //simple XML file
    public static void deserializeSimple () {
        try {
            System.out.println("\nIn deserializeSimple");
            XmlMapper xmlMapper = new XmlMapper();

            //read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("testingJackson/password.xml")));

            //creating instance of password object
            Credentials deserialized = xmlMapper.readValue(readContent, Credentials.class);

            //print details
            System.out.println("Deserialized data...");
            System.out.println("\tHost: " + deserialized.getHost());
            System.out.println("\tPort: " + deserialized.getPort());
            System.out.println("\tUser: " + deserialized.getUser());
            System.out.println("\tPassword: " + deserialized.getPassword());
        } catch (IOException e) {
            System.out.println("Error in deserializeFromXML: " + e);
        }
    }//end of deserializeSimple

    //nested xml
    public static void serializeNested(){
        try{
            System.out.println("In serializeNested...");
            XmlMapper xmlMapper = new XmlMapper();

            //serialize our object into a new String
            String xmlString = xmlMapper.writeValueAsString(new Nested("credit", "credit_url",
                    new Nested.Image("url", "title", "link"), "suggested_pickup", "suggested_pickup_period"));

            //write output to the console
            System.out.println("Nested XML has been successfully been serialized!");
            System.out.println("The contents of the nested xml file are as follows:");
            System.out.println(xmlString);

            //write to xml string to a new file
            File xmlNestedOutput = new File("nestedSerialized.xml");
            FileWriter fileWriter = new FileWriter(xmlNestedOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        }catch(Exception e){
            System.out.println("Error in serializeNested: " + e);
        }//end of catch
    }//end of serializeNested


    //nested xml
    public static void deserializeNested(){
        try{
            System.out.println("\nIn deserializeNested...");
            XmlMapper xmlMapper = new XmlMapper();
            //XmlMapper xmlMapper = (XmlMapper) new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //read the from the url the items that we need to deserialize
            URL url = new URL("https://w1.weather.gov/xml/current_obs/KSTJ.xml");

            //reading with BufferedReader
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String currentLine = "";
            String lineConcat = "";

            //while loop to get the image information
            while((currentLine = in.readLine()) != null){
                if(currentLine.contains("<image>")){
                    for(int i = 0; i < 5; i++){
                        lineConcat = lineConcat + currentLine.trim();
                        currentLine = in.readLine();
                    }
                    break;
                }
            }

            //create image from String
            Nested.Image image = xmlMapper.readValue(lineConcat, Nested.Image.class);

            //creating a new buffered reader and resetting the strings
            BufferedReader in2 = new BufferedReader(new InputStreamReader(url.openStream()));
            currentLine = "";
            lineConcat = "";

            Nested nested = new Nested();

            //while loop for nested object
            while((currentLine = in2.readLine()) != null){
                if(currentLine.contains("<credit>")){
                    currentLine = currentLine.replaceAll("<credit>", "");
                    currentLine = currentLine.replaceAll("</credit>", "");
                    nested.setCredit(currentLine.trim());
                    currentLine = in2.readLine();
                    currentLine = currentLine.replaceAll("<credit_URL>", "");
                    currentLine = currentLine.replaceAll("</credit_URL>", "");
                    nested.setCreditUrl(currentLine.trim());
                    for(int i = 0; i < 5; i++){in2.readLine();}
                    currentLine = in2.readLine();
                    currentLine = currentLine.replaceAll("<suggested_pickup>", "");
                    currentLine = currentLine.replaceAll("</suggested_pickup>", "");
                    nested.setSuggestedPickup(currentLine.trim());
                    currentLine = in2.readLine();
                    currentLine = currentLine.replaceAll("<suggested_pickup_period>", "");
                    currentLine = currentLine.replaceAll("</suggested_pickup_period>", "");
                    nested.setSuggestedPickupPeriod(currentLine.trim());
                    break;
                }
            }
            nested.setImage(image);

            //print details
            System.out.println("Deserialized data...");
            System.out.println("\tCredit: " + nested.getCredit());
            System.out.println("\tCredit Url: " + nested.getCreditUrl());
            System.out.println("\tImage Information:");
            System.out.println("\t\tImage Url: " + image.getUrl());
            System.out.println("\t\tImage Title: " + image.getTitle());
            System.out.println("\t\tImage Link: " + image.getLink());
            System.out.println("\tSuggested Pickup: " + nested.getSuggestedPickup());
            System.out.println("\tSuggested Pickup Period: " + nested.getSuggestedPickupPeriod());
        }catch(Exception e){
            System.out.println("Error in deserializeNested: " + e);
        }
    }//end of deserializeNested

    //array expressed as XML
    public static void deserializeState(){
        try{
            System.out.println("\nIn deserializeState");
            XmlMapper xmlMapper = new XmlMapper();

            //create url that contains the xml file
            URL url = new URL("https://civilserviceusa.github.io/us-states/data/states.xml");

            //testing reading from url with a BufferedReader
            System.out.println("Pulling first state from xml file...");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            in.readLine();
            String test = "";
            int count = 0;
            String result= "";
            while ((test = in.readLine().trim()) != null){
                result = result + test;
                if (test.contains("</state>")){
                    count = count +1;
                    if(count >= 2){
                        result = result +"</states>";
                        break;
                    }
                }
            }

            List<States> states = xmlMapper.readValue(result, new TypeReference<List<States>>() {});
            System.out.println(toString(states));
        } catch (Exception e){
            System.out.println("Error in deserializeState: " + e);
        }
    }//end of deserializedState

    //toString to help print the array expressed as XML example
    public static String toString(Object obj) {
        try {
            StringWriter w = new StringWriter();
            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(w, obj);
            return w.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}