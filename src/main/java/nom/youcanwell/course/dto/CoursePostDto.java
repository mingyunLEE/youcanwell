package nom.youcanwell.course.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CoursePostDto {
    private String courseCategory;
    @NotNull
    private String courseTitle;
    private String courseDescription;
    private LocalDate courseStartDate;
    private String courseAuthDescription;
    private int coursePrice;
    private int courseViewCount;
}
