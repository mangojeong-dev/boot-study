package org.scoula.bootstudy.repository;

import org.scoula.bootstudy.dto.BoardCreateRequest;
import org.scoula.bootstudy.dto.BoardResponse;
import org.scoula.bootstudy.dto.BoardUpdateRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ResultSet 한 줄을 BoardResponse 객체 하나로 바꾸는 매핑 규칙이다.
    // JDBC는 SQL 결과를 직접 다루기 때문에 이런 변환 코드를 Repository에 둔다.
    private final RowMapper<BoardResponse> boardRowMapper = (rs, rowNum) -> new BoardResponse(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("writer"),
            toLocalDateTime(rs.getTimestamp("created_at")),
            toLocalDateTime(rs.getTimestamp("updated_at"))
    );

    public List<BoardResponse> findAll() {
        String sql = """
                SELECT id, title, content, writer, created_at, updated_at
                FROM board
                ORDER BY id DESC
                """;

        return jdbcTemplate.query(sql, boardRowMapper);
    }

    public Optional<BoardResponse> findById(Long id) {
        String sql = """
                SELECT id, title, content, writer, created_at, updated_at
                FROM board
                WHERE id = ?
                """;

        List<BoardResponse> result = jdbcTemplate.query(sql, boardRowMapper, id);
        return result.stream().findFirst();
    }

    public Long save(BoardCreateRequest request) {
        String sql = """
                INSERT INTO board (title, content, writer)
                VALUES (?, ?, ?)
                """;

        // AUTO_INCREMENT로 생성된 id를 다시 받아오기 위해 KeyHolder를 사용한다.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.title());
            ps.setString(2, request.content());
            ps.setString(3, request.writer());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("게시글 id 생성에 실패했습니다.");
        }
        return key.longValue();
    }

    public int update(Long id, BoardUpdateRequest request) {
        String sql = """
                UPDATE board
                SET title = ?, content = ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql, request.title(), request.content(), id);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private static java.time.LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
