package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	public void 게시물등록() {

		BoardDTO dto = BoardDTO.builder().title("서비스게시물테스트").content("내용입니다").writer("또치").build();

		int no = service.register(dto);
		System.out.println("새로운 게시물 번호: " + no);
	}

	@Test
	public void 게시물목록조회() {

		List<BoardDTO> list = service.getList();

		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO);
		}
	}

	@Test
	public void 게시물단건조회() {

		BoardDTO dto = service.read(5);
		System.out.println(dto);
	}

	@Test
	public void 게시물수정() {

		BoardDTO dto = service.read(5);
		dto.setContent("내용수정확인");
		service.modify(dto);
	}

	@Test
	public void 게시물삭제() {
		int result = service.remove(3);
		System.out.println(result);
	}

}
