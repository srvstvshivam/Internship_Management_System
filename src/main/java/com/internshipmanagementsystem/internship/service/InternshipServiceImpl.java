package com.internshipmanagementsystem.internship.service;

import com.internshipmanagementsystem.internship.dto.InternshipRequest;
import com.internshipmanagementsystem.internship.dto.InternshipResponse;
import com.internshipmanagementsystem.internship.model.Internship;
import com.internshipmanagementsystem.internship.model.InternshipStatus;
import com.internshipmanagementsystem.internship.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

  
    @Override
    public InternshipResponse createInternship(InternshipRequest request, Long coordinatorId) {

        if (internshipRepository.existsByTitle(request.getTitle())) {
            throw new RuntimeException("Internship with this title already exists");
        }

        Internship internship = new Internship();

        
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setDomain(request.getDomain());
        internship.setTechStack(request.getTechStack());
        internship.setCompanyName("C-DAC Delhi"); 
        internship.setSector(request.getSector());
        internship.setLocation(request.getLocation());

        
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
        internship.setDurationWeeks(request.getDurationWeeks());
        internship.setDurationInMonths(request.getDurationInMonths());
        internship.setApplicationDeadline(request.getApplicationDeadline());

        
        internship.setMaxStudents(request.getMaxStudents());
        internship.setEnrolledStudents(0);

        
        internship.setEligibilityCriteria(request.getEligibilityCriteria());
        internship.setMinimumPercentage(request.getMinimumPercentage());
        internship.setMinPassoutYear(request.getMinPassoutYear());
        internship.setMaxPassoutYear(request.getMaxPassoutYear());
        internship.setNoActiveBacklogsRequired(
                request.getNoActiveBacklogsRequired() != null
                        ? request.getNoActiveBacklogsRequired() : false);

        
        internship.setStipendAmount(request.getStipendAmount());
        internship.setIsPaid(request.getIsPaid() != null ? request.getIsPaid() : false);

        
        internship.setStatus(InternshipStatus.DRAFT);
        internship.setScheduleApproved(false);
        internship.setCoordinatorId(coordinatorId);
        internship.setCreatedByCoordinatorId(coordinatorId);

        
        internship.setCreatedAt(LocalDateTime.now());
        internship.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(internshipRepository.save(internship));
    }

   
    @Override
    public InternshipResponse updateInternship(Long id, InternshipRequest request) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id: " + id));

        if (internship.getStatus() == InternshipStatus.OPEN ||
                internship.getStatus() == InternshipStatus.ONGOING) {
            throw new RuntimeException("Cannot update an active internship");
        }

        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setDomain(request.getDomain());
        internship.setTechStack(request.getTechStack());
        internship.setSector(request.getSector());
        internship.setLocation(request.getLocation());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
        internship.setDurationWeeks(request.getDurationWeeks());
        internship.setDurationInMonths(request.getDurationInMonths());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setMaxStudents(request.getMaxStudents());
        internship.setEligibilityCriteria(request.getEligibilityCriteria());
        internship.setMinimumPercentage(request.getMinimumPercentage());
        internship.setMinPassoutYear(request.getMinPassoutYear());
        internship.setMaxPassoutYear(request.getMaxPassoutYear());
        internship.setNoActiveBacklogsRequired(
                request.getNoActiveBacklogsRequired() != null
                        ? request.getNoActiveBacklogsRequired() : false);
        internship.setStipendAmount(request.getStipendAmount());
        internship.setIsPaid(request.getIsPaid() != null ? request.getIsPaid() : false);
        internship.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(internshipRepository.save(internship));
    }

    
    @Override
    public InternshipResponse publishInternship(Long id) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id: " + id));

        if (internship.getStatus() != InternshipStatus.DRAFT &&
                internship.getStatus() != InternshipStatus.PENDING_APPROVAL) {
            throw new RuntimeException("Only DRAFT internships can be published");
        }

        internship.setStatus(InternshipStatus.OPEN);
        internship.setPublishedAt(LocalDateTime.now());
        internship.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(internshipRepository.save(internship));
    }

    
    @Override
    public InternshipResponse closeInternship(Long id) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id: " + id));

        if (internship.getStatus() != InternshipStatus.OPEN) {
            throw new RuntimeException("Only OPEN internships can be closed");
        }

        internship.setStatus(InternshipStatus.CLOSED);
        internship.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(internshipRepository.save(internship));
    }

    
    @Override
    public void deleteInternship(Long id) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id: " + id));

        if (internship.getStatus() != InternshipStatus.DRAFT) {
            throw new RuntimeException("Only DRAFT internships can be deleted");
        }

        internshipRepository.delete(internship);
    }

    
    @Override
    public InternshipResponse getInternshipById(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id: " + id));
        return mapToResponse(internship);
    }

    
    @Override
    public List<InternshipResponse> getAllInternships() {
        return internshipRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    
    @Override
    public List<InternshipResponse> getInternshipsByStatus(String status) {
        try {
            InternshipStatus internshipStatus = InternshipStatus.valueOf(status.toUpperCase());
            return internshipRepository.findByStatus(internshipStatus)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status +
                    ". Valid values: DRAFT, PENDING_APPROVAL, OPEN, CLOSED, ONGOING, COMPLETED");
        }
    }

    
    @Override
    public List<InternshipResponse> getInternshipsByCoordinator(Long coordinatorId) {
        return internshipRepository.findByCoordinatorId(coordinatorId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

   
    private InternshipResponse mapToResponse(Internship i) {
        return new InternshipResponse(
                i.getId(), i.getTitle(), i.getDescription(), i.getDomain(),
                i.getTechStack(), i.getCompanyName(), i.getSector(), i.getLocation(),
                i.getStartDate(), i.getEndDate(), i.getDurationWeeks(), i.getDurationInMonths(),
                i.getApplicationDeadline(), i.getMaxStudents(), i.getEnrolledStudents(),
                i.getEligibilityCriteria(), i.getMinimumPercentage(),
                i.getMinPassoutYear(), i.getMaxPassoutYear(), i.getNoActiveBacklogsRequired(),
                i.getStipendAmount(), i.getIsPaid(), i.getStatus(), i.getScheduleApproved(),
                i.getCoordinatorId(), i.getCreatedAt(), i.getUpdatedAt(), i.getPublishedAt()
        );
    }
}