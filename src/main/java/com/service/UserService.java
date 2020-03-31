package com.service;

import com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User, String>{
    Map<String, Object> checkUserAcc(String acc);

    int addUser(User user);

    Map<String,Object> userLogin(String acc, String password);

    Map<String,Object>  updateUser(User user, String onlineId);

    public int updateImg(String onlineId, String userId, String img);

    public User getUserByAcc(String acc);

    public Map<String,Object> getAccByMap(Map<String, Object> map, int currentPage, int pageSize);



    int changeAccStatus(List<String> ids, String status);

    Map<String, Object> adminLogin(String acc, String password);

    Map<String, Object> getLoginSuccessInfo(String acc, String password);

}
