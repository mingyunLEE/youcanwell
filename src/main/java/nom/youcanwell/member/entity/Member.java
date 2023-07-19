package nom.youcanwell.member.entity;

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

    @Column(nullable = false,unique = true)
    private String memberEmail;

    @Column(nullable = false,unique = true)
    private String memberName;

    @Column
    private double memberMoney=0;

    @Column(nullable = false)
    private String memberImage = "image";

    @ElementCollection(fetch = FetchType.EAGER)
    @Column
    private List<String> roles = new ArrayList<>();

    @Column
    private String provider;
    @Column
    private String providerId;

}
