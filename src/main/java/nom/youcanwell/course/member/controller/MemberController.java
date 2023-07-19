package nom.youcanwell.course.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nom.youcanwell.course.member.dto.MemberDto;
import nom.youcanwell.course.member.mapper.MemberMapper;
import nom.youcanwell.course.member.service.MemberService;
import nom.youcanwell.dto.MultiResponseDto;
import nom.youcanwell.dto.SingleResponseDto;
import nom.youcanwell.course.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;

    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity postMember() {
        Member member = new Member();

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToDetailResponse(createdMember)), HttpStatus.CREATED);
    }

    @GetMapping("/test/{memberId}")
    public ResponseEntity getMemberById(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findMemberById(memberId);

        return new ResponseEntity(
                new SingleResponseDto<>(mapper.memberToDetailResponse(member)),HttpStatus.OK);
    }

    @GetMapping("/{memberName}")
    public ResponseEntity getMemberByMemberName(@PathVariable("memberName") String memberName) {
        Member member = memberService.findMemberByMemberName(memberName);

        return new ResponseEntity(
                new SingleResponseDto<>(mapper.memberToDetailResponse(member)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size,
                                     @RequestParam(defaultValue = "questionId") String sort) {
        Page<Member> pageInformation = memberService.findAllMember(page - 1, size,sort);
        List<Member> allMembers = pageInformation.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(mapper.memberListResponses(allMembers),pageInformation),HttpStatus.OK);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") Long memberId,
                                      @Validated @RequestBody MemberDto.Patch patchData) {
        Member member = memberService.updateMemberInfo(memberId,mapper.memberPatchToMember(patchData));

        return new ResponseEntity(
                new SingleResponseDto<>(mapper.memberToDetailResponse(member)),HttpStatus.OK);
    }
}
