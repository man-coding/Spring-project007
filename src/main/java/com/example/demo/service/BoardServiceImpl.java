package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service // 서비스 클래스로 지정. -> 역할 부여
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;

	@Override
	public int register(BoardDTO dto) {

		System.out.println(dto);

		Board entity = dtoToEntity(dto);

		repository.save(entity);

		int newNo = entity.getNo();

		System.out.println(entity);

		return newNo;

	}

	@Override
	public List<BoardDTO> getList() {

		// 데이터베이스에서 게시물 목록 가져오기
		List<Board> result = repository.findAll();

		// 리스트 생성
		List<BoardDTO> list = new ArrayList<>();

		// 리스트에서 스트림 생성
		list = result.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());

		return list; // dto 리스트 반환
	}

}
