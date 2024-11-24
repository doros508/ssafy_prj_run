package com.mrt.mvc.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		System.out.println("상세 조회 할 게시글: " + board.toString());
		return board;
	}

	/** 게시글 등록 */
	@Transactional
	@Override
	public boolean writeBoard(Board board, List<MultipartFile> files) {
	    System.out.println("service: writeBoard() 실행");
	    int result = dao.insertBoard(board);

	    int boardNo = board.getBoardNo();
	    System.out.println("게시글 번호: " + boardNo);
	    System.out.println("service: 넘어온 " + board.toString());

	    // 파일 처리 및 저장 (파일이 있을 때만 처리)
	    if (files != null && !files.isEmpty()) { // 파일이 들어온 경우
	        board.setBoardFileList(new ArrayList<>());
	        
	        System.out.println("service: " + files.size() + "개의 파일이 첨부되었습니다.");
	        String datePath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
	        File dir = new File("C:/maratalk/uploads/board" + datePath); // 디렉토리 주소를 나타내는 File 객체를 생성
	        dir.mkdirs(); // 날짜에 해당하는 디렉토리가 없는 경우에 생성한다.
	        
	        for (MultipartFile file : files) {
	            String fileNo = UUID.randomUUID().toString(); // 파일의 고유 번호
	            String oriName = file.getOriginalFilename(); // 파일의 원본 이름
	            String fileSystemName = fileNo + "_" + oriName; // UUID_Dog.png
	            
	            File fileLocation = new File(dir, fileSystemName);
	            try {
	                file.transferTo(fileLocation);
	                
	                // DB에 저장할 BoardFile 객체 생성
	                BoardFile boardFile = new BoardFile();
	                boardFile.setFileNo(fileNo);
	                boardFile.setOriName(oriName);
	                boardFile.setPath(datePath);
	                boardFile.setSystemName(fileSystemName);
	                boardFile.setBoardNo(boardNo);
	                
	                board.getBoardFileList().add(boardFile);
	                System.out.println("보드에 들어간 파일은 " + board.getBoardFileList().size() + "개 입니다.");
	                
	                dao.insertBoardFile(boardFile);
	            } catch (Exception e) {
	                e.printStackTrace();
	                System.out.println("service > writeBoard() > catch문: 파일 저장 실패");
	            }
	        }
	        System.out.println("등록된 게시글은 " + board.toString());
	    } else {
	        System.out.println("파일이 첨부되지 않았습니다.");
	    }

	    return result == 1;
	}


	/** 게시글 수정 */
	@Transactional
	@Override
	public boolean modify(Board board) {
		System.out.println("service: 넘어온 " + board.toString());

		// 기존 게시글 저장
		Board tmp = dao.selectOne(board.getBoardNo());
		System.out.println("service: 임시 " + tmp.toString());

		// 만약 수정한 게시글에 내용이 없는 경우
		if (board.getBoardContent() == null)
			tmp.setBoardTitle(board.getBoardTitle()); // 제목만 변경
		// 수정한 게시글에 제목이 없는 경우
		if (board.getBoardTitle() == null)
			tmp.setBoardContent(board.getBoardContent()); // 내용만 변경

		if (board.getBoardContent() != null && board.getBoardTitle() != null) {
			tmp.setBoardTitle(board.getBoardTitle());
			tmp.setBoardContent(board.getBoardContent());
		}

		System.out.println("service: 수정된 " + tmp.toString());
		return dao.updateBoard(tmp) == 1;
	}

	/** 게시글 삭제 */
	@Transactional
	@Override
	public boolean removeBoard(int no) {
		return dao.deleteBoard(no) == 1;
	}

}
