import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class PurchaseLists {

    @EmbeddedId
    private KeyPurchaseList id;

    @Column(name = "student_name",insertable = false,updatable = false)
    private String studentName;

    @Column(name = "course_name",insertable = false,updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionData;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionData() {
        return subscriptionData;
    }

    public void setSubscriptionData(Date subscriptionData) {
        this.subscriptionData = subscriptionData;
    }

}
