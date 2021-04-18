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
    Board get(@PathVariable int boardSeq) {
        return boardService.get(boardSeq);
    }

    /**
     * 게시글 등록/수정 처리
     *
     * @param board
     */
    @GetMapping("/save")
    void save(Board board) {
        boardService.save(board);
    }

    /**
     * 게시글 삭제
     *
     * @param boardSeq
     */
    @GetMapping("/delete/{boardSeq}")
    void delete(@PathVariable int boardSeq) {
        boardService.delete(boardSeq);
    }
}
