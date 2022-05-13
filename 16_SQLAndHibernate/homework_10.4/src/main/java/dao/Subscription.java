package dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private Key id;


    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Data
    @Embeddable
    public static class Key implements Serializable {
        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;
    }



}
