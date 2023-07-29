package hellojpa.domain_practice;

import javax.persistence.*;

@Entity
public class Selection {

    @Id @GeneratedValue
    @Column(name = "SELECTION_ID")
    private Long selection_id;

//    @ManyToOne
//    @JoinColumn(name = "POST_ID")
//    private Post post;

    private String image;
    private String content;
    private Double vote_percentage;
    private Integer each_voted_count;
}
