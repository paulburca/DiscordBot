package com.pein.commands;

import com.pein.bot.BotLauncher;
import com.sun.syndication.io.SyndFeedInput;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

public class Ask extends Command {

    public Ask(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {

        String[] input = getArguments();
        StringBuilder question = new StringBuilder();
        GuildMessageReceivedEvent event = getEvent();

        if (input.length < 3) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.MAGENTA);
            usage.setTitle(BotLauncher.getMessages().getString("usage"));
            usage.setDescription(BotLauncher.getMessages().getString("usage.desc") + "\n\n" + BotLauncher.getMessages().getString("usage.desc1"));
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        for (int i = 2; i < input.length; i++) {
            if (i == input.length - 1) {
                question.append(input[i]);
            } else {
                question.append(input[i]).append(" ");
            }
        }

        switch(input[1]){
            case "stack":
                String url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle="+question+"&site=stackoverflow";
                ResponseEntity<String> stackResponse = restTemplate.getForEntity(url,String.class);

                byte[] bytes = stackResponse.getBody().getBytes(StandardCharsets.UTF_8);

                try {
                    GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case "alpha":
                ResponseEntity<String> response = restTemplate.getForEntity("https://api.wolframalpha.com/v2/query?input=" + question + "&appid=VK9P9V-P7PJWEKPHA", String.class);
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
                } catch (SAXException | IOException e) {
                    e.printStackTrace();
                }

                Element root = document.getDocumentElement();

                NodeList nodeList = root.getElementsByTagName("pod");
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    Node node1 = element.getElementsByTagName("plaintext").item(0);
                    message.append(node1.getTextContent()).append("\n");
                }

                if (!message.toString().equals("")) {
                    EmbedBuilder answer = new EmbedBuilder();
                    answer.setColor(Color.ORANGE);
                    answer.setTitle(BotLauncher.getMessages().getString("answer.is"));
                    answer.setDescription(message.substring(0, Math.min(message.length() - 1, 512)) + "...");
                    event.getChannel().sendMessage(answer.build()).queue();
                } else {
                    EmbedBuilder fail = new EmbedBuilder();
                    fail.setColor(Color.RED);
                    fail.setTitle(BotLauncher.getMessages().getString("no.answers"));
                    fail.setDescription(BotLauncher.getMessages().getString("ask.me"));
                    event.getChannel().sendMessage(fail.build()).queue();
                }
                break;
            default:
                break;
        }


    }

}
