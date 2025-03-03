package it.interno.ai.configuration;

import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SimpleVectorStore;
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
                .similarityThreshold(0.50)
                .topK(5)
                .build();

        ContextualQueryAugmenter queryAugmenter = ContextualQueryAugmenter.builder()
                .allowEmptyContext(true)
                .build();

        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .queryAugmenter(queryAugmenter)
                .build();
    }





}
