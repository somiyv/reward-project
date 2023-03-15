
CREATE TABLE IF NOT EXISTS somidb.member
(
    id       BIGINT            NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(30)       NOT NULL COMMENT '로그인 아이디',
    name     VARCHAR(30)       NOT NULL COMMENT '사용자명',
    is_login tinyint default 0 NOT NULL COMMENT '로그인 여부',
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS somidb.rewards
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    member_id   BIGINT NOT NULL COMMENT '사용자 pk',
    title       VARCHAR(50) COMMENT '제목',
    description TEXT COMMENT '설명',
    point       int COMMENT '포인트',
    create_date datetime COMMENT '생성일자',
    PRIMARY KEY (id),
    constraint rewards_member_id_fk
        foreign key (member_id) references member (id)
) DEFAULT CHARSET=utf8mb4;