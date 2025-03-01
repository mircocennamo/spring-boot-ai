package it.interno.ai.controller;

import it.interno.ai.tools.PersonaPoliticamenteEspostaGeneratorTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mirco.cennamo on 27/02/2025
 * @project spring-into-ai
 */
@RestController
public class PPEController {

    @Autowired
    private ChatClient chatClient;



   /*public MyFamilyController(ChatClient.Builder builder) {
        this.chatClient = builder
               // .defaultSystem("Sei un utile assistente AI che risponde alle informazioni sulla mia famiglia.")
               // .defaultFunctions("myFamilyFunction")

                .build();
    }

    */



    @GetMapping("/info")
    public String myFamilyFaq(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }



    @GetMapping("/infonew")
    public String myFamilyNewFaq(@RequestParam String message) {
        Message userMessage = new UserMessage(message);
        List<Message> messages =List.of(
               // new SystemMessage("Sei un utile assistente AI che risponde alle informazioni sulla mia famiglia."),
               // new SystemMessage("Fornisci risposte dettagliate e accurate."),
               // new SystemMessage("Sei pronto a rispondere alle domande sulla mia famiglia?"),
               // new SystemMessage("Fammi sapere cosa vuoi sapere sulla mia famiglia."),
               // new SystemMessage("Sono pronto a rispondere alle tue domande sulla mia famiglia."),
               // new SystemMessage("Sono un assistente AI pronto a rispondere alle domande sulla mia famiglia."),
                userMessage
        );
        // ChatOptions chatOptions = OpenAiChatOptions.builder().function("myFamilyFunction").build();
       // Prompt prompt = new Prompt(systemMessages,chatOptions);
        //Prompt prompt = new Prompt(systemMessages, ChatOptions.builder()..function("myFamilyFunction").build());
        Prompt prompt = new Prompt(messages);
        return  chatClient
                .prompt(prompt)
                .tools(new PersonaPoliticamenteEspostaGeneratorTools())
                .call()
                .content();
       // ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
       // return response.getResult().getOutput().getText();

        //eturn  response.getResult().getOutput().getText();

        // Process the response internally and return only necessary information
        //return "Family information processed successfully.";
    }
}
