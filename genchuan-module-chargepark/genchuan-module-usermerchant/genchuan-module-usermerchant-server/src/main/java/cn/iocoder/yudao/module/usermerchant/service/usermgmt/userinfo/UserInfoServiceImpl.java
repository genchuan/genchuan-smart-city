package cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 用户信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean createUserInfo(@Valid UserInfoCreateReqVO createReqVO) {
        // 插入
        UserInfoDO userInfo = BeanUtils.toBean(createReqVO, UserInfoDO.class);
        int rows = userInfoMapper.insert(userInfo);
        // 返回是否插入成功
        return rows > 0;
    }

    @Override
    public void updateUserInfo(UserInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserInfoExists(updateReqVO.getId());
        // 更新
        UserInfoDO updateObj = BeanUtils.toBean(updateReqVO, UserInfoDO.class);
        userInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserInfo(Long id) {
        // 校验存在
        validateUserInfoExists(id);
        // 删除
        userInfoMapper.deleteById(id);
    }

    @Override
        public void deleteUserInfoListByIds(List<Long> ids) {
        // 删除
        userInfoMapper.deleteByIds(ids);
        }


    private void validateUserInfoExists(Long id) {
        if (userInfoMapper.selectById(id) == null) {
            throw exception(USER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public UserInfoDO getUserInfo(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public PageResult<UserInfoDO> getUserInfoPage(UserInfoPageReqVO pageReqVO) {
        return userInfoMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importUsers(List<UserInfoImportExcelVO> list, Boolean updateSupport) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (UserInfoImportExcelVO vo : list) {
            if (vo.getId() != null) {
                UserInfoDO existDO = userInfoMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (Boolean.TRUE.equals(updateSupport)) {
                        // 更新：复制属性，但保护创建信息
                        UserInfoDO updateDO = BeanUtils.toBean(vo, UserInfoDO.class);
                        updateDO.setCreator(null);
                        updateDO.setCreateTime(null);
                        userInfoMapper.updateById(updateDO);
                    } else {
                        // updateSupport = false，跳过该条记录
                        continue;
                    }
                } else {
                    // ID 不存在，按新增处理（忽略用户提供的 ID，由数据库自增）
                    UserInfoDO insertDO = BeanUtils.toBean(vo, UserInfoDO.class);
                    insertDO.setId(null);
                    userInfoMapper.insert(insertDO);
                }
            } else {
                // 无 ID，直接新增
                UserInfoDO insertDO = BeanUtils.toBean(vo, UserInfoDO.class);
                userInfoMapper.insert(insertDO);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(List<Long> ids, String status) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<UserInfoDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                .set("status", status);
        userInfoMapper.update(null, updateWrapper);
    }

}