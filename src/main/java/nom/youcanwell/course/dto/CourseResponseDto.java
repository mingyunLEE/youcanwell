package nom.youcanwell.course.dto;

import lombok.Builder;
import lombok.Getter;
import nom.youcanwell.course.entity.Course;

import java.time.LocalDate;

@Getter
@Builder
public class CourseResponseDto {
    private Long CourseId;
    private Course.CourseCategory courseCategory;
    private String courseTitle;
    private String courseDescription;
    private LocalDate courseStartDate;
    private String courseAuthDescription;
    private int coursePrice;
    private int courseViewCount;
}
