package com.nbcampif.ifstagram.domain.post.service;

import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostResponseDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Transactional
  public void createPost(
      PostRequestDto requestDto, MultipartFile image, User user) throws IOException {
    String uuid = UUID.randomUUID().toString();
    // 이미지 저장 시 이름이 겹칠 수 없기 때문에 UUID를 통해 랜덤 문자열 더해서 저장
    User userInfo = userRepository.findUser(user.getUserId()).orElseThrow(()
        -> new IllegalCallerException("존재하지 않는 회원입니다."));

    // 이미지 로직
    String fileName = image.getOriginalFilename(); // 내가 지정한 이미지명 등록
    Path path = Paths.get(
        System.getProperty("user.home"), "Desktop", "IFstagram"); // 이미지가 저장될 경로 지정
    Path filePath = path.resolve(uuid + fileName); // 파일의 경로 지정
    String postImage = filePath + "";

    if (!Files.exists(path)) {
      Files.createDirectories(path); // 디렉토리 생성
    }

    Files.copy(image.getInputStream(), filePath); // 파일 저장(파일입력스트림을 파일로 복사하여 로컬에 저장)

    Post post = new Post(requestDto, postImage, userInfo.getUserId());

    postRepository.save(post);
  }

  @Transactional(readOnly = true)
  public List<PostResponseDto> getPostList() {
    List<PostResponseDto> responseDtos = new ArrayList<>();
    List<Post> postList = postRepository.findAll();
    for (Post p : postList) {
      PostResponseDto responseDto = new PostResponseDto(p);
      List<PostResponseDto> responseDtoList = postRepository.findAll()
          .stream().map(PostResponseDto::new).toList();
      responseDto.setPostList(responseDtoList);
      responseDtos.add(responseDto);
    }
    return responseDtos;
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
    // todo: 사진 수정 불가 찾아보기
  }

  @Transactional
  public void deletePost(Long postId) {
    Post post = findPost(postId);

    postRepository.delete(post);
  }

  private Post findPost(Long postId) {
    return postRepository.findById(postId).orElseThrow(()
        -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    // todo: 사진을 삭제해도 url이 남아 있고 db를 삭제해도 사진이 남아 있음
  }
}
