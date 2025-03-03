package it.interno.ai.configuration;

import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionCallingChatConfiguration {
    @Bean
    InMemoryChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        return new QuestionAnswerAdvisor(vectorStore);
    }

   // @Bean
   // VectorStore vectorStore(EmbeddingModel embeddingModel) {
   //     return SimpleVectorStore.builder(embeddingModel).build();
   // }

    @Bean
    RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore) {
        VectorStoreDocumentRetriever documentRetriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .similarityThreshold(0.80) // Aumenta la soglia di similarità per risultati più precisi
                .topK(3) // Riduci il numero di documenti recuperati
                .build();

        ContextualQueryAugmenter queryAugmenter = ContextualQueryAugmenter.builder()
                .allowEmptyContext(false) // Non permettere contesti vuoti per query più rilevanti
               // .maxContextLength(300) // Limita la lunghezza del contesto per migliorare le prestazioni
                .build();

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .queryAugmenter(queryAugmenter)
                .build();
    }





}
