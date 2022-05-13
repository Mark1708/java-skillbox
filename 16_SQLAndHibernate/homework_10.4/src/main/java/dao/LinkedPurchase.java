package dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase {

    @EmbeddedId
    private Key key;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public LinkedPurchase(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.key = new Key(studentName, courseName);
    }

    public LinkedPurchase() {

    }


    @Data
    @Embeddable
    public static class Key implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        public Key(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }

        public Key() {

        }
    }
}