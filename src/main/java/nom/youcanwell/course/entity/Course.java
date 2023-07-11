package nom.youcanwell.course.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private CourseCategory courseCategory;

    @Column(nullable = false)
    private String courseTitle;

    @Column(nullable = false)
    private String courseDescription;

    @Column(nullable = false)
    private LocalDate courseStartDate;

    @Builder
    public Course(Long courseId, CourseCategory courseCategory, String courseTitle, String courseDescription, LocalDate courseStartDate) {
        this.courseId = courseId;
        this.courseCategory = courseCategory;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseStartDate = courseStartDate;
    }

    public enum CourseCategory {
        HEALTH("건강"),
        COOKING("요리");

        @Getter
        private String category;

        CourseCategory(String category){
            this.category = category;
        }
    }
}
