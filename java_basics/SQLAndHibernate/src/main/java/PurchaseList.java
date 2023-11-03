import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {
    @EmbeddedId
    private PurchaseListKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Course course;
    private int price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_date", referencedColumnName = "subscription_date")
    private Subscription subscription;

    public PurchaseListKey getId() {
        return id;
    }

    public void setName(PurchaseListKey id) {
        this.id = id;
    }

    public void setId(PurchaseListKey id) {
        this.id = id;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
