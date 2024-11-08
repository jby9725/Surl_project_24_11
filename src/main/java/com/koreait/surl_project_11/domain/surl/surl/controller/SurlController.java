package com.koreait.surl_project_11.domain.surl.surl.controller;

import com.koreait.surl_project_11.domain.surl.surl.entity.Surl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurlController {

    private List<Surl> surls = new ArrayList<>();
    private long surlsLastId;

    @GetMapping("/add")
    @ResponseBody
    public Surl add(String body, String url) {
        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();

        surls.add(surl);

        return surl;
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public String add(
            @PathVariable String body,
            HttpServletRequest req
    ) {
        String requestURI = req.getRequestURI();

        if (req.getQueryString() != null) {
            requestURI += "?" + req.getQueryString();
        }

        String[] urlBits = requestURI.split("/", 4);

        requestURI = urlBits[3];

        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(requestURI)
                .build();

        surls.add(surl);

        return requestURI;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll() {

        return surls;
    }

    @GetMapping("/g/{id}")
    public String go(
            @PathVariable long id
    ) {
        Surl surl = surls.stream()
                .filter(_surl -> _surl.getId() == id)
                .findFirst()
                .orElse(null);

        if (surl == null) throw new RuntimeException("%d번 데이터를 찾을 수 없어요.".formatted(id));

        surl.increaseCount();

        // 경로 다시 설정
        return "redirect:" + surl.getUrl();
    }

}
