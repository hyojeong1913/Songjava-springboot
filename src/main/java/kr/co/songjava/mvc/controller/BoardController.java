package kr.co.songjava.mvc.controller;

import io.swagger.annotations.*;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.framework.web.bind.annotation.RequestConfig;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import kr.co.songjava.mvc.service.BoardService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시판 Controller
 */
@Controller
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록 조회
     *
     * @param parameter
     * @param pageRequest
     * @return
     */
    @GetMapping
    @ApiOperation(value = "게시판 목록 조회", notes = "게시판 목록 정보를 조회할 수 있습니다.")
    public BaseResponse<List<Board>> getList(
            @ApiParam BoardSearchParameter parameter,
            @ApiParam MySQLPageRequest pageRequest
    ) {
        logger.info("pageRequest : {}", pageRequest);

        PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);

        return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
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
    public BaseResponse<Board> get(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);

        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시글" });
        }
        return new BaseResponse<Board>(board);
    }

    /**
     * 등록/수정 화면으로 이동
     *
     * @param parameter
     * @param model
     */
    @GetMapping("/form")
    @RequestConfig(loginCheck = false)
    public void form(BoardParameter parameter, Model model) {
        // 수정 화면
        if (parameter.getBoardSeq() > 0) {
            Board board = boardService.get(parameter.getBoardSeq());
            model.addAttribute("board", board);
        }

        model.addAttribute("parameter", parameter);
    }

    /**
     * 게시글 등록/수정 처리
     *
     * @param board
     * @return
     */
    @PostMapping("/save")
    @RequestConfig(loginCheck = false)
    @ResponseBody
    @ApiOperation(value = "게시글 등록/수정 처리", notes = "신규 게시글 저장 및 기존 게시글 수정이 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", example = "title1"),
            @ApiImplicitParam(name = "boardType", value = "게시글 유형", example = "NOTICE"),
            @ApiImplicitParam(name = "contents", value = "게시글 내용", example = "contents1")
    })
    public BaseResponse<Integer> save(BoardParameter board) {

        // 게시글 제목 필수 체크
        if (StringUtils.isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "게시글 제목" });
        }

        // 게시글 내용 필수 체크
        if (StringUtils.isEmpty(board.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "게시글 내용" });
        }

        // 게시글 등록/수정 처리
        boardService.save(board);

        // 해당 게시글의 인덱스(PK) 리턴
        return new BaseResponse<Integer>(board.getBoardSeq());
    }

    /**
     * 대용량 게시글 등록 처리1
     *
     * @return
     */
    @ApiOperation(value = "대용량 등록 처리1", notes = "대용량 등록 처리1")
    @PostMapping("/saveList1")
    @RequestConfig(loginCheck = true)
    public BaseResponse<Boolean> saveList1() {
        int count = 0;

        List<BoardParameter> list = new ArrayList<BoardParameter>();

        // 테스트를 위한 랜덤 10,000 건의 데이터를 생성
        while (true) {
            count++;

            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);

            list.add(new BoardParameter(title, contents));

            if (count >= 10000) {
                break;
            }
        }

        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long end = System.currentTimeMillis();

        logger.info("실행 시간 : {}", (end - start) / 1000.0);

        return new BaseResponse<Boolean>(true);
    }

    /**
     * 대용량 게시글 등록 처리2
     *
     * @return
     */
    @PostMapping("/saveList2")
    @RequestConfig(loginCheck = true)
    @ApiOperation(value = "대용량 등록 처리2", notes = "대용량 등록 처리2")
    public BaseResponse<Boolean> saveList2() {
        int count = 0;

        List<BoardParameter> list = new ArrayList<BoardParameter>();

        // 테스트를 위한 랜덤 10,000 건의 데이터를 생성
        while (true) {
            count++;

            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);

            list.add(new BoardParameter(title, contents));

            if (count >= 10000) {
                break;
            }
        }

        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long end = System.currentTimeMillis();

        logger.info("실행 시간 : {}", (end - start) / 1000.0);

        return new BaseResponse<Boolean>(true);
    }

    /**
     * 게시글 삭제
     *
     * @param boardSeq
     */
    @DeleteMapping("/{boardSeq}")
    @RequestConfig(loginCheck = true)
    @ApiOperation(value = "게시글 삭제 처리", notes = "게시글 번호에 해당하는 상세 정보를 삭제할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1")
    })
    public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
        // 게시글 조회
        Board board = boardService.get(boardSeq);

        // 조회된 게시글이 없는 경우
        if (board == null) {
            return new BaseResponse<Boolean>(false); // 삭제해야 하는데 이미 존재하지 않으므로 false 리턴
        }

        // 게시글 삭제
        boardService.delete(boardSeq);

        // 게시글 삭제했으므로 마지막으로 true 리턴
        return new BaseResponse<Boolean>(true);
    }
}
