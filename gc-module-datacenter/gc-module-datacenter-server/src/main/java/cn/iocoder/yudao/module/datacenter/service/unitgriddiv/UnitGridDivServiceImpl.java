package cn.iocoder.yudao.module.datacenter.service.unitgriddiv;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.unitgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.unitgriddiv.UnitGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 单元网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class UnitGridDivServiceImpl implements UnitGridDivService {

    @Resource
    private UnitGridDivMapper unitGridDivMapper;

    @Override
    public Long createUnitGridDiv(UnitGridDivSaveReqVO createReqVO) {
        // 插入
        UnitGridDivDO unitGridDiv = BeanUtils.toBean(createReqVO, UnitGridDivDO.class);
        unitGridDivMapper.insert(unitGridDiv);
        // 返回
        return unitGridDiv.getId();
    }

    @Override
    public void updateUnitGridDiv(UnitGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateUnitGridDivExists(updateReqVO.getId());
        // 更新
        UnitGridDivDO updateObj = BeanUtils.toBean(updateReqVO, UnitGridDivDO.class);
        unitGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteUnitGridDiv(Long id) {
        // 校验存在
        validateUnitGridDivExists(id);
        // 删除
        unitGridDivMapper.deleteById(id);
    }

    private void validateUnitGridDivExists(Long id) {
        if (unitGridDivMapper.selectById(id) == null) {
            throw exception(UNIT_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public UnitGridDivDO getUnitGridDiv(Long id) {
        return unitGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<UnitGridDivDO> getUnitGridDivPage(UnitGridDivPageReqVO pageReqVO) {
        return unitGridDivMapper.selectPage(pageReqVO);
    }

}