package com.jiseung.sb6.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeRepositoryTest {
	
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private NoticeService noticeService;
	
	@Test
	void Test() throws Exception{
		this.selectTest();
	}
	
	
	private void selectTest() {
		//글번호로 Notice, NoticeFiles 조회
		//글제목, 작성자, filename, oriname
		
		Optional<NoticeVO> opt = noticeRepository.findById(1L);
		NoticeVO noticeVO = opt.get();
		
		System.out.println(noticeVO.getTitle());
		System.out.println(noticeVO.getContents());
		
		for(NoticeFileVO noticeFileVO : noticeVO.getNoticeFileVOs()) {
			System.out.println(noticeFileVO.getFileName());
			System.out.println(noticeFileVO.getOriName());
		}
		
		System.out.println("---------------finish---------------");
	}
	
	
	private void saveTest1() {
		
		//insert notice, noticeFile
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("title1");
		noticeVO.setContents("contents1");
		noticeVO.setWriter("writer1");
		
		List<NoticeFileVO> ar = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName1.jpg");
		noticeFileVO.setOriName("oriName1.jpg");
		noticeFileVO.setNoticeVO(noticeVO);
		
		ar.add(noticeFileVO);
		
		NoticeFileVO noticeFileVO2 = new NoticeFileVO();
		noticeFileVO2.setFileName("fileName2.jpg");
		noticeFileVO2.setOriName("oriName2.jpg");
		noticeFileVO2.setNoticeVO(noticeVO);
		
		ar.add(noticeFileVO2);
		
		noticeVO.setNoticeFileVOs(ar);
		
		noticeRepository.save(noticeVO);
		
		System.out.println("finish");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//---------------------------------------------------------------------------------
	
	
	//@Test
	void test3() throws Exception{
		List<NoticeVO> ar = noticeRepository.findByTitleContainingOrderByNumDesc("9");
		for(NoticeVO noticeVO:ar) {
			System.out.println(noticeVO.getTitle());
		}
	}
	
	//@Test
	void test2() throws Exception{
		List<NoticeVO> ar = noticeRepository.findByNumBetween(100, 105);
				
		for(NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getNum());
		}
	}
	
	//@Test
	void test() throws Exception{
		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0L);
		
		System.out.println(ar.get(0).getNum());
		System.out.println(ar.get(1).getNum());
		System.out.println(ar.get(2).getNum());
	}
	
	//@Test
	void updateTest() {
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setTitle("Update Title");
		noticeVO.setContents("Update Contents");
		noticeVO.setWriter("Update Writer");
		noticeVO.setNum(1);
		
		noticeRepository.save(noticeVO);
	}
	
	
	
	
	//@Test
	void saveTest() throws Exception{
		
		for(int i=0;i<150;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setTitle("test title"+i);
			noticeVO.setContents("test contents"+i);
			noticeVO.setWriter("test writer"+i);
			noticeVO = noticeRepository.save(noticeVO);
		if(i%10 == 0) {
			Thread.sleep(500);
		}		
	}
		System.out.println("finish");
	}
	
	
	//@Test
	void SelectOneTest() {
		Optional<NoticeVO> vo = noticeRepository.findById(1L);
		NoticeVO noticeVO = vo.get();
		System.out.println(noticeVO.getNum());
		System.out.println(noticeVO.getTitle());
		
	}

	//@Test
	void counttest() {
		//long count = noticeRepository.count();
		List<NoticeVO> ar = noticeRepository.findAll();
		for(NoticeVO noticeVO:ar) {
			System.out.println(noticeVO.getTitle());
			System.out.println(noticeVO.getNum());
		}
		
		//System.out.println("Count: "+count);
	}

}
