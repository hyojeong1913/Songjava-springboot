package kr.co.songjava.mvc.service;

import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 서비스
 */
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /**
     * 게시판 목록 조회
     *
     * @return
     */
    public List<Board> getList() {
        return boardRepository.getList();
    }

    /**
     * 게시판 단건 게시글 조회
     *
     * @param boardSeq
     * @return
     */
    public Board get(int boardSeq) {
        return boardRepository.get(boardSeq);
    }

    /**
     * 게시글 저장
     *
     * @param board
     */
    public void save(Board board) {
        boardRepository.save(board);
    }

    /**
     * 게시글 수정
     * @param board
     */
    public void update(Board board) {
        boardRepository.update(board);
    }

    /**
     * 게시글 삭제
     *
     * @param boardSeq
     */
    public void delete(int boardSeq) {
        boardRepository.delete(boardSeq);
    }
}
