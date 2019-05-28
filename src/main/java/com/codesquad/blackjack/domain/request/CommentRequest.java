package com.codesquad.blackjack.domain.request;

import com.codesquad.blackjack.domain.Comment;
import com.codesquad.blackjack.domain.player.User;
import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    private String contents;

    public Comment newComment(User writer) {
        return new Comment(contents, writer);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("contents", contents)
                .toString();
    }

}
