package nom.youcanwell.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "닉네임은 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
        private String memberName;

        private String memberDescription;

        private String memberImage;

        private boolean hasRedCard;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class detailResponse {
        private long memberId;
        private String memberDescription;
        private String memberEmail;
        private String memberName;
        private double memberMoney;
        private String memberImage;

    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class listResponse {
        private Long memberId;
        private String memberName;
        private LocalDateTime created_at;
    }
}
