package com.pein.bot;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class CommandHandler extends ListenerAdapter {

    private List<PredefinedFeed> listPredefinedFeeds = getFeeds();

    public  List<PredefinedFeed> getFeeds() {
        List<PredefinedFeed> returnList = new ArrayList<>();
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("src/main/resources/feeds.txt"));
            String fileLine;
            while ((fileLine = fileReader.readLine()) != null) {
                String[] feedArguments = fileLine.split(",");
                PredefinedFeed predefinedFeed = new PredefinedFeed("",feedArguments[0],
                        feedArguments[1],"romana","none", LocalDate.now());
                returnList.add(predefinedFeed);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return returnList;
    }

    public static boolean isNumeric(String string){
        if(string == null){
            return false;
        }
        try{
            int number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] commandArguments = event.getMessage().getContentRaw().split("\\s+");

        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "clear")) {
            if (commandArguments.length != 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.ORANGE);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: '" + BotLauncher.prefix + "clear [number of messages]'");
                event.getChannel().sendMessage(usage.build()).queue();
            } else {
                try {

//                    event.getChannel().getHistory().retrievePast(Integer.parseInt(commandArguments[1]))
//                            .map(messages -> messages.get(1))
//                            .queue(message -> System.out.println("Retrieving the first message channel before delete"));

                    List<Message> messages = event.getChannel()
                            .getHistory()
                            .retrievePast(Integer.parseInt(commandArguments[1]))
                            .complete();
                    event.getChannel().deleteMessages(messages).queue();

                    EmbedBuilder succes = new EmbedBuilder();
                    succes.setColor(Color.ORANGE);
                    succes.setTitle("Successfully deleted " + commandArguments[1] + ".");
                    succes.setDescription("Messages older than 2 weeks can't be deleted.");
                    event.getChannel().sendMessage(succes.build()).queue();

                } catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.langIllegalArgument.Exception: Message retrieval")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.ORANGE);
                        error.setTitle("Too many messages selected.");
                        error.setDescription("Between 1-100 messages can be deleted one at a time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.ORANGE);
                        error.setTitle("Selected messages are older that 2 weeks.");
                        error.setDescription("Messages older stan 2 weeks can't be deleted.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }

            }
        }

        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "info")) {
            EmbedBuilder information = new EmbedBuilder();
            information.setTitle("(⌐■_■) Information");
            information.setDescription("content information");
            information.addField("Creator", "Burcă Paul (paulburca), Filimon Dănuț-Dumitru (Danie83)", false);
            information.setColor(0xed1313);
            information.setFooter("fill", event.getMember().getUser().getEffectiveAvatarUrl());


            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(information.build()).queue();
            information.clear();
        }


        if (commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "news")) {
            String url = null;
            int numberOfEntries = 3; // default

            if(commandArguments.length <=2){
                url = listPredefinedFeeds.get(0).getFeedLink();
            }
            else
            {
                int count = 0;
                boolean existence = false;
                for(PredefinedFeed predefinedFeed : listPredefinedFeeds){
                    if(commandArguments[1].equals(predefinedFeed.getFeedCategory())){
                        url = listPredefinedFeeds.get(count).getFeedLink();
                        existence = true;
                    }
                    count++;
                }
                if(!existence){
                    event.getChannel().sendMessage("Sorry, but that's not a valid category.").queue();
                }
            }

            if (isNumeric(commandArguments[commandArguments.length-1])) {
                numberOfEntries = Integer.parseInt(commandArguments[commandArguments.length-1]);
            }

            if (url != null) {
                RSSManager rssManager = new RSSManager(url, event, numberOfEntries);
            }
        }
        if(commandArguments[0].equalsIgnoreCase(BotLauncher.prefix + "ask")){
            RestTemplate restTemplate = new RestTemplate();
            String input = commandArguments[1];
            ResponseEntity<String> response = restTemplate.getForEntity("https://api.wolframalpha.com/v2/query?input="+input+"&appid=VK9P9V-P7PJWEKPHA",String.class);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
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
            for(int i = 0; i<nodeList.getLength();i++) {
                Element element = (Element) nodeList.item(i);
                if(element.getAttribute("title").equals("Wikipedia summary")){
                    Node node1 = element.getElementsByTagName("plaintext").item(0);
                    event.getChannel().sendMessage (node1.getTextContent()).queue();
                }
            }
        }
    }

}
