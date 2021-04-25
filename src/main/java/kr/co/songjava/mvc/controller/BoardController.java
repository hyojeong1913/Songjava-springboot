package kr.co.songjava.mvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 Controller
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록 조회
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "게시판 목록 조회", notes = "게시판 목록 정보를 조회할 수 있습니다.")
    public List<Board> getList() {
        return boardService.getList();
    }

    /**
     * 게시글 상세 조회
     *
     * @param boardSeq
     * @return
     */
    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "게시글 상세 조회", notes = "게시글 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1")
    })
    public Board get(@PathVariable int boardSeq) {
        return boardService.get(boardSeq);
    }

    /**
     * 게시글 등록/수정 처리
     *
     * @param board
     */
    @PostMapping
    @ApiOperation(value = "게시글 등록/수정 처리", notes = "신규 게시글 저장 및 기존 게시글 수정이 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", example = "title1"),
            @ApiImplicitParam(name = "contents", value = "게시글 내용", example = "contents1")
    })
    public int save(BoardParameter board) {
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
    @DeleteMapping("/{boardSeq}")
    @ApiOperation(value = "게시글 삭제 처리", notes = "게시글 번호에 해당하는 상세 정보를 삭제할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1")
    })
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
