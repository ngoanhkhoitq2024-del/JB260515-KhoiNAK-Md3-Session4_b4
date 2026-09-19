package re.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.api.dto.InstructorCreateRequest;
import re.edu.api.model.Instructor;
import re.edu.api.repository.IInstructorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final IInstructorRepository instructorRepository;

    // Lấy tất cả Instructor
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    // Lấy theo ID
    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElse(null);
    }

    // Thêm
    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    // Cập nhật
    public Instructor updateInstructor(Long id, Instructor instructor) {
        if (!instructorRepository.existsById(id)) {
            return null;
        }
        instructor.setId(id);
        return instructorRepository.save(instructor);
    }

    // Xóa theo ID
    public Instructor deleteInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor == null) {
            return null;
        }
        instructorRepository.deleteById(id);
        return instructor;
    }
}
