package com.mrt.mvc.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dao.UserDao;
import com.mrt.mvc.model.dto.User;
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public void registUser(User user, MultipartFile userPhoto) throws IOException {
		String filePath = saveFile(userPhoto); //파일 저장 및 저장경로 가져오기.
		
		user.setUserPhoto(filePath); //객체에 저장된 파일 경로 설정.
		
		userDao.insertUser(user);
	}
	
	/*
	 * 	@PostMapping("/write")
	public String write(@RequestParam("attach") MultipartFile attach, Board board) throws Exception {
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
			board.setBoardFile(boardFile);
		}
		boardService.writeBoard(board);
		return "redirect:/board/list";
	}
}


	 * 
	 * */
	
	

	@Override
	public User login(String id, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("id", id);
		info.put("password", password);
		User tmp = userDao.selectOne(info);
		return tmp;
		
	}

	@Override
	public void deleteAccount(int userNo) {
		userDao.deleteUser(userNo);
	}

	@Override
	public String saveFile(MultipartFile file) throws IOException {
		//사진 파일 저장 및 경로 반환. 
		String fileName = file.getOriginalFilename();
		String filePath = "/static/img" + fileName;
		
		//지정된 경로에 저장하기. 
		File dest = new File(filePath);
		file.transferTo(dest); //파일 저장하기. 
		
		return filePath;
	}
	
	
	
}
