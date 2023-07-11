package nom.youcanwell.course.mapper;

import nom.youcanwell.course.dto.CoursePostDto;
import nom.youcanwell.course.dto.CourseResponseDto;
import nom.youcanwell.course.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course coursePostDtoToCourse(CoursePostDto coursePostDto);
    CourseResponseDto courseToCourseResponseDto(Course course);
}
