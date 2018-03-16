package com.leesf.controller;

import com.leesf.po.Users;
import com.leesf.service.UserService;
import com.leesf.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.List;

@Controller @RequestMapping(value = "/users")
public class UserController {
  @Autowired UserService userService;

  @ResponseBody @RequestMapping(value = "/listUsers", method = {
      RequestMethod.POST, RequestMethod.GET }) public void listUsers(
      HttpServletRequest request, HttpServletResponse response,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String key) throws Exception {
    System.out.println("xxxxxx");
    OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
    List<Users> users = userService.getUsers(name, key);
    ResultUtils.resultSuccess(users, out);
  }
}
