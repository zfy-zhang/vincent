package com.vincent.personal.service.impl;

import com.vincent.personal.dao.UserVoMapper;
import com.vincent.personal.exception.TipException;
import com.vincent.personal.modal.vo.UserVo;
import com.vincent.personal.modal.vo.UserVoExample;
import com.vincent.personal.service.IUserService;
import com.vincent.personal.util.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserVoMapper userDao;

    @Override
    public Integer insertUser(UserVo userVo) {

        Integer uid = null;
        if (StringUtils.isNotBlank(userVo.getUsername()) && StringUtils.isNotBlank(userVo.getEmail())) {
            //用户密码摘要
            String encodePwd = MyUtils.MD5encode(userVo.getUsername() + userVo.getPassword());
            userVo.setPassword(encodePwd);
        }

        return userVo.getUid();
    }

    @Override
    public UserVo queryUserById(Integer uid) {
        UserVo userVo = null;
        if (uid != null) {
            userVo = userDao.selectByPrimaryKey(uid);
        }
        return userVo;
    }

    @Override
    public UserVo login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码为空");
        }
        UserVoExample userVoExample = new UserVoExample();
        UserVoExample.Criteria criteria = userVoExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        long count = userDao.countByExample(userVoExample);
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        String pwd = MyUtils.MD5encode(username + password);
        criteria.andPasswordEqualTo(pwd);
        List<UserVo> userVoList = userDao.selectByExample(userVoExample);
        if (userVoList.size() != 1) {
            throw new TipException("用户名或者密码错误");
        }
        return userVoList.get(0);
    }

    @Override
    public void updateByUid(UserVo userVo) {

        if (null == userVo || null == userVo.getUid()) {
            throw new TipException("userVo is null");
        }
        int i = userDao.updateByPrimaryKeySelective(userVo);
        if (i != 1) {
            throw new TipException("update user by uid and retrun is not one");
        }

    }
}
