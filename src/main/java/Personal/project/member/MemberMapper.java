package Personal.project.member;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setLoginId(rs.getString("loginId"));
        member.setName(rs.getString("name"));
        member.setPassword(rs.getString("password"));
        return member;
    }
}
