package re.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.api.dto.ApiResponse;
import re.edu.api.dto.CourseCreateRequest;
import re.edu.api.dto.CourseUpdateRequest;
import re.edu.api.model.Course;
import re.edu.api.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    // Lấy all ds
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy danh sách khóa học thành công", courses));
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy khóa học thành công", course));
    }

    // Thêm
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true, "Tạo khóa học thành công", null));
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest req
    ) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Cập nhật khóa học thành công", null));
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Xóa khóa học thành công", null));
    }
}