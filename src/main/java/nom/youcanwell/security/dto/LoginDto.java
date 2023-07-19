package nom.youcanwell.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class LoginDto {
    private Long memberId;
    private String memberEmail;
    private String memberName;
    private String imageUrl;
    private String AccessToken;
    private String RefreshToken;
}
