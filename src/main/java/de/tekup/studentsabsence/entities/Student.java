package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"image","group","absences"})
public class Student implements Serializable {
    //TODO Complete Validations of fields


    @Id
    private Long sid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    //TODO Complete Relations with other entities
    @OneToMany(mappedBy = "student")
    private List<Absence> absences;
    @ManyToOne
    private Group group;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

}
