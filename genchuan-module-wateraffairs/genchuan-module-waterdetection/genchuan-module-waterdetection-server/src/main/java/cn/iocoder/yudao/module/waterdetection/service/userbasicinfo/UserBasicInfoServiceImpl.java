package cn.iocoder.yudao.module.waterdetection.service.userbasicinfo;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.userbasicinfo.UserBasicInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.userbasicinfo.UserBasicInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 用户基础信息登记 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class UserBasicInfoServiceImpl implements UserBasicInfoService {

    @Resource
    private UserBasicInfoMapper userBasicInfoMapper;

    @Override
    public Long createUserBasicInfo(UserBasicInfoSaveReqVO createReqVO) {
        // 插入
        UserBasicInfoDO userBasicInfo = BeanUtils.toBean(createReqVO, UserBasicInfoDO.class);
        userBasicInfoMapper.insert(userBasicInfo);
        // 返回
        return userBasicInfo.getId();
    }

    @Override
    public void updateUserBasicInfo(UserBasicInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateUserBasicInfoExists(updateReqVO.getId());
        // 更新
        UserBasicInfoDO updateObj = BeanUtils.toBean(updateReqVO, UserBasicInfoDO.class);
        userBasicInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserBasicInfo(Long id) {
        // 校验存在
        validateUserBasicInfoExists(id);
        // 删除
        userBasicInfoMapper.deleteById(id);
    }

    private void validateUserBasicInfoExists(Long id) {
        if (userBasicInfoMapper.selectById(id) == null) {
            throw exception(USER_BASIC_INFO_NOT_EXISTS);
        }
    }

    @Override
    public UserBasicInfoDO getUserBasicInfo(Long id) {
        return userBasicInfoMapper.selectById(id);
    }

    @Override
    public PageResult<UserBasicInfoDO> getUserBasicInfoPage(UserBasicInfoPageReqVO pageReqVO) {
        return userBasicInfoMapper.selectPage(pageReqVO);
    }

}