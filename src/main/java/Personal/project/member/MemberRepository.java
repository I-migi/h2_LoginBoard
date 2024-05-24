package Personal.project.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member addMember(Member member){
        String sql ="INSERT INTO member(name,loginId,password) VALUES(?,?,?)";
        jdbcTemplate.update(sql,member.getName(),member.getLoginId(),member.getPassword());
        return member;
    }
    public Optional<Member> findByLoginId(String loginId){
        String sql = "SELECT * FROM member WHERE loginId = ?";
        Member member = jdbcTemplate.queryForObject(sql, new Object[]{loginId}, new MemberMapper());
        return Optional.ofNullable(member);
    }

}
