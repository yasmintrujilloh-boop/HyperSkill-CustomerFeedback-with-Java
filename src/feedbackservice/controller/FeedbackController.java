package feedbackservice.controller;

import feedbackservice.dto.FeedbackPageResponseDto;
import feedbackservice.dto.FeedbackRequestDto;
import feedbackservice.dto.FeedbackResponseDto;
import feedbackservice.model.Feedback;
import feedbackservice.service.interfaces.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {
    public final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody FeedbackRequestDto request) {

        Feedback feedback = new Feedback();
        feedback.setRating(request.getRating());
        feedback.setFeedback(request.getFeedback());
        feedback.setCustomer(request.getCustomer());
        feedback.setProduct(request.getProduct());
        feedback.setVendor(request.getVendor());

        Feedback saved = this.feedbackService.saveFeedback(feedback);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/feedback/" + saved.getId())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> getFeedback(
            @PathVariable String id) {

        try {
            FeedbackResponseDto response = feedbackService.getFeedbackById(id);
            return ResponseEntity.ok(response);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<FeedbackPageResponseDto> getAllFeedback(

            @RequestParam(defaultValue = "1") int page,

            @RequestParam(defaultValue = "10") int perPage,

            @RequestParam(required = false) Integer rating,

            @RequestParam(required = false) String customer,

            @RequestParam(required = false) String product,

            @RequestParam(required = false) String vendor
    ) {

        return ResponseEntity.ok(
                feedbackService.getAllFeedbacks(
                        page,
                        perPage,
                        rating,
                        customer,
                        product,
                        vendor
                )
        );
    }


}
