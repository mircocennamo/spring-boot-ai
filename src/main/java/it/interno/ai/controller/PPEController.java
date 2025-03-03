package it.interno.ai.controller;

import it.interno.ai.tools.PersonaPoliticamenteEspostaGeneratorTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mirco.cennamo on 27/02/2025
 * @project spring-into-ai
 */
@RestController
public class PPEController {

    private final ChatClient chatClient;

    private static final Logger log = LoggerFactory.getLogger(PPEController.class);


    private final String PROMPT_BLUEPRINT = """
      Le richieste sono relative alle persone politicamente esposte.
      L'advisor RetrievalAugmentationAdvisor fornito contiene la lista delle persone politicamente esposte da esaminare.
      Cerca sempre la risposta uando sempre l'advisor RetrievalAugmentationAdvisor fornito.
      Considera ogni persona come una persona politicamente esposta anche se non specificato.
     
      Query:
      {query}
      Nel caso in cui non hai alcuna risposta dal'advisor RetrievalAugmentationAdvisor fornito, dì semplicemente:
      Mi dispiace, non ho le informazioni che stai cercando.
    """;
    private final RetrievalAugmentationAdvisor retrievalAugmentationAdvisor;

    public PPEController(//PersonaPoliticamenteEspostaGeneratorTools tools,
                         ChatClient.Builder builder,
                         ChatMemory chatMemory,
                         ResourceLoader resourceLoader,
                         RetrievalAugmentationAdvisor retrievalAugmentationAdvisor,
                         PersonaPoliticamenteEspostaGeneratorTools tools
                          ) {

        this.retrievalAugmentationAdvisor = retrievalAugmentationAdvisor;

        this.chatClient = builder
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
             // .defaultSystem(resourceLoader.getResource("classpath:/prompts/ppe.st"))
                //.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
               .defaultTools(tools)
               .build();
    }

    @GetMapping("/info")
    public String infoPPE(@RequestParam String message) {
        long startTime = System.currentTimeMillis();


            String response = this.chatClient.prompt()
                    .user(message)
                    .advisors(retrievalAugmentationAdvisor)
                    .call()
                    .content();
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            log.info("Response time: {} ms", responseTime);
            return response;

    }

/*
    @GetMapping("/chat")
    public String chat(@RequestParam String message) throws IOException, ParseException {
       return this.chatClient
                .prompt()
                .user(message)
                //.tools(tools)
                .advisors(questionAnswerAdvisor)
                .call()
                .content();

    }

    @GetMapping("/search-ppe")
    public List<PPEDistro> get(@RequestParam("searchText") String searchText) {
        return pPEDocumentService.findSimilarDocuments(searchText);
    }


    @GetMapping("/search")
    public String getWikiAnswer(@RequestParam("question") String question) {
        return chatClient.prompt()
                .user(question)
                .advisors(new QuestionAnswerAdvisor(vectorStore)) // Enable RAG
                .call()
                .content();
    }



    private String createPrompt(String query) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_BLUEPRINT);
       // ObjectMapper objectMapper = new ObjectMapper();
      //  objectMapper.registerModule(new JavaTimeModule());
     //   objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        promptTemplate.add("query", query);
        return promptTemplate.render();
    }
*/

}
