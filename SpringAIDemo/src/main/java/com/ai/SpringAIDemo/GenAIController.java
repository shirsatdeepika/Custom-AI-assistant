package com.ai.SpringAIDemo;

import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class GenAIController {

    ChatService chatService;
    ImageService imageService;
    RecipeService recipeService;

    public GenAIController(ChatService chatService,ImageService imageService,RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

/*@GetMapping("generate-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws Exception {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String url = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(url);

    }*/
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/generate-image")
    public List<String> generateImage(HttpServletResponse response, @RequestParam String prompt) throws Exception {
              ImageResponse imageResponse = imageService.generateImage(prompt);

              //streams to get url from imageResponse
             List<String> urls = imageResponse.getResults().stream()
              .map(result -> result.getOutput().getUrl())
              .collect(Collectors.toList());
       
              return urls;

    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue="any") String cuisine,
                                @RequestParam(defaultValue="") String dietaryRestrictions) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

}
