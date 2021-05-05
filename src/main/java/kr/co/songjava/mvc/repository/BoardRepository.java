package kr.co.songjava.mvc.repository;

import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 게시판 Repository
 */
@Repository
public interface BoardRepository {

    // 게시판 목록 조회
    List<Board> getList(BoardSearchParameter parameter);

    // 게시판 단건 게시글 조회
    Board get(int boardSeq);

    // 게시글 저장
    void save(BoardParameter board);
    void saveList(Map<String, Object> paramMap);

    // 게시글 수정
    void update(BoardParameter board);

    // 게시글 삭제
    void delete(int boardSeq);
}
