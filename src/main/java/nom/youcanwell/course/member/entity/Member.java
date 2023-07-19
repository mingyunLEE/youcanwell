package nom.youcanwell.course.member.entity;

import lombok.*;
import nom.youcanwell.audit.TimeAudit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Member_Table")
public class Member extends TimeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String memberDescription;

    @Builder
    public Member(String memberEmail, String memberImage, List<String> roles, String provider, String providerId) {

        this.roles = roles;
        this.provider = provider;
        this.providerId = providerId;
        this.memberEmail = memberEmail;
        this.memberImage = memberImage;

        this.memberName = "임의값";
        this.memberMoney = 0;
        this.memberDescription = "";
    }

    @Column(nullable = false,unique = true)
    private String memberEmail;

    @Column(nullable = false,unique = true)
    private String memberName;

    @Column
    private double memberMoney=0;

    @Column(nullable = false)
    private String memberImage = "image";


    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column
    private String provider;
    @Column
    private String providerId;

}
