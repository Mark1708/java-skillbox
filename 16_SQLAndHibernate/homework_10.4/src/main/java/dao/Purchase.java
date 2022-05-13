package dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "PurchaseList")
public class Purchase {

    @EmbeddedId
    private Key key;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @Data
    @Embeddable
    public static class Key implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;
    }
}
