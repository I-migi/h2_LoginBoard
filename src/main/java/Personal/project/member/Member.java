package Personal.project.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public Member(Long id, String name, String loginId, String password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    public Member() {

    }
}
