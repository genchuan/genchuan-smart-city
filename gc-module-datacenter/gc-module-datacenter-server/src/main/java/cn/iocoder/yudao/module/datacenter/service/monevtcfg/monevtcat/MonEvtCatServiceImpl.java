package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcat.MonEvtCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtcat.MonEvtCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtCatServiceImpl implements MonEvtCatService {

    @Resource
    private MonEvtCatMapper monEvtCatMapper;

    @Override
    public Long createMonEvtCat(MonEvtCatSaveReqVO createReqVO) {
        // 插入
        MonEvtCatDO monEvtCat = BeanUtils.toBean(createReqVO, MonEvtCatDO.class);
        monEvtCatMapper.insert(monEvtCat);
        // 返回
        return monEvtCat.getId();
    }

    @Override
    public void updateMonEvtCat(MonEvtCatSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtCatExists(updateReqVO.getId());
        // 更新
        MonEvtCatDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtCatDO.class);
        monEvtCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtCat(Long id) {
        // 校验存在
        validateMonEvtCatExists(id);
        // 删除
        monEvtCatMapper.deleteById(id);
    }

    private void validateMonEvtCatExists(Long id) {
        if (monEvtCatMapper.selectById(id) == null) {
            throw exception(MON_EVT_CAT_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtCatDO getMonEvtCat(Long id) {
        return monEvtCatMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtCatDO> getMonEvtCatPage(MonEvtCatPageReqVO pageReqVO) {
        return monEvtCatMapper.selectPage(pageReqVO);
    }

}