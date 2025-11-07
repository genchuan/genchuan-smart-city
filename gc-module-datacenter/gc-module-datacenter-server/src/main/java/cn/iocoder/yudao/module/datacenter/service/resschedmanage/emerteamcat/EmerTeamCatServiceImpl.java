package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteamcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteamcat.EmerTeamCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerteamcat.EmerTeamCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 救援队伍分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerTeamCatServiceImpl implements EmerTeamCatService {

    @Resource
    private EmerTeamCatMapper emerTeamCatMapper;

    @Override
    public Long createEmerTeamCat(EmerTeamCatSaveReqVO createReqVO) {
        // 插入
        EmerTeamCatDO emerTeamCat = BeanUtils.toBean(createReqVO, EmerTeamCatDO.class);
        emerTeamCatMapper.insert(emerTeamCat);
        // 返回
        return emerTeamCat.getId();
    }

    @Override
    public void updateEmerTeamCat(EmerTeamCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerTeamCatExists(updateReqVO.getId());
        // 更新
        EmerTeamCatDO updateObj = BeanUtils.toBean(updateReqVO, EmerTeamCatDO.class);
        emerTeamCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerTeamCat(Long id) {
        // 校验存在
        validateEmerTeamCatExists(id);
        // 删除
        emerTeamCatMapper.deleteById(id);
    }

    private void validateEmerTeamCatExists(Long id) {
        if (emerTeamCatMapper.selectById(id) == null) {
            throw exception(EMER_TEAM_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EmerTeamCatDO getEmerTeamCat(Long id) {
        return emerTeamCatMapper.selectById(id);
    }

    @Override
    public PageResult<EmerTeamCatDO> getEmerTeamCatPage(EmerTeamCatPageReqVO pageReqVO) {
        return emerTeamCatMapper.selectPage(pageReqVO);
    }

}