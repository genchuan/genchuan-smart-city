package cn.iocoder.yudao.module.datacenter.service.emerplan.emerplancat;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplancat.EmerPlanCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerplancat.EmerPlanCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预案分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerPlanCatServiceImpl implements EmerPlanCatService {

    @Resource
    private EmerPlanCatMapper emerPlanCatMapper;

    @Override
    public Long createEmerPlanCat(EmerPlanCatSaveReqVO createReqVO) {
        // 插入
        EmerPlanCatDO emerPlanCat = BeanUtils.toBean(createReqVO, EmerPlanCatDO.class);
        emerPlanCatMapper.insert(emerPlanCat);
        // 返回
        return emerPlanCat.getId();
    }

    @Override
    public void updateEmerPlanCat(EmerPlanCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerPlanCatExists(updateReqVO.getId());
        // 更新
        EmerPlanCatDO updateObj = BeanUtils.toBean(updateReqVO, EmerPlanCatDO.class);
        emerPlanCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerPlanCat(Long id) {
        // 校验存在
        validateEmerPlanCatExists(id);
        // 删除
        emerPlanCatMapper.deleteById(id);
    }

    private void validateEmerPlanCatExists(Long id) {
        if (emerPlanCatMapper.selectById(id) == null) {
            throw exception(EMER_PLAN_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EmerPlanCatDO getEmerPlanCat(Long id) {
        return emerPlanCatMapper.selectById(id);
    }

    @Override
    public PageResult<EmerPlanCatDO> getEmerPlanCatPage(EmerPlanCatPageReqVO pageReqVO) {
        return emerPlanCatMapper.selectPage(pageReqVO);
    }

}