package kr.co.songjava.mvc.parameter;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

import java.util.List;

/**
 * 게시글 검색 파라미터
 */
@Data
public class BoardSearchParameter {

    private String keyword;
    private BoardType[] boardTypes;

    // 기본 생성자
    public BoardSearchParameter() {
    }
}
