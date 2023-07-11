package nom.youcanwell.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class WriterAudit {
    @Column(updatable = false)
    private String create_by_member;

    @Column
    private String updated_by_member;
}
