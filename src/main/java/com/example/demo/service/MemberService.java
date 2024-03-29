package com.example.demo.service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회 시 readOnly = true 해당 속성을 주면 최적화됨
//@AllArgsConstructor // 현재 클래스가 가지고 있는 필드를 가지고 생성자를 만들어줌
@RequiredArgsConstructor // 현재 클래스가 가지고 있는 필드 중 private 필드만을 가지고 생성자를 만들어줌
public class MemberService {

    private MemberRepository memberRepository;
//    @Autowired // 클래스 간의 의존관계를 스프링 컨테이너가 자동으로 연결해줌
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
    /**
     *  회원 가입
     */
    @Transactional // 데이터 변경 시 트렌젝션 안에서 진행됨
    public Long join(MemberDTO member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    /**
     *  회원 가입 시 예외 처리
     */
    private void validateDuplicateMember(MemberDTO member) { // 만약 중복 회원이 있으면 예외 처리
        List<MemberDTO> findMembers = memberRepository.findByStudentId(member.getStudentId());
        if(!findMembers.isEmpty()){ // 해당 결과값 문자열 길이가 0이 아니면
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 이미 존재하는 학번임
        }

    }
    /**
     *  회원 전체 조회
     */
    public List<MemberDTO> findMembers(){
        return memberRepository.findAll();
    }
    /**
     *  회원 단권 조회
     */
    public MemberDTO findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
