package kr.co.songjava.mvc.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {

    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;
    private int viewCount;
    private Date regDate;
    private boolean delYn;
}