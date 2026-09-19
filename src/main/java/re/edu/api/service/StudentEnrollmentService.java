package re.edu.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.api.model.Course;
import re.edu.api.model.Student;
import re.edu.api.model.StudentEnrollment;
import re.edu.api.repository.ICourseRepository;
import re.edu.api.repository.IStudentEnrollmentRepository;
import re.edu.api.repository.IStudentRepository;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentService {
    private final IStudentRepository studentRepository;
    private final ICourseRepository courseRepository;
    private final IStudentEnrollmentRepository enrollmentRepository;

    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Student với ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy Course với ID: " + courseId));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }
}