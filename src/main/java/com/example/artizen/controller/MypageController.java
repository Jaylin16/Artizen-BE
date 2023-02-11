package com.example.artizen.controller;

import com.example.artizen.dto.request.MypageRequestDto;
import com.example.artizen.security.MemberDetailsImpl;
import com.example.artizen.service.MypageService;
import com.example.artizen.util.TimeStamped;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MypageController extends TimeStamped {

    private final MypageService mypageService;

    @GetMapping(value = "/mypage")
    public ResponseEntity<?> mypage (@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return mypageService.mypage(memberDetails.getMember());
    }

    //내가 좋아요한 컨텐츠
    @GetMapping(value = "/hearts")
    public ResponseEntity<?> getHearts (@AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                        @RequestParam(value = "page") Integer page,
                                        @RequestParam(value = "size") Integer size) {
        Integer pageTemp = page -1;
        return mypageService.getHearts(memberDetails.getMember(), pageTemp, size);
    }

    //내가 작성한 커뮤니티 글
    @GetMapping(value = "/community")
    public ResponseEntity<?> getCommunity (@AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "size") int size) {
        int pageTemp = page -1;
        return mypageService.getCommunity(memberDetails.getMember(), pageTemp, size);
    }

    //마이티켓 기록하기
    @PostMapping(value = "/ticket/write")
    public ResponseEntity<?> writeMyticket (@AuthenticationPrincipal MemberDetailsImpl memberDetails,
                                            @ModelAttribute MypageRequestDto mypageRequestDto) throws IOException {
        return mypageService.writeMyticket(memberDetails.getMember(), mypageRequestDto);
    }
}
