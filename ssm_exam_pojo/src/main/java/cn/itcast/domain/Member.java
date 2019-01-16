package cn.itcast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;
}
