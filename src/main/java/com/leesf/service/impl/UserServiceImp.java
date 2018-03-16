package com.leesf.service.impl;

import com.leesf.mapper.UsersMapper;
import com.leesf.po.Users;
import com.leesf.po.UsersExample;
import com.leesf.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService") public class UserServiceImp implements UserService {
  @Autowired UsersMapper usersMapper;
  private Logger LOG = LoggerFactory.getLogger(this.getClass());

  public List<Users> getUsers(String name, String key) {
    UsersExample usersExample = new UsersExample();

    if (StringUtils.isNotEmpty(key)) {
      Map<String, String> maps = new HashMap<String, String>();
      maps.put("address", key);
      maps.put("hobby", key);
      usersExample.createCriteria().multiColumnOrLike(maps);
    }

    if (StringUtils.isNotEmpty(name)) {
      if (usersExample.getOredCriteria().size() == 0){
        usersExample.createCriteria();
      }
      usersExample.getOredCriteria().get(0).andNameEqualTo(name);
    }

    List<Users> users = usersMapper.selectByExampleWithBLOBs(usersExample);
    return users;
  }
}
