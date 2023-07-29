package hellojpa.domain_practice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User post_user;

    @OneToMany(mappedBy = "comment_user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "selection_id")
    private List<Selection> selections = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String title;
    private String image;
    private String content;
    private LocalDateTime created_at;
//    private Integer view_count;
//    private Integer like_count;
//    private Integer all_voted_count;

}
