package com.pein.commands;

import com.pein.Entities.CategoryEntity;
import com.pein.bot.BotLauncher;
import com.pein.repositories.CategoryRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class GetCategories extends Command {
    public GetCategories(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }

    CategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public void run() {

        List<CategoryEntity> categories = categoryRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        if (categories == null) {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(Color.RED);
            error.setDescription(BotLauncher.getMessages().getString("no.available.categories"));
            getEvent().getChannel().sendMessage(error.build()).queue();
            return;
        }

        for (CategoryEntity categoryEntity : categories) {
            stringBuilder.append(categoryEntity.getName()).append(",");
        }

        stringBuilder.substring(0, 256);

        EmbedBuilder embedCategories = new EmbedBuilder();
        embedCategories.setColor(Color.GREEN);
        embedCategories.setTitle(BotLauncher.getMessages().getString("available.categories"));
        embedCategories.setDescription(stringBuilder.toString());
        getEvent().getChannel().sendMessage(embedCategories.build()).queue();
    }
}
