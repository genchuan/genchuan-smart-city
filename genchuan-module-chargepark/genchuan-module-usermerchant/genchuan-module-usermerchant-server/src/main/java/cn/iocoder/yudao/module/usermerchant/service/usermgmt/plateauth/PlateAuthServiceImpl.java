package cn.iocoder.yudao.module.usermerchant.service.usermgmt.plateauth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.plateauth.PlateAuthMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 车牌认证 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PlateAuthServiceImpl implements PlateAuthService {

    @Resource
    private PlateAuthMapper plateAuthMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Long createPlateAuth(PlateAuthSaveReqVO createReqVO) {
        // 插入
        PlateAuthDO plateAuth = BeanUtils.toBean(createReqVO, PlateAuthDO.class);
        plateAuthMapper.insert(plateAuth);

        // 返回
        return plateAuth.getId();
    }

    @Override
    public void updatePlateAuth(PlateAuthSaveReqVO updateReqVO) {
        // 校验存在
        validatePlateAuthExists(updateReqVO.getId());
        // 更新
        PlateAuthDO updateObj = BeanUtils.toBean(updateReqVO, PlateAuthDO.class);
        plateAuthMapper.updateById(updateObj);
    }

    @Override
    public void deletePlateAuth(Long id) {
        // 校验存在
        validatePlateAuthExists(id);
        // 删除
        plateAuthMapper.deleteById(id);
    }

    @Override
        public void deletePlateAuthListByIds(List<Long> ids) {
        // 删除
        plateAuthMapper.deleteByIds(ids);
        }

    private void validatePlateAuthExists(Long id) {
        if (plateAuthMapper.selectById(id) == null) {
            throw exception(PLATE_AUTH_NOT_EXISTS);
        }
    }

    @Override
    public PlateAuthDO getPlateAuth(Long id) {
        return plateAuthMapper.selectById(id);
    }

    @Override
    public PageResult<PlateAuthDO> getPlateAuthPage(PlateAuthPageReqVO pageReqVO) {
        // 1. 处理昵称筛选：如果前端传了 nickname，则转换为 userId 并设置到查询条件
        if (StrUtil.isNotBlank(pageReqVO.getNickname())) {
            Long userId = userInfoMapper.getIdByNickname(pageReqVO.getNickname());
            if (userId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setUserId(userId);
        }
        if (StrUtil.isNotBlank(pageReqVO.getAuditorName())) {
            Long auditorId = userInfoMapper.getIdByNickname(pageReqVO.getAuditorName());
            if (auditorId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setAuditorId(auditorId);
        }

        PageResult<PlateAuthDO> pageResult = plateAuthMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                PlateAuthDO::getUserId,
                PlateAuthDO::setNickname,
                "user_info", "id", "nickname"
        );

        // 批量填充审核人昵称
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                PlateAuthDO::getAuditorId,
                PlateAuthDO::setAuditorName,
                "user_info", "id", "nickname"
        );

        return pageResult;
    }

}