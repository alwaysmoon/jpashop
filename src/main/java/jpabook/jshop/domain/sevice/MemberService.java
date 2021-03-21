package jpabook.jshop.domain.sevice;

import jpabook.jshop.domain.Member;
import jpabook.jshop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //spring 트랜잭션 옵션이 많아서 추천 // 조회시 성능 최적화. 읽기 전용 트랜잭션
//@AllArgsConstructor
// final 필드만 생성자 생성, test case 작성시 용의
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 생성자 injection
//    @Autowired
//    public MemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // setter injection
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

        // member 를 변환하명 영속상태 끊긴 member 가 조회되어 반환됨
        // 업데이트와 조회 둘 다 이루어짐
    }


}
