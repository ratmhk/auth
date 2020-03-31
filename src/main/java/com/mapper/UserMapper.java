package com.mapper;

import com.entity.School;
import com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,String>{
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByAcc(String acc);

    User selectByPrimaryKey(String id);

    String getPwdByAcc(String acc);

    int checkAcc(String acc);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateImg(Map<String, Object> map);

    int changeAccStatusBatch(Map<String,Object> map);

    School getUserSchool(String userId);

    Map<String,Object> selectAdminAcc(String userId);//Map<String,Object> map


}