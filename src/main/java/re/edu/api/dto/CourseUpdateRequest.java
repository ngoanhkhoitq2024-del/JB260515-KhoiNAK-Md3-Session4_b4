package re.edu.api.dto;

import re.edu.api.model.CourseStatus;

public record CourseUpdateRequest(
        String title,
        CourseStatus status,
        Long instructorId
){
}
