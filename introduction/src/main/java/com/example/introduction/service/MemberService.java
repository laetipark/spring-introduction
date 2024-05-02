package com.example.introduction.service;

import com.example.introduction.dto.MemberDTO;
import com.example.introduction.entity.Member;
import com.example.introduction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(MemberDTO memberDTO) {
        Member memberEntity = Member.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // email로 회원 정보 조회
        Optional<Member> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());

        // email 조회 결과가 있는 경우
        if (byMemberEmail.isPresent()) {
            Member member = byMemberEmail.get();
            return MemberDTO.toMemberDTO(member);
        }

        // email 조회 결과가 없는 경우
        return null;
    }
}
