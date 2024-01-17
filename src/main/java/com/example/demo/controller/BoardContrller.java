package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardContrller {

	@Autowired
	BoardService service;

	// 메인화면
	@GetMapping("/main")
	public void main() {

	}

	// 목록화면
	@GetMapping("/list")
	public void list(Model model) {
		List<BoardDTO> list = service.getList(); // 서비스로 게시물 목록 가져오기
		model.addAttribute("list", list); // 화면에 게시물 리스트 전달
	}

	// 등록화면
	@GetMapping("/register")
	public void register() {

	}

	// 등록처리
	@PostMapping("/register") // 화면에서 전달받은 게시물 전달자 객체
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {

		// 게시물 등록하고 새로운 게시물 번호 반환
		int no = service.register(dto);

		// 목록화면에 새로운 게시물 번호 전달 flash
		redirectAttributes.addFlashAttribute("msg", no);

		// 목록화면으로 이동. HTML 경로 아님. url주소를 작성할 것.
		return "redirect:/board/list";
	}

	// 상세화면
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {

		BoardDTO dto = service.read(no);

		model.addAttribute("dto", dto);
		// 상세화면에 게시물 정보 출력
	}

	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {

		BoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {

		// 게시물 수정
		service.modify(dto);

		// 리다이렉트 주소에 파라미터 추가(?no=1) 화면이 아니라 링크에 파라미터 추가하는 기능 addattribute
		redirectAttributes.addAttribute("no", dto.getNo());

		// 상세화면으로 리다이렉트
		return "redirect:/board/read";
	}
}
