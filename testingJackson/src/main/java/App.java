import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * todo: PROGRAM DESCRIPTION
 *
 * @author: Group 7
 * @since: February 2022
 *
 */

public class App {
    public static void main(String[] args) {

        //System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Serializing and Deserializing a simple XMl File -=-=-=-=-=-=-=-=-=-");
        //serializeSimple();
        deserializeSimple();
        //System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Serializing and Deserializing a nested XML File -=-=-=-=-=-=-=-=-=-");
        //serializeNested();
        //deserializeNested();
        //System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Deserializing an array expressed as XML -=-=-=-=-=-=-=-=-=-");
        //deserializeState();

        //testing creating a nested object
        Nested.Image image = new Nested.Image("url", "title", "link");
        Nested nested = new Nested("credit", "creditUrl", image, "suggPick", "suggPickperiod");

        //printing values
        System.out.println("values");
        System.out.println("\tCredit Url: " + nested.getCreditUrl());
        System.out.println("\tImage:");
        System.out.println("\t\turl: " + nested.getImage());



    }

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

            //read the from the url the items that we need to deserialize
            URL url = new URL("https://w1.weather.gov/xml/current_obs/KSTJ.xml");

            //reading with BufferedReader
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String currentLine = "";
            String lineConcat = "";
            while((currentLine = in.readLine().trim()) != null){
                if(currentLine.contains("<credit>")){
                    lineConcat = lineConcat + currentLine;
                    for(int i = 0; i <= 7; i++){
                        lineConcat = lineConcat + in.readLine().trim();
                    }
                    break;
                }
            }//end of while loop

            //System.out.println(lineConcat);
            //creating instance of Nested Object
            Nested deserialized = xmlMapper.readValue(lineConcat, Nested.class);

            //print details
            System.out.println("Deserialized data...");
            System.out.println("\tCredit: " + deserialized.getCredit());
            System.out.println("\tCredit Url: " + deserialized.getCreditUrl());
            System.out.println("\tImage: " + deserialized.getImage());
            System.out.println("\tSuggested Pickup: " + deserialized.getSuggestedPickup());
            System.out.println("\tSuggested Pickup Period: " + deserialized.getSuggestedPickupPeriod());



        }catch(Exception e){
            System.out.println("Error in deserializeNested: " + e);
        }
    }

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