package re.edu.api.dto;

import re.edu.api.model.CourseStatus;

public record CourseCreateRequest(
    String title,
    CourseStatus status,
    Long instructorId) {
}
