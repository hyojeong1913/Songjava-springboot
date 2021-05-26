package kr.co.songjava.mvc.domain;

/**
 * 메뉴 게시판 종류
 */
public enum MenuType {

    community(BoardType.COMMUNITY),
    notice(BoardType.NOTICE),
    faq(BoardType.FAQ),
    inquiry(BoardType.INQUIRY);

    private BoardType boardType;

    MenuType(BoardType boardType) {
        this.boardType = boardType;
    }
}
