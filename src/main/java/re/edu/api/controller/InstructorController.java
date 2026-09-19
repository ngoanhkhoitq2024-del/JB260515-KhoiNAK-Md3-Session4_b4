package re.edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.api.dto.ApiResponse;
import re.edu.api.dto.InstructorCreateRequest;
import re.edu.api.model.Instructor;
import re.edu.api.service.InstructorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    // Lấy full ds
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        List<Instructor> instructors = instructorService.findAllInstructors();

        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy danh sách giảng viên thành công", instructors));
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.findInstructorById(id);

        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Lấy giảng viên thành công", instructor));
    }

    // Thêm
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(
            @RequestBody InstructorCreateRequest req) {

        instructorService.createInstructor(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                true, "Tạo giảng viên thành công", null));
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateInstructor(
            @PathVariable Long id,
            @RequestBody Instructor instructor) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructor);
        if (updatedInstructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Cập nhật giảng viên thành công", null));
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(@PathVariable Long id) {
        Instructor deletedInstructor = instructorService.deleteInstructorById(id);
        if (deletedInstructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, "Không tìm thấy giảng viên với ID: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Xóa giảng viên thành công", null));
    }
}