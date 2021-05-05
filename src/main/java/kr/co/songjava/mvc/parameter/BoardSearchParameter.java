package kr.co.songjava.mvc.parameter;

import lombok.Data;

/**
 * 게시글 검색 파라미터
 */
@Data
public class BoardSearchParameter {

    private String keyword;

    // 기본 생성자
    public BoardSearchParameter() {
    }
}
