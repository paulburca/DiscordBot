package com.pein.bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Ask extends Command{

    Ask(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    void handleCommand(){

        RestTemplate restTemplate = new RestTemplate();
        String[] input = getArguments();
        String question = "";
        GuildMessageReceivedEvent event = getEvent();
        for(int i = 1;i<input.length;i++){
            if(i == input.length - 1){
                question = question + input[i];
            }else
            {
                question = question + input[i] + " ";
            }
        }
        //System.out.println(question);
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.wolframalpha.com/v2/query?input="+question+"&appid=VK9P9V-P7PJWEKPHA",String.class);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //System.out.println(response.getBody());
        InputSource is = new InputSource(new StringReader(response.getBody()));
        Document document = null;
        try {
            document = builder.parse(is);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = document.getDocumentElement();

        NodeList nodeList = root.getElementsByTagName("pod");
        String message = "";
        for(int i = 0; i<nodeList.getLength();i++) {
            Element element = (Element) nodeList.item(i);

            Node node1 = element.getElementsByTagName("plaintext").item(0);
            message = message + node1.getTextContent() + "\n";
        }
        if(message != ""){
            event.getChannel().sendMessage (message).queue();
        }
        else
        {
            event.getChannel().sendMessage("There is no such thing.").queue();
        }
    }

}
