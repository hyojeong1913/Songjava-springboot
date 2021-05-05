package kr.co.songjava.mvc.service;

import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import kr.co.songjava.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param pageRequestParameter
     * @return
     */
    public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
        return boardRepository.getList(pageRequestParameter);
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
     * 단순 반복문을 이용한 게시글 등록 처리
     *
     * @param list
     */
    public void saveList1(List<BoardParameter> list) {
        for (BoardParameter parameter : list) {
            boardRepository.save(parameter);
        }
    }

    /**
     * 100개씩 배열에 담아서 일괄 게시글 등록 처리
     *
     * @param boardList
     */
    public void saveList2(List<BoardParameter> boardList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("boardList", boardList);
        boardRepository.saveList(paramMap);
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
