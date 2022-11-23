package edu.abl.poty.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "persons")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // full name of person
    private String name;

    @Column // e.g "King of Saudi Arabia"
    private String title;

    @Column // year of award
    private int year;

    @Column // 'man of the year', 'woman of the year', 'persons ...', etc...
    @ApiModelProperty(notes = "'man of the year', 'woman of the year', 'persons ...', etc..")
    private String honor;

    @Column // residing country?
    private String country;

    @Column // birth year of person
    private Integer birthYear;

    @Column // death year of person
    private Integer deathYear; // Integer instead of int, so that it nullable (since person might not be dead yet lol)

    @Column // economics, politics, science, activism etc ...
    private String category;

    @Column // context for the award
    private String context;

}