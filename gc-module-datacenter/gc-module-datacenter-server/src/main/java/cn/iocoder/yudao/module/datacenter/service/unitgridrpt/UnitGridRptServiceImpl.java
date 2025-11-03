package cn.iocoder.yudao.module.datacenter.service.unitgridrpt;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.unitgridrpt.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.unitgridrpt.UnitGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.unitgridrpt.UnitGridRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 单元网格统计 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class UnitGridRptServiceImpl implements UnitGridRptService {

    @Resource
    private UnitGridRptMapper unitGridRptMapper;

    @Override
    public Long createUnitGridRpt(UnitGridRptSaveReqVO createReqVO) {
        // 插入
        UnitGridRptDO unitGridRpt = BeanUtils.toBean(createReqVO, UnitGridRptDO.class);
        unitGridRptMapper.insert(unitGridRpt);
        // 返回
        return unitGridRpt.getId();
    }

    @Override
    public void updateUnitGridRpt(UnitGridRptSaveReqVO updateReqVO) {
        // 校验存在
        validateUnitGridRptExists(updateReqVO.getId());
        // 更新
        UnitGridRptDO updateObj = BeanUtils.toBean(updateReqVO, UnitGridRptDO.class);
        unitGridRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteUnitGridRpt(Long id) {
        // 校验存在
        validateUnitGridRptExists(id);
        // 删除
        unitGridRptMapper.deleteById(id);
    }

    private void validateUnitGridRptExists(Long id) {
        if (unitGridRptMapper.selectById(id) == null) {
            throw exception(UNIT_GRID_RPT_NOT_EXISTS);
        }
    }

    @Override
    public UnitGridRptDO getUnitGridRpt(Long id) {
        return unitGridRptMapper.selectById(id);
    }

    @Override
    public PageResult<UnitGridRptDO> getUnitGridRptPage(UnitGridRptPageReqVO pageReqVO) {
        return unitGridRptMapper.selectPage(pageReqVO);
    }

}