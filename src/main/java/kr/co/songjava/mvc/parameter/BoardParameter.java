package kr.co.songjava.mvc.parameter;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

/**
 * 게시글 파라미터
 */
@Data
public class BoardParameter {

    private int boardSeq;
    private String title;
    private BoardType boardType;
    private String contents;

    // 기본 생성자
    public BoardParameter() {
    }

    // 테스트용 생성자
    public BoardParameter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
