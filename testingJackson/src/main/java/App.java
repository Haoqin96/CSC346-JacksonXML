import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * todo: PROGRAM DESCRIPTION
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
        System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Deserializing a nested XML File -=-=-=-=-=-=-=-=-=-");
        System.out.println("\n-=-=-=-=-=-=-=-=-=- Example of Deserializing an array expressed as XML -=-=-=-=-=-=-=-=-=-");
        deserializeState();

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
    }

    //simple XML file
    public static void deserializeSimple () {
        try {
            System.out.println("\nIn deserializeSimple");
            XmlMapper xmlMapper = new XmlMapper();

            //read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("password.xml")));

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
    }

    public static void deserializeNested(){
        try{
            System.out.println("\nIn deserializeNested...");

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
            //testing from the xml file I created
            //String url = new String(Files.readAllBytes(Paths.get("states.xml")));



            /*
            String test = "";
            InputStream in = url.openStream();
            Scanner scan = new Scanner(in);
            scan.nextLine().trim();
            while(scan.hasNext()){
                if(scan.next().equals(null)){
                    continue;
                }
                test = test + scan.next().trim();
                if (scan.next().equals("</states>")) {
                    break;
                }
            }

            */

            /*
            InputStream input = new URL("https://civilserviceusa.github.io/us-states/data/states.xml").openStream();
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(input);
            while(reader.hasNext()){
                System.out.println(reader.getText());
                reader.next();
            }
            */





            //testing reading from url with a scanner
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            in.readLine();
            String test = "";
            String result= "";
            while ((test = in.readLine().trim()) != null){
                //test = test + in.readLine();
                System.out.println(test);
                //result = result + test;
                //if (in.readLine().equals("</state>")){
                    //break;
                //}
            }
            /**/



             /*
            String test = "";
            Scanner sc = new Scanner(url);
            while(sc.hasNextLine()){
                test = test+sc.next();
            }
            //System.out.println(test);
            */


            //List<States> states = xmlMapper.readValue(test, new TypeReference<List<States>>() {});
            //System.out.println(toString(states));

            System.out.println("End of try");
        } catch (Exception e){
            System.out.println("Error in deserializeState: " + e);
        }
    }

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