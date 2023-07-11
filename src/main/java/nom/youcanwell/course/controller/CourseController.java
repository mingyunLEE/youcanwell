package nom.youcanwell.course.controller;

import nom.youcanwell.course.mapper.CourseMapper;
import nom.youcanwell.course.controller.service.CourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    public CourseController(CourseMapper courseMapper, CourseService courseService) {
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }


}
