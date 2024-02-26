package com.nbcampif.ifstagram.domain.post.service;

import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostResponseDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public void createPost(PostRequestDto requestDto, MultipartFile image) throws IOException {
    String uuid = UUID.randomUUID().toString();
    String fileName = image.getOriginalFilename(); // 내가 지정한 이미지명 등록
    System.out.println("이미지 파일 명 : " + fileName);

    Path path = Paths.get(System.getProperty("user.home"), "Desktop", "IFstagram"); // 이미지가 저장될 경로 지정
    System.out.println("이미지 저장할 디렉토리 생성 : " + path);

    Path filePath = path.resolve(uuid + fileName); // 파일의 경로 지정
    System.out.println("파일 경로 지정 : " + filePath);

    String postImage = filePath + "";
    System.out.println(postImage);

    if(!Files.exists(path)) {
      Files.createDirectories(path); // 디렉토리 생성
    }

    Files.copy(image.getInputStream(), filePath); // 파일 저장(파일입력스트림을 파일로 복사하여 로컬에 저장)

    Post post = new Post(requestDto, postImage);
    System.out.println(post);

    postRepository.save(post);
  }

  @Transactional(readOnly = true)
  public List<Post> getPostList() {
    return postRepository.findAll();
  }

  @Transactional(readOnly = true)
  public PostResponseDto getPost(Long postId) {
    Post post = findPost(postId);
    PostResponseDto responseDto = new PostResponseDto(
        post.getTitle(),
        post.getContent(),
        post.getPostImg()
    );
    return responseDto;
  }

  @Transactional
  public void updatePost(Long postId, PostRequestDto requestDto) {
    Post post = findPost(postId);

    post.updatePost(requestDto);
  }

  @Transactional
  public void deletePost(Long postId) {
    Post post = findPost(postId);

    postRepository.delete(post);
  }

  private Post findPost (Long postId) {
    return postRepository.findById(postId).orElseThrow(()
      -> new IllegalCallerException("일치하는 게시글이 없습니다."));
  }
}
