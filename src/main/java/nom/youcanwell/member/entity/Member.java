package nom.youcanwell.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nom.youcanwell.audit.TimeAudit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Member_Table")
public class Member extends TimeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String memberDescription;

    @Column(nullable = false,unique = true)
    private String memberEmail;

    @Column(nullable = false,unique = true)
    private String memberName;

    @Column(nullable = false)
    private double memberMoney=0;

    @Column(nullable = false)
    private String memberImage = "image";

}
