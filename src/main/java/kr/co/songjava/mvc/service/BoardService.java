package kr.co.songjava.mvc.service;

import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
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
     * 게시글 저장/수정
     *
     * @param param
     */
    public void save(BoardParameter param) {
        // 게시글 조회
        Board board = boardRepository.get(param.getBoardSeq());

        // 조회된 게시글이 없는 경우 추가
        if (board == null) {
            boardRepository.save(param);
        } else { // 조회된 게시글이 있는 경우 수정
            boardRepository.update(param);
        }
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
