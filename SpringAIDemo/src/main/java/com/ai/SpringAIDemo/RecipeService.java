package com.ai.SpringAIDemo;

import java.util.Map;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import org.springframework.ai.chat.model.ChatModel;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
                              String cuisine,
                              String dietaryRestrictions)
 {
        var template = """
                Create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is  {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide a detailed recipe with step-by-step instructions, list of ingredints,cooking time, and serving suggestions.
                """;
          
        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String,Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestrictions", dietaryRestrictions
        );
        Prompt prompt= promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

}
