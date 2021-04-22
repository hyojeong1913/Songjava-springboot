package kr.co.songjava.mvc.controller;

import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 게시판 Controller
 */
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록 조회
     *
     * @return
     */
    @GetMapping
    public List<Board> getList() {
        return boardService.getList();
    }

    /**
     * 게시판 단건 게시글 조회
     *
     * @param boardSeq
     * @return
     */
    @GetMapping("/{boardSeq}")
    public Board get(@PathVariable int boardSeq) {
        return boardService.get(boardSeq);
    }

    /**
     * 게시글 등록/수정 처리
     *
     * @param board
     */
    @GetMapping("/save")
    public int save(Board board) {
        // 게시글 등록/수정 처리
        boardService.save(board);

        // 해당 게시글의 인덱스(PK) 리턴
        return board.getBoardSeq();
    }

    /**
     * 게시글 삭제
     *
     * @param boardSeq
     */
    @GetMapping("/delete/{boardSeq}")
    public boolean delete(@PathVariable int boardSeq) {
        // 게시글 조회
        Board board = boardService.get(boardSeq);

        // 조회된 게시글이 없는 경우
        if (board == null) {
            return false; // 삭제해야 하는데 이미 존재하지 않으므로 false 리턴
        }

        // 게시글 삭제
        boardService.delete(boardSeq);

        // 게시글 삭제했으므로 마지막으로 true 리턴
        return true;
    }
}
