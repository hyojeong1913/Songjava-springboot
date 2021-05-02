package kr.co.songjava.mvc.parameter;

import lombok.Data;

@Data
public class BoardParameter {

    private int boardSeq;
    private String title;
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
