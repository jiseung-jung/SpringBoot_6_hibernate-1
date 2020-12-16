package com.jiseung.sb6.board.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long>{
	
	//상속받은 메서드
	//long count() : 데이터 총 갯수 select count() from notice
	//select = find where = By
	
	//select * from notice where num > 0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long n);
	
	//select * from notice where num between ? and ?
	public List<NoticeVO> findByNumBetween(long n1, long n2);
	
	//select * from notice where title like '%' ? '%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String search);
	
	
	

}
