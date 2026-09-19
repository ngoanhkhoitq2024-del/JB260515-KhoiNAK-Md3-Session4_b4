package re.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.api.dto.ApiResponse;
import re.edu.api.service.StudentEnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class StudentEnrollmentController {
    private final StudentEnrollmentService studentEnrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enrollStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        studentEnrollmentService.enrollStudent(studentId, courseId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true, "Đăng ký khóa học thành công", null));
    }
}