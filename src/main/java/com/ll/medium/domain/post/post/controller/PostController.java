package com.ll.medium.domain.post.post.controller;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.post.post.service.PostService;
import com.ll.medium.exception.NotFoundException;
import com.ll.medium.global.rq.Rq.Rq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "PostController", description = "글 CRUD 컨트롤러")
public class PostController {
    private final PostService postService;
    private final Rq rq;

    @GetMapping("/{id}")
    public String showDetail(@PathVariable long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 글을 찾을 수 없습니다."));

        // 유료 글 확인 및 처리 로직 추가
        if (post.isPaid()) {
            // 현재 사용자의 유료 멤버십 상태 확인 필요
            // 예: if (!currentUser.isPaidMember()) {
            //         post.setContent("이 글은 유료멤버십전용 입니다.");
            //     }
        }

        model.addAttribute("post", post);
        return "domain/post/post/detail";
    }




    @GetMapping("/list")
    @Operation(summary = "글 목록")
    public String showList(
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sorts));

        Page<Post> postPage = postService.search(kw, pageable);
        rq.setAttribute("postPage", postPage);
        rq.setAttribute("page", page);

        return "domain/post/post/list";
    }
}