USE bootstudy;

#목록 조회:
SELECT id, title, content, writer, created_at, updated_at
FROM board
ORDER BY id DESC;

#단건 조회:

SELECT id, title, content, writer, created_at, updated_at
FROM board
WHERE id = ?;

#작성:
INSERT INTO board (title, content, writer)
VALUES (?, ?, ?);

#수정:
UPDATE board
SET title = ?, content = ?
WHERE id = ?;

#삭제:
DELETE FROM board
WHERE id = ?;