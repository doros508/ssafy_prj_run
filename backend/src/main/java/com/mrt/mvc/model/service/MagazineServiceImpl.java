package com.mrt.mvc.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dao.MagazineDao;
import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.MagazineFile;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class MagazineServiceImpl implements MagazineService {

	// 의존성 주입
	private final MagazineDao dao;
	public MagazineServiceImpl(MagazineDao dao) {
		this.dao = dao;
	}
	
	// 매거진 전체 조회 및 검색 조회
	@Override
	public List<Magazine> getMagazineList(SearchCondition condition) {
		return dao.selectAll(condition);
	}

	// 매거진 상세 조회
	@Override
	public Magazine getMagazineByNo(int no) {
		dao.updateViewCnt(no);
		return dao.selectOne(no);
	}

	@Transactional
	@Override
	public boolean writeMagazine(Magazine magazine, List<MultipartFile> files) {
		System.out.println("service: writeBoard() 실행");
		int result = dao.insertMagazine(magazine);

		int boardNo = magazine.getMagazineNo();
		System.out.println("게시글 번호: " + boardNo);
		System.out.println("service: 넘어온 " + magazine.toString());

		// 파일 처리 및 저장
		if (files.size() > 0) { // 파일이 들어온 경우
			magazine.setMagazineFileList(new ArrayList<>());
			
			System.out.println("service: " + files.size() +"개의 파일이 첨부되었습니다.");
			String datePath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
			File dir = new File("C:/maratalk/uploads/magazine" + datePath); // 디렉토리 주소를 나타내는 File 객체를 생성
			dir.mkdirs(); // 날짜에 해당하는 디렉토리가 없는 경우에 생성한다.
			
			for (MultipartFile file : files) {
				String fileNo = UUID.randomUUID().toString(); // 파일의 고유 번호
				String oriName = file.getOriginalFilename(); // 파일의 원본 이름
				String fileSystemName = fileNo + "_" + oriName; // UUID_Dog.png
				
				File fileLocation = new File(dir, fileSystemName);
				try {
					file.transferTo(fileLocation);
					
					// DB에 저장할 MagazineFile 객체 생성
					MagazineFile magazineFile = new MagazineFile();
					magazineFile.setFileNo(fileNo);
					magazineFile.setOriName(oriName);
					magazineFile.setPath(datePath);
					magazineFile.setSystemName(fileSystemName);
					magazineFile.setMagazineNo(boardNo);
					
					magazine.getMagazineFileList().add(magazineFile);
					System.out.println("매거진에 들어간 파일은 " + magazine.getMagazineFileList().size() + "개 입니다.");
					
					dao.insertMagazineFile(magazineFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("service > writeMagazine() > catch문: 파일 저장 실패");
				}
			}
			System.out.println("등록된 매거진은 " + magazine.toString());
		}
		return result == 1;
	}
	
}
