# CSC346-JacksonXML

## Members

* Kassidy Ashworth, writer [Github](https://github.com/kashworth2)
* Michael Le,  librarian [Github](https://github.com/mhuynhle-web)
* Nick Wright, coder [Github](https://github.com/nwright7)
* Nicholas Wu, assistant coder/assistant librarian [Github](https://github.com/Haoqin96)

## What is Jackson?

Jackson is a **library** that can handle JSON in Java. It's a collection of tools, that can work with CSV, XML, YAML, and Java properties. This is a walkthrough of the XML component, where it can serialize Java objects into XML, or deserialize XML into Java objects. 

## Setting up Jackson Project

To set up a Jackson project, you should first create a new Maven project. After creation, there should be a `pom.xml` file, where you will add the following dependency: 

```
  <dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.9.0</version>
  </dependency>
```


## Deserializing Simple XML File

We'll start with reading and deserializing a simple xml file. For example, the following:

```
<?xml version="1.0"?>
<credentials>
    <host>woz.cs.missouriwestern.edu</host>
    <port>33006</port> <!--This isn't the right port, by the way -->
    <user>csc</user>
    <password xhint="room where woz is located It definitily is not '!😈湯🦊🚴'">********</password>
</credentials>
```

partnered with: 

```
    public static void deserializeSimple () {
        try {
            System.out.println("\nIn deserializeSimple");
            XmlMapper xmlMapper = new XmlMapper();

            String readContent = new String(Files.readAllBytes(Paths.get("password.xml")));

            Credentials deserialized = xmlMapper.readValue(readContent, Credentials.class);

            System.out.println("Deserialized data...");
            System.out.println("\tHost: " + deserialized.getHost());
            System.out.println("\tPort: " + deserialized.getPort());
            System.out.println("\tUser: " + deserialized.getUser());
            System.out.println("\tPassword: " + deserialized.getPassword());
        } catch (IOException e) {
            System.out.println("Error in deserializeFromXML: " + e);
        }
    }
```

will output: 
```
In deserializeSimple
Deserialized data...
	Host: woz.cs.missouriwestern.edu
	Port: 33006
	User: csc
	Password: ********
values
	Credit Url: creditUrl
	Image:
		url: org.example.Nested$Image@ba8d91c
```
## Deserializing Nested XML File

Next, we will read from the [this url](https://w1.weather.gov/xml/current_obs/KSTJ.xml), and will deserialize the nested XML file. To do this we will add the following method: 

```
    public static void deserializeNested(){
        try{
            System.out.println("\nIn deserializeNested...");
            XmlMapper xmlMapper = new XmlMapper();

            URL url = new URL("https://w1.weather.gov/xml/current_obs/KSTJ.xml");

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
            }

            Nested deserialized = xmlMapper.readValue(lineConcat, Nested.class);

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
```

## Deserializing Array in XML

Lastly, we will deserialize an array expressed in XML. With an array of all 50 states, formatted in the following way: 

```
<states>
<state>
<state>Alabama</state>
<slug>alabama</slug>
<code>AL</code>
<nickname>Yellowhammer State</nickname>
<website>http://www.alabama.gov</website>
<admission_date>1819-12-14</admission_date>
<admission_number>22</admission_number>
<capital_city>Montgomery</capital_city>
<capital_url>http://www.montgomeryal.gov</capital_url>
<population>4833722</population>
<population_rank>23</population_rank>
<constitution_url>http://alisondb.legislature.state.al.us/alison/default.aspx</constitution_url>
<state_flag_url>https://cdn.civil.services/us-states/flags/alabama-large.png</state_flag_url>
<state_seal_url>https://cdn.civil.services/us-states/seals/alabama-large.png</state_seal_url>
<map_image_url>https://cdn.civil.services/us-states/maps/alabama-large.png</map_image_url>
<landscape_background_url>https://cdn.civil.services/us-states/backgrounds/1280x720/landscape/alabama.jpg</landscape_background_url>
<skyline_background_url>https://cdn.civil.services/us-states/backgrounds/1280x720/skyline/alabama.jpg</skyline_background_url>
<twitter_url>https://twitter.com/alabamagov</twitter_url>
<facebook_url>https://www.facebook.com/alabamagov</facebook_url>
</state>
...
```

The following deserialization method: 

```
    public static void deserializeState(){
        try{
            System.out.println("\nIn deserializeState");
            XmlMapper xmlMapper = new XmlMapper();

            URL url = new URL("https://civilserviceusa.github.io/us-states/data/states.xml");

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
    }
```

will output the following: 

```
In deserializeState
Pulling first state from xml file...
[ {
  "state" : "Alabama",
  "slug" : "alabama",
  "code" : "AL",
  "nickname" : "Yellowhammer State",
  "website" : "http://www.alabama.gov",
  "admission_date" : "1819-12-14",
  "admission_number" : "22",
  "capital_city" : "Montgomery",
  "capital_url" : "http://www.montgomeryal.gov",
  "population" : "4833722",
  "population_rank" : "23",
  "constitution_url" : "http://alisondb.legislature.state.al.us/alison/default.aspx",
  "state_flag_url" : "https://cdn.civil.services/us-states/flags/alabama-large.png",
  "state_seal_url" : "https://cdn.civil.services/us-states/seals/alabama-large.png",
  "map_image_url" : "https://cdn.civil.services/us-states/maps/alabama-large.png",
  "landscape_background_url" : "https://cdn.civil.services/us-states/backgrounds/1280x720/landscape/alabama.jpg",
  "skyline_background_url" : "https://cdn.civil.services/us-states/backgrounds/1280x720/skyline/alabama.jpg",
  "twitter_url" : "https://twitter.com/alabamagov",
  "facebook_url" : "https://www.facebook.com/alabamagov"
} ]
values
	Credit Url: creditUrl
	Image:
		url: org.example.Nested$Image@560348e6

```


## Link to our repo
[Click this link](https://github.com/Haoqin96/CSC346-JacksonXML)
