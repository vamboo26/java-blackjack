package com.codesquad.blackjack.domain.request;

import com.codesquad.blackjack.domain.Post;
import com.codesquad.blackjack.domain.player.User;
import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {

    private String title;

    private String contents;

    public Post newPost(User writer) {
        return new Post(title, contents, writer);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("contents", contents)
                .toString();
    }

}