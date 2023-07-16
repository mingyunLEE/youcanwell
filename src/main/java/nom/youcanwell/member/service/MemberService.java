package nom.youcanwell.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nom.youcanwell.exception.BusinessLogicException;
import nom.youcanwell.exception.ExceptionCode;
import nom.youcanwell.member.entity.Member;
import nom.youcanwell.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        String random = UUID.randomUUID().toString().substring(0, 6);

        member.setMemberEmail(random + "@gmail.com");

        member.setMemberName(random);

        return memberRepository.save(member);
    }

    public Member findMemberById(Long memberId) {
        return verifiedMemberById(memberId);
    }

    public Member findMemberByMemberName(String memberName) {
        return verifiedMemberByMemberName(memberName);
    }

    public Page<Member> findAllMember(int page, int size, String sort) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }


    private Member verifiedMemberByMemberName(String memberName) {
        Optional<Member> optionalMember = memberRepository.findByMemberName(memberName);
        Member foundMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return foundMember;
    }

    private Member verifiedMemberById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member foundMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return foundMember;
    }

    public Member updateMemberInfo(Long memberId, Member member) {
        Member memberFromRepository = verifiedMemberById(memberId);
        verifyExistsMemberName(member.getMemberName());
        log.info("patch.name = {}", member.getMemberName());

        Optional.ofNullable(member.getMemberName())
                .ifPresent(new_memberName -> memberFromRepository.setMemberName(new_memberName));
        Optional.ofNullable(member.getMemberDescription())
                .ifPresent(new_memberDescription -> memberFromRepository.setMemberDescription(new_memberDescription));
        Optional.ofNullable(member.getMemberImage())
                .ifPresent(new_memberImage -> memberFromRepository.setMemberImage(new_memberImage));

        return memberRepository.save(memberFromRepository);
    }

    private void verifyExistsMemberName(String memberName) {
        Optional<Member> member = memberRepository.findByMemberName(memberName);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_NAME_ALREADY_EXISTS);
        }
    }
}