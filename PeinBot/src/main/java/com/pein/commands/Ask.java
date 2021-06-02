package com.pein.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pein.bot.BotLauncher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class Ask extends Command {

    public Ask(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    public void run() {

        String[] input = getArguments();
        GuildMessageReceivedEvent event = getEvent();

        if (input.length < 3) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.MAGENTA);
            usage.setTitle(BotLauncher.getMessages().getString("usage"));
            usage.setDescription(BotLauncher.getPrefix() + BotLauncher.getMessages().getString("usage.desc")
                    + "\n\n" + BotLauncher.getMessages().getString("usage.desc1"));
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(usage.build()).queue();
            return;
        }

        StringBuilder stackQuestion = new StringBuilder();
        StringBuilder alphaQuestion = new StringBuilder();
        for (int i = 2; i < input.length; i++) {
            if (i == input.length - 1) {
                stackQuestion.append(input[i]);
                alphaQuestion.append(input[i]);
            } else {
                stackQuestion.append(input[i]).append("%20");
                alphaQuestion.append(input[i]).append(" ");
            }
        }

        switch (input[1]) {
            case "stack":
                String urlString = "https://api.stackexchange.com/2.2/search?order=desc&sort=relevance&intitle=" + stackQuestion + "&site=stackoverflow";
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.connect();

                    JsonParser jsonParser = new JsonParser();
                    JsonElement rootElement = jsonParser.parse(new InputStreamReader(new GZIPInputStream((InputStream) request.getContent()))); // Convert the input stream to a json
                    JsonObject rootObject = rootElement.getAsJsonObject();
                    JsonArray items = rootObject.get("items").getAsJsonArray();
                    for (int count = 0; count < Math.min(items.size() - 1, 5); ) {
                        JsonObject result = items.get(count).getAsJsonObject();
                        if (result.get("is_answered").getAsBoolean()) {
                            String title = result.get("title").getAsString();
                            title = title.replaceAll("&quot;", "\"");
                            title = title.replaceAll("&#39;", "'");

                            String link = result.get("link").getAsString();
                            String answer = "";
                            try {
                                answer = result.get("accepted_answer_id").getAsString();
                            } catch (Exception exception) {
                                //
                            }

                            EmbedBuilder response = new EmbedBuilder();
                            response.setColor(Color.GREEN);
                            response.setTitle(title.substring(0, Math.min(128, title.length())));
                            response.setDescription("[" + BotLauncher.getMessages().getString("relevant") + "](" + link + ")");

                            if (answer.length() != 0) {
                                response.addField(BotLauncher.getMessages().getString("answer")
                                        , "[" + BotLauncher.getMessages().getString("answer.view")
                                                + "](" + link + "/#" + answer + ")", false);
                            } else {
                                response.addField(BotLauncher.getMessages().getString("no.answer"), ":(", false);
                            }

                            event.getChannel().sendTyping().queue();
                            event.getChannel().sendMessage(response.build()).queue();

                            count++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "alpha":
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity("https://api.wolframalpha.com/v2/query?input="
                        + alphaQuestion.toString().replaceAll("\\+", "plus")
                        + "&appid=VK9P9V-P7PJWEKPHA", String.class);
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
                if (root.getAttribute("success").equals("false")) {
                    EmbedBuilder fail = new EmbedBuilder();
                    fail.setColor(Color.RED);
                    fail.setTitle(BotLauncher.getMessages().getString("no.answers"));
                    fail.setDescription(BotLauncher.getMessages().getString("ask.me"));
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(fail.build()).queue();
                    break;
                }

                EmbedBuilder answer = new EmbedBuilder();
                answer.setTitle(BotLauncher.getMessages().getString("answer.is"));
                answer.setColor(Color.ORANGE);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    if (!element.getElementsByTagName("plaintext").item(0).getTextContent().equals("")) {
                        answer.addField(element.getAttribute("title"),
                                element.getElementsByTagName("plaintext").item(0).getTextContent(), false);
                    }
                }

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(answer.build()).queue();
                break;
            default:
                EmbedBuilder defaultCase = new EmbedBuilder();
                defaultCase.setColor(Color.red);
                defaultCase.setDescription(BotLauncher.getMessages().getString("ask.description"));
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(defaultCase.build()).queue();
                break;
        }


    }

}
