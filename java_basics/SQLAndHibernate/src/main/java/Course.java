import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NaturalId
    private String name;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @Column(name = "description", nullable = true)
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)   // LAZY - "ленивая загрузка, чтобы не запрашивать
    private Teacher teacher;                                        //         всех учителей, если они не нужны"
    @Column(name = "students_count", nullable = true)
    private int studentsCount;
    private int price;
    @Column(name = "price_per_hour", nullable = true)
    private float pricePerHour;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Subscriptions", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
