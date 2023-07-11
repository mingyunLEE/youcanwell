package nom.youcanwell.course.service;

import nom.youcanwell.course.entity.Course;
import nom.youcanwell.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course){
        return saveCourse(course);
    }

    private Course saveCourse(Course course){
        return courseRepository.save(course);
    }
}
