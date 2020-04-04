package com.mycompany.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-03T22:31:38")
@StaticMetamodel(AssignmentMovie.class)
public class AssignmentMovie_ { 

    public static volatile SingularAttribute<AssignmentMovie, byte[]> image;
    public static volatile SingularAttribute<AssignmentMovie, Date> releaseDate;
    public static volatile SingularAttribute<AssignmentMovie, String> director;
    public static volatile SingularAttribute<AssignmentMovie, String> description;
    public static volatile SingularAttribute<AssignmentMovie, String> producer;
    public static volatile SingularAttribute<AssignmentMovie, BigDecimal> id;
    public static volatile SingularAttribute<AssignmentMovie, String> title;
    public static volatile SingularAttribute<AssignmentMovie, Double> budget;

}