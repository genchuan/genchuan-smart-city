package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcat.MonCompCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompcat.MonCompCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompCatServiceImpl implements MonCompCatService {

    @Resource
    private MonCompCatMapper monCompCatMapper;

    @Override
    public Long createMonCompCat(MonCompCatSaveReqVO createReqVO) {
        // 插入
        MonCompCatDO monCompCat = BeanUtils.toBean(createReqVO, MonCompCatDO.class);
        monCompCatMapper.insert(monCompCat);
        // 返回
        return monCompCat.getId();
    }

    @Override
    public void updateMonCompCat(MonCompCatSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompCatExists(updateReqVO.getId());
        // 更新
        MonCompCatDO updateObj = BeanUtils.toBean(updateReqVO, MonCompCatDO.class);
        monCompCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompCat(Long id) {
        // 校验存在
        validateMonCompCatExists(id);
        // 删除
        monCompCatMapper.deleteById(id);
    }

    private void validateMonCompCatExists(Long id) {
        if (monCompCatMapper.selectById(id) == null) {
            throw exception(MON_COMP_CAT_NOT_EXISTS);
        }
    }

    @Override
    public MonCompCatDO getMonCompCat(Long id) {
        return monCompCatMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompCatDO> getMonCompCatPage(MonCompCatPageReqVO pageReqVO) {
        return monCompCatMapper.selectPage(pageReqVO);
    }

}