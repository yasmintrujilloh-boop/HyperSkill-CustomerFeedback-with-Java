package feedbackservice.dto;

import java.util.List;

public class FeedbackPageResponseDto {
    private long total_documents;
    private boolean is_first_page;
    private boolean is_last_page;
    private List<FeedbackResponseDto> documents;

    public FeedbackPageResponseDto() {}

    public FeedbackPageResponseDto(
            long total_documents,
            boolean is_first_page,
            boolean is_last_page,
            List<FeedbackResponseDto> documents) {
        this.total_documents = total_documents;
        this.is_first_page = is_first_page;
        this.is_last_page = is_last_page;
        this.documents = documents;
    }

    public long getTotal_documents() {
        return total_documents;
    }

    public void setTotal_documents(long total_documents) {
        this.total_documents = total_documents;
    }

    public boolean isIs_first_page() {
        return is_first_page;
    }

    public void setIs_first_page(boolean is_first_page) {
        this.is_first_page = is_first_page;
    }

    public boolean isIs_last_page() {
        return is_last_page;
    }

    public void setIs_last_page(boolean is_last_page) {
        this.is_last_page = is_last_page;
    }

    public List<FeedbackResponseDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FeedbackResponseDto> documents) {
        this.documents = documents;
    }
}
