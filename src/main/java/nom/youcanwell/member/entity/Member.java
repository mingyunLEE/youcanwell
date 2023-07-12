package nom.youcanwell.member.entity;

import nom.youcanwell.audit.TimeAudit;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

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

}
