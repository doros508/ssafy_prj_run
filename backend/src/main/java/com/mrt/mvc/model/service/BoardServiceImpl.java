package com.mrt.mvc.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dao.BoardDao;
import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	// 의존성 주입
	private final BoardDao dao;
	private final ResourceLoader resourceLoader;
	public BoardServiceImpl(BoardDao dao, ResourceLoader resourceLoader) {
		this.dao = dao;
		this.resourceLoader = resourceLoader;
	}
	
	/** 전체 게시글 검색 조회 */
	@Override
	public List<Board> getBoardList(SearchCondition condition) {
		System.out.println("BoardService) getBoardList 메서드 호출");
		List<Board> list = dao.selectAll(condition);
		System.out.println("불러온 게시글 목록: " + list.toString());
		return list;
	}

	/** 게시글 상세 조회 */
	@Override
	public Board getBoardByNo(int no) {
		dao.updateViewCnt(no);
		Board board = dao.selectOne(no);
		System.out.println(board.toString());
		return board;
	}

	/** 게시글 작성 */
	@Transactional
	@Override
	public boolean writeBoard(Board board) {
		return dao.insertBoard(board) == 1;
	}
	@Transactional
	@Override
	public boolean writeBoard(Board board, MultipartFile file) {
		if (file != null && file.getSize() > 0) {
	        try {
	        	String fileName = file.getOriginalFilename(); // 실제 파일 이름
	        	String fileNo = UUID.randomUUID().toString(); // 고유한 이름
	        	
	        	// 확장자까지 저장하기
	        	String fileExtension = "";
	        	if (fileName != null && fileName.contains(".")) { // . 의 위치를 찾아서
	        		fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1); // 그 다음부터의 문자열을 저장
	        	}
	        	System.out.println("원본 파일 이름: " + fileName);
	        	System.out.println("고유 파일 이름: " + fileNo);
	        	System.out.println("파일 확장자: " + fileExtension);
	        	
	        	if (board.getBoardFile() == null) {
	        		board.setBoardFile(new BoardFile());
	        	}
	        		        	
	        	board.getBoardFile().setFileNo(fileNo);
	        	board.getBoardFile().setOriName(fileName);
	        	
	        	System.out.println("BoardFile 객체: " + board.getBoardFile().toString()); // 확인
	        	
	        	Resource resource = resourceLoader.getResource("classpath:/static/img");
	        	file.transferTo(new File(resource.getFile(), fileNo));
//	        	File directory = resource.getFile();
//	        	if (!directory.exists()) {
//	        		directory.mkdirs(); // 디렉토리가 없으면 생성
//	            }
//	        	File destinationFile = new File(directory, fileNo + "." + fileExtension);
//				file.transferTo(new File(resource.getFile(), fileNo));
				
				dao.insertBoard(board);
				dao.insertBoardFile(board.getBoardFile());
				
				return true;
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/** 게시글 수정 */
	@Transactional
	@Override
	public boolean modify(Board board) {
		// 기존 게시글 저장
		Board tmp = dao.selectOne(board.getBoardNo());
		tmp.setBoardTitle(board.getBoardTitle());
		
		// 만약 수정한 게시글에 내용이 없는 경우
		if (board.getBoardContent() == null)
			tmp.setBoardTitle(board.getBoardTitle()); // 제목만 변경
		// 수정한 게시글에 제목이 없는 경우
		if (board.getBoardTitle() == null)
			tmp.setBoardContent(tmp.getBoardContent()); // 내용만 변경
		return dao.updateBoard(tmp) == 1;
	}

	/** 게시글 삭제 */
	@Transactional
	@Override
	public boolean removeBoard(int no) {
		return dao.deleteBoard(no) == 1;
	}

}
