package com.nbcampif.ifstagram.global.common;

import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;

public class TestValues {

  public final static Long TEST_ID1 = 1L;
  public final static String TEST_EMAIL1 = "TestUser1@email.com";
  public final static String TEST_NICKNAME1 = "TestUser1";
  public final static String TEST_PROFILE_IMAGE1 = "https://k.kakaocdn.net/dn/1G9kp/btsAot8liOn/8CWudi3uy07rvFNUkk3ER0/img_640x640.jpg";
  public final static User TEST_USER1 = new User(TEST_ID1, TEST_EMAIL1, TEST_NICKNAME1, TEST_PROFILE_IMAGE1, "", 0L, UserRole.USER);

  public final static Long TEST_ID2 = 2L;
  public final static String TEST_EMAIL2 = "TestUser2@email.com";
  public final static String TEST_NICKNAME2 = "TestUser2";
  public final static String TEST_PROFILE_IMAGE2 = "https://k.kakaocdn.net/dn/1G9kp/btsAot8liOn/8CWudi3uy07rvFNUkk3ER0/img_640x640.jpg";
  public final static User TEST_USER2 = new User(TEST_ID2, TEST_EMAIL2, TEST_NICKNAME2, TEST_PROFILE_IMAGE2, "", 0L, UserRole.USER);

}
