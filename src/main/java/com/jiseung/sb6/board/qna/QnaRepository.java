package com.jiseung.sb6.board.qna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {
	
	//답글 작성
	//부모의 ref와 같고 step이 부모의 step 보다 큰 것들 select
	List<QnaVO> findByRefAndStepGreaterThan(long ref, long step);

}
