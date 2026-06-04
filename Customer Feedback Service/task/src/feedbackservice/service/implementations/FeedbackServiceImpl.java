package feedbackservice.service.implementations;

import feedbackservice.dto.FeedbackPageResponseDto;
import feedbackservice.dto.FeedbackResponseDto;
import feedbackservice.model.Feedback;
import feedbackservice.repository.FeedbackRepository;
import feedbackservice.service.interfaces.FeedbackService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repository;

    public FeedbackServiceImpl(FeedbackRepository repository) {
        this.repository = repository;
    }
    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    @Override
    public FeedbackPageResponseDto getAllFeedbacks(
            int page,
            int perPage,
            Integer rating,
            String customer,
            String product,
            String vendor) {

        if (page < 1) {
            page = 1;
        }

        if (perPage < 5 || perPage > 20) {
            perPage = 10;
        }

        Feedback probe = new Feedback();

        probe.setRating(rating);
        probe.setCustomer(customer);
        probe.setProduct(product);
        probe.setVendor(vendor);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues();

        Example<Feedback> example =
                Example.of(probe, matcher);

        Pageable pageable = PageRequest.of(
                page - 1,
                perPage,
                Sort.by(Sort.Direction.DESC, "_id")
        );

        Page<Feedback> result =
                repository.findAll(example, pageable);

        List<FeedbackResponseDto> documents =
                result.getContent()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return new FeedbackPageResponseDto(
                result.getTotalElements(),
                result.isFirst(),
                result.isLast(),
                documents
        );
    }

    @Override
    public FeedbackResponseDto getFeedbackById(String id) {
        Feedback feedback = repository.findById(id)
                .orElseThrow();

        return toResponse(feedback);

    }
    private FeedbackResponseDto toResponse(Feedback feedback) {
        return new FeedbackResponseDto(
                feedback.getId(),
                feedback.getRating(),
                feedback.getFeedback(),
                feedback.getCustomer(),
                feedback.getProduct(),
                feedback.getVendor()
        );

    }


}