package com.jzcccc.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @RequestMapping("/me")
  @ResponseBody
  public ResponseEntity<UserDto> me(Authentication authentication) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String principal = (String) authentication.getPrincipal();
    Map<String, Object> additionalInfo = getAdditionalInfo(authentication);
    int userId = (int) additionalInfo.get("user_id");
    return ResponseEntity.ok(new UserDto(principal, userId));
  }

  private Map<String, Object> getAdditionalInfo(Authentication authentication) {
    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
    return (Map<String, Object>) details.getDecodedDetails();
  }

  @RequestMapping("/me2")
  @ResponseBody
  public String me() {

    return "";
  }

  @ResponseBody
  @RequestMapping("/test")
  public String test() {
    return "{\"test\":\"test\"}";
  }

  private class UserDto {

    private int userId;
    private String username;

    UserDto(String username, int userId) {
      this.username = username;
      this.userId = userId;
    }

    public int getUserId() {
      return userId;
    }

    public String getUsername() {
      return username;
    }

  }

}
