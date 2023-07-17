package nom.youcanwell.course.controller;

import nom.youcanwell.course.dto.CoursePostDto;
import nom.youcanwell.course.entity.Course;
import nom.youcanwell.course.mapper.CourseMapper;
import nom.youcanwell.course.service.CourseService;
import nom.youcanwell.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping()
    public ResponseEntity postCorse(@Valid @RequestBody CoursePostDto coursePostDto){
        Course course = courseMapper.coursePostDtoToCourse(coursePostDto);
        course = courseService.createCourse(course);

        return new ResponseEntity<>(
                new SingleResponseDto<>(courseMapper.courseToCourseResponseDto(course))
                , HttpStatus.CREATED);
    }


}
