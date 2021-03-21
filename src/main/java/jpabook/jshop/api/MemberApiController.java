package jpabook.jshop.api;

import jpabook.jshop.domain.Member;
import jpabook.jshop.domain.sevice.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> memberV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }


    @GetMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMeberV2(@RequestBody @Valid CreateMemberRequest requqest) {

        Member member = new Member();
        member.setName(requqest.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // put 같은 호출을 여러번 해도 변함이 없음
    @PutMapping("/api/v2/member2/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        // 커맨드랑 쿼리를 분리한다? - 회원 수정 api
        // 변경감지
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    private class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    private class CreateMemberRequest {
        private String name;

    }

    // 현재 프로그램에서 DTO 를 안만드는 이유?
    // API 스펙에 맞는 DTO를 만들면 api 안 까도 사양을 알 수 있음
    // 엔티티는 직접 사용하지 않는걸 추천. 엔티티를 수정하면 api 스펙이 변함.
}
