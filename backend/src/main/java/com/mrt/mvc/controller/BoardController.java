package com.mrt.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;
import com.mrt.mvc.model.dto.SearchCondition;
import com.mrt.mvc.model.service.BoardService;

@RestController
@RequestMapping("/maratalk")
public class BoardController {
	
	// 의존성 주입
	private final BoardService service;
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	/** 게시글 전체 조회 및 검색 조회 */
	@GetMapping("/board")
	public ResponseEntity<List<Board>> list(@ModelAttribute SearchCondition condition) {
		System.out.println("전체 게시글을 조회합니다.");
		System.out.println("검색 정보: " + condition);
		List<Board> list = service.getBoardList(condition);
		if (list == null && list.size() == 0) {
			System.out.println("리스트가 비어있습니다.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/** 게시글 상세 조회*/
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> detail(@PathVariable("no") int no) {
		Board board = service.getBoardByNo(no);
		System.out.println("넘어온 게시글: " + board.toString());
		if (board != null) {
			System.out.println(no + "번 게시글을 조회합니다.");
			return new ResponseEntity<>(board, HttpStatus.OK);
		}
		System.out.println("조회한 게시글이 없습니다.");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/** 게시글 등록
	 * @throws IOException 
	 * @throws IllegalStateException */
	@PostMapping("/board")
	public ResponseEntity<String> write(@RequestBody Board board, @RequestParam("attach") MultipartFile attach) throws IllegalStateException, IOException {
		// 사용자가 업로드한 파일 이름
		String oriName = attach.getOriginalFilename();
		if (oriName.length() > 0) {  // 사용자가 파일을 선택한 경우
			// 서버의 특정 디렉토리에 저장
			String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
			File dir = new File("c:/SSAFY/uploads" + subDir);
			dir.mkdirs();
			String systemName = UUID.randomUUID().toString() + oriName;
			attach.transferTo(new File(dir, systemName));  // 메모리의 파일 정보를 특정 위치에 저장
			
			// 데이터베이스에 저장하기 위한 준비
			BoardFile boardFile = new BoardFile();
			boardFile.setFilePath(subDir);
			boardFile.setOriName(oriName);
			boardFile.setSystemName(systemName);
			System.out.println(boardFile.toString());  // 확인
			board.setBoardFile(boardFile);
			System.out.println(board.toString());
		}
		if (service.writeBoard(board))
			return new ResponseEntity<>("게시글이 작성되었습니다.", HttpStatus.OK);
		return new ResponseEntity<>("게시글이 작성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/** 게시글 수정 
	 * @return */
	@PutMapping("/board/{no}")
	public ResponseEntity<String> updateBoard(@PathVariable("no") int no, @RequestBody Board board) {
		System.out.println(no + "번 게시글을 수정합니다.");
		board.setBoardNo(no);
		if (service.modify(board)) {
			System.out.println(no + "번 게시글이 수정되었습니다.");
			return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
		}
		System.out.println("수정에 실패했습니다.");
		return new ResponseEntity<>("수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/** 게시글 삭제 */
	@DeleteMapping("/board/{no}")
	public ResponseEntity<String> delete(@PathVariable int no) {
		if (service.removeBoard(no))
			return ResponseEntity.status(HttpStatus.OK).body("Board deleted successfully");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Board");
	}
}
