package feedbackservice.service.interfaces;

import feedbackservice.dto.FeedbackPageResponseDto;
import feedbackservice.dto.FeedbackResponseDto;
import feedbackservice.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback saveFeedback(Feedback feedback);

    FeedbackPageResponseDto getAllFeedbacks(int page,
                                            int perPage,
                                            Integer rating,
                                            String customer,
                                            String product,
                                            String vendor);

    FeedbackResponseDto getFeedbackById(String id);


}