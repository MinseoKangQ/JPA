//package hellojpa.domain_practice;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "GOMGOMUSER")
//public class User {
//
//    @Id @GeneratedValue
//    @Column(name = "USER_ID")
//    private Long user_id;
//
//    private String pw;
//    private String image;
//
//    @OneToMany(mappedBy = "post_user")
//    private List<Post> posts = new ArrayList<>();
//
//    @OneToMany(mappedBy = "comment_user")
//    private List<Comment> comments = new ArrayList<>();
//}
