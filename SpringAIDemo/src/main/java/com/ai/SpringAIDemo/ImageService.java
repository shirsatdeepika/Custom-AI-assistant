package com.ai.SpringAIDemo;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt) {
        // Call the image model to generate an image based on user input
   // ImageResponse imageResponse= imageModel.call(
    //        new ImagePrompt(prompt)
    ImageResponse imageResponse = imageModel.call(
            new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
              //  .model("gpt-image-1")
                        .quality("low")
                        .size("1024x1024")
                        .n(1)
                        .build()
        ));
        return imageResponse;
    }
}
