package com.leesf.service;

import com.leesf.po.Users;

import java.util.List;

public interface UserService {
  List<Users> getUsers(String name, String key);
}
