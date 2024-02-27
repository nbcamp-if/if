package com.nbcampif.ifstagram.domain.repost.entity;

import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reposts")
@NoArgsConstructor
public class Repost {

  private Long repostId; // 가져온 post id

  @Id
  private Long postId; // 가져온 post를 생성할 때 생기는 post id 즉, repost된 게시글의 id

  private Long userId; // 누군가의 글을 가져다 쓴 유저 id

  public Repost(Post savePostInfo, User user) {
    this.repostId = savePostInfo.getPostId();
    this.postId = savePostInfo.getRepostId();
    this.userId = user.getUserId();
  }
}
