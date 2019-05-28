package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.player.User;
import com.google.common.base.MoreObjects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_to_post"))
    private Post post;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_to_writer"))
    private User writer;

    public Comment(String contents, User writer) {
        checkNotNull(contents);
        checkNotNull(writer);
        checkArgument(1 <= contents.length() && contents.length() <= 50,
        "contents length must be between 1 and 50");

        this.contents = contents;
        this.writer = writer;
    }

    public void toPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("contents", contents)
                .add("post", post)
                .add("writer", writer)
                .toString();
    }

}
