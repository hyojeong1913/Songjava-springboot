package kr.co.songjava.mvc.controller;

import io.swagger.annotations.*;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.framework.web.bind.annotation.RequestConfig;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import kr.co.songjava.mvc.domain.MenuType;
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
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 목록 조회
     *
     * @param menuType
     * @param parameter
     * @param pageRequest
     * @param model
     * @return
     */
    @GetMapping("/{menuType}")
    public String list(@PathVariable MenuType menuType, BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model) {
        logger.info("menuType : {}", menuType);
        logger.info("pageRequest : {}", pageRequest);

        parameter.setBoardType(menuType.boardType());

        PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);

        List<Board> boardList = boardService.getList(pageRequestParameter);
        model.addAttribute("boardList", boardList);
        model.addAttribute("menuType", menuType);

        return "/board/list";
    }

    /**
     * 상세 페이지
     *
     * @param menuType
     * @param boardSeq
     * @param model
     * @return
     */
    @GetMapping("/{menuType}/{boardSeq}")
    public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq, Model model) {
        Board board = boardService.get(boardSeq);

        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시글" });
        }

        model.addAttribute("board", board);
        model.addAttribute("menuType", menuType);

        return "/board/detail";
    }

    /**
     * 등록 화면으로 이동
     *
     * @param menuType
     * @param parameter
     * @param model
     */
    @GetMapping("/{menuType}/form")
    @RequestConfig(loginCheck = false)
    public String form(@PathVariable MenuType menuType, BoardParameter parameter, Model model) {
        model.addAttribute("parameter", parameter);
        model.addAttribute("menuType", menuType);

        return "/board/form";
    }

    /**
     * 수정 화면으로 이동
     *
     * @param menuType
     * @param boardSeq
     * @param parameter
     * @param model
     * @return
     */
    @GetMapping("/{menuType}/edit/{boardSeq}")
    @RequestConfig(loginCheck = false)
    public String edit(@PathVariable MenuType menuType, @PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model) {
        // 수정 화면
        if (parameter.getBoardSeq() > 0) {
            Board board = boardService.get(parameter.getBoardSeq());
            model.addAttribute("board", board);
        }

        model.addAttribute("parameter", parameter);
        model.addAttribute("menuType", menuType);

        return "/board/form";
    }

    /**
     * 게시글 등록/수정 처리
     *
     * @param board
     * @return
     */
    @PostMapping("/{menuType}/save")
    @RequestConfig(loginCheck = false)
    @ResponseBody
    @ApiOperation(value = "게시글 등록/수정 처리", notes = "신규 게시글 저장 및 기존 게시글 수정이 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", example = "title1"),
            @ApiImplicitParam(name = "boardType", value = "게시글 유형", example = "NOTICE"),
            @ApiImplicitParam(name = "contents", value = "게시글 내용", example = "contents1")
    })
    public BaseResponse<Integer> save(@PathVariable MenuType menuType, BoardParameter board) {

        // 게시글 제목 필수 체크
        if (StringUtils.isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "게시글 제목" });
        }

        // 게시글 내용 필수 체크
        if (StringUtils.isEmpty(board.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "게시글 내용" });
        }

        board.setBoardType(menuType.boardType());

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

    /**
     * vuejs 연동을 위한 게시글 목록 리스트 조회 Api
     * RestApi 형식을 사용하기 위해 method 앞에 @ResponseBody 붙여서 사용
     *
     * @return
     */
    @GetMapping("/getListApi")
    public @ResponseBody List<Board> getListApi() {
        return boardService.getListApi();
    }
}
