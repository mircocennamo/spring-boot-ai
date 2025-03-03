package it.interno.ai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.interno.ai.model.PPEDistro;
import it.interno.ai.model.PersonaPoliticamenteEsposta;
import it.interno.ai.repository.PPEDocumentsRepository;
import it.interno.ai.service.PPEDocumentService;
import it.interno.ai.tools.PersonaPoliticamenteEspostaGeneratorTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @author mirco.cennamo on 27/02/2025
 * @project spring-into-ai
 */
@RestController
public class PPEController {

    private final ChatClient chatClient;
    private final ResourceLoader resourceLoader;
    //private final PersonaPoliticamenteEspostaGeneratorTools tools;
    private static final Logger log = LoggerFactory.getLogger(PPEController.class);
    //private final PPEDocumentService pPEDocumentService;
    private final QuestionAnswerAdvisor questionAnswerAdvisor;
    //private final ExecutorService executorService;
    private final VectorStore vectorStore;
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
                         ChatClient.Builder builder, ChatMemory chatMemory, ResourceLoader resourceLoader,
                         //PPEDocumentService pPEDocumentService,
                          QuestionAnswerAdvisor questionAnswerAdvisor,
                         //ExecutorService executorService,
                          VectorStore vectorStore,
                         RetrievalAugmentationAdvisor retrievalAugmentationAdvisor
                          ) {
       //this.tools=tools;
        this.resourceLoader = resourceLoader;
       // this.pPEDocumentService = pPEDocumentService;
        this.questionAnswerAdvisor = questionAnswerAdvisor;
        //this.executorService = executorService;
        this.vectorStore = vectorStore;
        this.retrievalAugmentationAdvisor = retrievalAugmentationAdvisor;

        this.chatClient = builder
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
              //.defaultSystem(resourceLoader.getResource("classpath:/prompts/ppe.st"))
                //.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
               //.defaultTools(tools)
               .build();
    }

    @GetMapping("/info")
    public String myFamilyFaq(@RequestParam String message) {
        log.info("message: {}", message);
        return this.chatClient.prompt()
                .user(message)
                .advisors(retrievalAugmentationAdvisor)
                //.advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
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

 */

    private String createPrompt(String query) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_BLUEPRINT);
       // ObjectMapper objectMapper = new ObjectMapper();
      //  objectMapper.registerModule(new JavaTimeModule());
     //   objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        promptTemplate.add("query", query);
        return promptTemplate.render();
    }


}
