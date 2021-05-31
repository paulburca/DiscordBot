package com.pein.bot;

import com.pein.Entities.CategoryEntity;
import com.pein.repositories.CategoryRepository;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class GetCategories extends Command{
    public GetCategories(String[] arguments, GuildMessageReceivedEvent event) {
        super(arguments, event);
    }
    CategoryRepository categoryRepository = new CategoryRepository();
    @Override
    void handleCommand() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        StringBuilder stringBuilder = new  StringBuilder();
        for(CategoryEntity categoryEntity : categories){
            stringBuilder.append(categoryEntity.getName()).append("\n");
        }
        getEvent().getChannel().sendMessage(stringBuilder.toString()).queue();
    }
}
