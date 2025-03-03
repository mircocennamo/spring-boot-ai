package it.interno.ai.model;

/**
 * @author mirco.cennamo on 02/03/2025
 * @project my-spring-boot-ai
 */
public class PPEDistro {
    private String filePath;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
