package it.interno.ai.repository;

import it.interno.ai.model.PPEDistro;
import it.interno.ai.tools.PersonaPoliticamenteEspostaGeneratorTools;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.ai.vectorstore.SearchRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mirco.cennamo on 02/03/2025
 * @project my-spring-boot-ai
 */
@Component
public class PPEDocumentsRepository {
    private final VectorStore vectorStore;
    private final ResourceLoader resourceLoader;
    private static final Logger log = LoggerFactory.getLogger(PPEDocumentsRepository.class);

    public PPEDocumentsRepository(VectorStore vectorStore, ResourceLoader resourceLoader) {
        this.vectorStore = vectorStore;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void savePPE() throws IOException {
        log.info("start savePPE");
        Map<String, Object> metadata = new HashMap<>();
        String filePath = "classpath:/ppe_ridotto.json";
        Resource resource = resourceLoader.getResource(filePath);
        JsonReader jsonReader = new JsonReader(resource,
                "COGNOME", "NOME", "DATA NASCITA (gg/mm/aaaa)", "LUOGO NASCITA", "PROVINCIA NASCITA","DATA FINE CONTROLLO (gg/mm/aaaa)","ENTE DI APPARTENENZA","RUOLO");
        List<Document> documents = jsonReader.get();
        this.vectorStore.add(documents);
        log.info("finished savePPE with {} documents", documents.size());
    }




    /*public List<PPEDistro> findSimilarDocuments(String searchText) {
        return vectorStore
                .similaritySearch(SearchRequest.builder()
                        .query(searchText)
                        .similarityThreshold(0.87)
                        .topK(10)
                        .build())
                .stream()
                .map(document -> {
                    PPEDistro wikiDocument = new PPEDistro();
                    wikiDocument.setFilePath((String) document.getMetadata().get("filePath"));
                    wikiDocument.setContent(document.getFormattedContent());
                    return wikiDocument;
                })
                .toList();
    }

     */
}