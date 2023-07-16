package nom.youcanwell.member.mapper;

import nom.youcanwell.member.dto.MemberDto;
import nom.youcanwell.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    List<MemberDto.listResponse> memberListResponses(List<Member> memberList);
    // 회원 정보를 수정할때
    default Member memberPatchToMember(MemberDto.Patch patchMember) {
        Member member = new Member();

        member.setMemberName(patchMember.getMemberName());
        member.setMemberDescription(patchMember.getMemberDescription());
        member.setMemberImage(patchMember.getMemberImage());

        return member;
    }

    default MemberDto.detailResponse memberToDetailResponse(Member member) {
        MemberDto.detailResponse memberDetailResponse = new MemberDto.detailResponse();

        memberDetailResponse.setMemberId(member.getMemberId());
        memberDetailResponse.setMemberDescription(member.getMemberDescription());
        memberDetailResponse.setMemberEmail(member.getMemberEmail());
        memberDetailResponse.setMemberName(member.getMemberName());
        memberDetailResponse.setMemberMoney(member.getMemberMoney());
        memberDetailResponse.setMemberImage(member.getMemberImage());

        return memberDetailResponse;
    }

    default MemberDto.listResponse memberToListResponse(Member member) {
        MemberDto.listResponse memberListResponse = new MemberDto.listResponse();

        memberListResponse.setMemberId(member.getMemberId());
        memberListResponse.setMemberName(member.getMemberName());
        memberListResponse.setCreated_at(member.getCreated_at());

        return memberListResponse;
    }
}
