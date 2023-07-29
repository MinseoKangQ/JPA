package hellojpa.domain_practice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User comment_user;

    private String image;
    private String content;
    private LocalDateTime created_at;


}
