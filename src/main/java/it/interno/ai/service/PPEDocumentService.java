package it.interno.ai.service;

import it.interno.ai.model.PPEDistro;
import it.interno.ai.repository.PPEDocumentsRepository;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mirco.cennamo on 02/03/2025
 * @project my-spring-boot-ai
 */
@Service
public class PPEDocumentService {

    private final PPEDocumentsRepository pPEDocumentsRepository;
    private final ResourceLoader resourceLoader;
    // constructors


    public PPEDocumentService(PPEDocumentsRepository pPEDocumentsRepository, ResourceLoader resourceLoader) {
        this.pPEDocumentsRepository = pPEDocumentsRepository;
        this.resourceLoader = resourceLoader;
    }



    public List<PPEDistro> findSimilarDocuments(String searchText) {
        return pPEDocumentsRepository.findSimilarDocuments(searchText);
    }
}