package com.service.impl;

import com.entity.PageBean;
import com.entity.School;
import com.entity.User;
import com.mapper.MenuMapper;
import com.mapper.UserMapper;
import com.mapper.UserRoleMapper;
import com.service.UserService;
import com.utils.MyEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  extends BaseServiceImpl<User,String>  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Map<String, Object> checkUserAcc(String acc) {
        Map<String,Object> map = new HashMap<>();
        boolean canAddUser = true ;  // 不能添加
        int count = userMapper.checkAcc(acc) ;
        if(count >0){// 已经存在记录
            canAddUser = false;
        }
        map.put("count", count);
        map.put("canAddUser", canAddUser);
        return map;
    }

    @Override
    @Transactional
    public int addUser(User user) {
        user.setIdForInsert("system");
        user.setPassword(MyEncodeUtil.getSHA256StrJava(user.getPassword(),MyEncodeUtil.SHA256) );
        return  userMapper.insert(user)  ;
    }

    @Override
    @Transactional
   public Map<String,Object> userLogin(String acc,String password){
       Map<String , Object> map = new HashMap<>();
       map.put("flag", false);
       User user = userMapper.selectByAcc(acc);//userMapper.getPwdByAcc(acc);
       if (null == user) return map; //用户不存在
        else if(null != user.getStatus() && "0".equals(user.getStatus())){// '状态1正常 0冻结'
            return map;
        }
       String realPwd = user.getPassword();
       if (realPwd != null &&  password.equals(realPwd)) {
            map.put("flag", true);
            user.setPassword(null);
            School school = userMapper.getUserSchool(user.getId());
            map.put("userInfo", user);
            map.put("schoolInfo", school);
        }
        return map;
   };

    @Override
    @Transactional
    public Map<String,Object> updateUser(User user,String onlineId){
        if (user.getId() == null){
            throw new RuntimeException("系统错误，用户id错误");
        }
         
        String userId = user.getId();
        user.setCommonFiledsForUpdate(onlineId);
        userMapper.updateByPrimaryKeySelective(user);
        User userInfo = userMapper.selectByPrimaryKey(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",userInfo);


        return map ;
    }

    @Override
    @Transactional
    public int updateImg(String onlineId , String userId ,String img){
        Map<String ,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("img",img);
        int res =  userMapper.updateImg(map);
        return res;
    }


    @Override
    public User getUserByAcc(String acc){
        return userMapper.selectByAcc(acc);
    }

    @Override
    public Map<String,Object> getAccByMap(Map<String,Object> map,int currentPage, int pageSize){
        Map<String, Object> body = new HashMap<String, Object> ();
        List<User> list = new ArrayList<User> ();
        int totalRecord = 0;//总记录数
        PageBean pb = new PageBean(currentPage, pageSize, totalRecord);

        totalRecord = userMapper.getCountByMap(map).intValue();


        if (totalRecord == 0) {  // 当总记录为0时，直接返回
            body.put("totalPage", pb.getTotalPage());
            body.put("totalRecord", totalRecord);
            body.put("list", list);
            return body;
        }

        map.put("startIndex", pb.getStartIndex());
        map.put("pageSize", pageSize);

        list = userMapper.getListByMap(map);

        pb = new PageBean(currentPage, pageSize, totalRecord);
        body.put("totalPage", pb.getTotalPage());
        body.put("totalRecord", totalRecord);
        body.put("list", list);

        return body;
    };


    @Override
    @Transactional
    public int changeAccStatus(List<String> ids, String status) {
        Map<String,Object> map = new HashMap<>();
        map.put("userIds",ids);
        map.put("status",status);
        if (ids.size() > 0 )   return userMapper.changeAccStatusBatch(map);
        return 0;
    }


    @Override
    public Map<String, Object> adminLogin(String acc, String password) {
        Map<String,Object> res = new HashMap<>();
        res.put("flag", false);
        //User user = userMapper.selectByAcc(acc);//userMapper.getPwdByAcc(acc);
        Map<String,Object> map = userMapper.selectAdminAcc(acc);
        Map<String,Object> auth = new HashMap<>();

        if (null == map) return res; //管理员不存在

        String userId = (String) map.get("userId");
        String realPwd = (String) map.get("password");
        password = MyEncodeUtil.getSHA256StrJava(password,MyEncodeUtil.SHA256);

        if (realPwd != null &&  password.equals(realPwd)) {
            res.put("flag", true);
            List<Map<String,Object>>  roleList = userRoleMapper.getUserRole(userId);

            auth.put("userId",userId);
            map.remove("password");
            res.put("userInfo", map);
            res.put("roleInfo", roleList);
            res.put("menuInfo", menuMapper.getIndexMenuByRole(map));
           /* res.put("schoolInfo", school);*/
        }
        return res;

    }


    public Map<String, Object> getLoginSuccessInfo(String acc, String password) {
        Map<String,Object> res = new HashMap<>();
        Map<String,Object> map = userMapper.selectAdminAcc(acc);
        if (null == map) return res; //管理员不存在

        String userId = (String) map.get("userId");
        res.put("flag", true);
        List<Map<String,Object>>  roleList = userRoleMapper.getUserRole(userId);

        map.remove("password");
        res.put("userInfo", map);
        res.put("roleInfo", roleList);
        res.put("menuInfo", menuMapper.getIndexMenuByRole(map));
        return res;

    }

}
