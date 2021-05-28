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
    private String menuCode;
    private String url;

    MenuType(BoardType boardType, String menuCode, String url) {
        this.boardType = boardType;
        this.menuCode = menuCode;
        this.url = url;
    }

    public BoardType boardType() {
        return boardType;
    }

    MenuType(BoardType boardType) {
        this.boardType = boardType;
    }
}
