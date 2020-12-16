package com.jiseung.sb6.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class QnaRepositoryTest {
	
	@Autowired
	private QnaRepository qnaRepository;
	

	@Test
	void test() {
		//save(insert) test
		//ref : 자기 자신의 글번호 (update)
		//step : 0
		//depth : 0 
		
	//	this.saveTest();
		
	//	ReplyTest();
		
	//	saveTest2();
		
		listTest();
	}
	
	
	
	private void listTest() {
		//일정한 갯수, 정렬방식
		//select * from qna order by num desc limit 0, 10;
		//Pageable
		//PageRequest.of(페이지 번호, 페이지당 보여줄 갯수)
		//페이지 번호는 0부터 시작 (curPage)
		
		Pageable pageable = PageRequest.of(1, 10);
		Page<QnaVO> page = qnaRepository.findAll(pageable);
		
		System.out.println("Size : "+page.getSize()); //perPage의 갯수(페이지당 보여줄 갯수)
		System.out.println(" : "+page.getContent().size());//VO의 갯수
		System.out.println("Totla Element : "+page.getTotalElements());
		//테이블의 모든 row 갯수, count()와 같음
		System.out.println("Total Pages : "+page.getTotalPages()); //전체 페이지의 갯수
		System.out.println("Next : "+page.hasNext());//다음 페이지가 있으면 true, 없으면 false
		System.out.println("Previous : "+page.hasPrevious());//이전 페이지가 있으면 true, 없으면 false
		System.out.println(""+page.getNumber());//페이지 번호 (curPage)
		System.out.println(""+page.getNumberOfElements());// content size 같음, VO의 갯수
		System.out.println("Content : "+page.hasContent());//List<VO> 있으면 true, 없으면 false
		System.out.println("First : "+page.isFirst());//현재 페이지가 처음인지 여부
		System.out.println("Last : "+page.isLast());//현재 페이지가 마지막인지
		
		List<QnaVO> ar = page.getContent();
		
		for(QnaVO qnaVO : ar) {
			System.out.println(qnaVO.getNum());
			System.out.println(qnaVO.getTitle());
			System.out.println("---------------------------------------");
		}
	}
	
	
	
	private void saveTest2() {
		List<QnaVO> ar = new ArrayList<>();
		for(int i=0;i<100;i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setTitle("title"+i);
			qnaVO.setWriter("writer"+i);
			qnaVO.setContents("contents"+i);
			ar.add(qnaVO);
		}
		
		qnaRepository.saveAll(ar);
		System.out.println("finish");
	}
	
	private void saveTest() {
		QnaVO qnaVO = new QnaVO();
		
		
		
		qnaVO.setTitle("qt1");
		qnaVO.setWriter("qw1");
		qnaVO.setContents("qc1");
		
		QnaFileVO qnaFileVO = new QnaFileVO();
		qnaFileVO.setFileName("fname1");
		qnaFileVO.setOriName("oName1");
		qnaFileVO.setQnaVO(qnaVO);
		
		List<QnaFileVO> ar = new ArrayList<>();
		ar.add(qnaFileVO);
		
		qnaVO.setQnaFileVOs(ar);
		
		qnaVO = qnaRepository.save(qnaVO); //insert
		
		qnaVO.setRef(qnaVO.getNum());
		
		qnaRepository.save(qnaVO); //ref update
		
		
	}
	
	
	private void ReplyTest() {
		//부모글번호, 답글제목, 답글내용
		QnaVO qnaVO = new QnaVO();
		qnaVO.setNum(1L);
		qnaVO.setTitle("reply Title");
		qnaVO.setContents("reply Contents");
		qnaVO.setWriter("reply Writer");
		
		
		//0. 부모 글번호로 부모글을 Select
		QnaVO parent = qnaRepository.findById(qnaVO.getNum()).get();
		System.out.println(parent.getTitle());
		System.out.println(parent.getRef());
		System.out.println(parent.getStep());
		System.out.println(parent.getDepth());
		
		//1. 부모의 ref와 같고 step이 부모의 step보다 큰 것들은 step+1 적용(Select, 값을 변경 후 save)
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(parent.getRef(), parent.getStep());
		
		for(QnaVO qVo : ar) {
			System.out.println(qVo.getTitle());
			System.out.println(qVo.getRef());
			System.out.println(qVo.getStep());
			qVo.setStep(qVo.getStep()+1);
		}
		
		qnaRepository.saveAll(ar);
		
		//2. 답글을 Insert
		//	ref는 부모의 ref, step은 부모의 step+1, depth는 부모의 depth+1
		qnaVO.setNum(0);
		qnaVO.setRef(parent.getRef());
		qnaVO.setStep(parent.getStep()+1);
		qnaVO.setDepth(parent.getDepth()+1);
		
		qnaRepository.save(qnaVO);
		
		
	}

}
