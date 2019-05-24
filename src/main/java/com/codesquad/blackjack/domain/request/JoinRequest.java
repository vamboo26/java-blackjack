package com.codesquad.blackjack.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
public class JoinRequest {

    @ApiModelProperty(value = "로그인 아이디", required = true)
    private String userId;

    @ApiModelProperty(value = "로그인 비밀번호", required = true)
    private String password;

    @ApiModelProperty(value = "사용자 이름", required = true)
    private String name;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("userId", userId)
                .append("password", password)
                .append("name", name)
                .toString();
    }

}
