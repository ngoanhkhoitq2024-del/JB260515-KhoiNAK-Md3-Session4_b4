package re.edu.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.api.dto.CourseCreateRequest;
import re.edu.api.dto.CourseUpdateRequest;
import re.edu.api.model.Course;
import re.edu.api.model.Instructor;
import re.edu.api.repository.ICourseRepository;
import re.edu.api.repository.IInstructorRepository;

import java.util.List;

@Service
public class CourseService {
    private final ICourseRepository courseRepository;
    private final IInstructorRepository iInstructorRepository;

    @Autowired
    public CourseService(ICourseRepository courseRepository, IInstructorRepository iInstructorRepository) {
        this.courseRepository = courseRepository;
        this.iInstructorRepository = iInstructorRepository;
    }

    // Lấy tất cả Course
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    // Lấy Course theo ID
    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy khóa học với ID: " + id));
    }

    // Thêm Course
    public Course createCourse(CourseCreateRequest req) {
        Instructor instructor = iInstructorRepository.findById(req.instructorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên"));
        Course course = new Course();
        course.setTitle(req.title());
        course.setStatus(req.status());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    // Cập nhật Course
    public Course updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy khóa học với ID: " + id));

        Instructor instructor = iInstructorRepository.findById(req.instructorId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy giảng viên với ID: " + req.instructorId()));

        course.setTitle(req.title());
        course.setStatus(req.status());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    // Xóa Course
    public void deleteCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy khóa học với ID: " + id));
        courseRepository.delete(course);
    }
}