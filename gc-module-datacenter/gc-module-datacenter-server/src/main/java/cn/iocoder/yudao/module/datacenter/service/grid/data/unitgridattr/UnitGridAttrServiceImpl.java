package cn.iocoder.yudao.module.datacenter.service.grid.data.unitgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.unitgridattr.UnitGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.unitgridattr.UnitGridAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 单元网格属性 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class UnitGridAttrServiceImpl implements UnitGridAttrService {

    @Resource
    private UnitGridAttrMapper unitGridAttrMapper;

    @Override
    public Long createUnitGridAttr(UnitGridAttrSaveReqVO createReqVO) {
        // 插入
        UnitGridAttrDO unitGridAttr = BeanUtils.toBean(createReqVO, UnitGridAttrDO.class);
        unitGridAttrMapper.insert(unitGridAttr);
        // 返回
        return unitGridAttr.getId();
    }

    @Override
    public void updateUnitGridAttr(UnitGridAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateUnitGridAttrExists(updateReqVO.getId());
        // 更新
        UnitGridAttrDO updateObj = BeanUtils.toBean(updateReqVO, UnitGridAttrDO.class);
        unitGridAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteUnitGridAttr(Long id) {
        // 校验存在
        validateUnitGridAttrExists(id);
        // 删除
        unitGridAttrMapper.deleteById(id);
    }

    private void validateUnitGridAttrExists(Long id) {
        if (unitGridAttrMapper.selectById(id) == null) {
            throw exception(UNIT_GRID_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public UnitGridAttrDO getUnitGridAttr(Long id) {
        return unitGridAttrMapper.selectById(id);
    }

    @Override
    public PageResult<UnitGridAttrDO> getUnitGridAttrPage(UnitGridAttrPageReqVO pageReqVO) {
        return unitGridAttrMapper.selectPage(pageReqVO);
    }

}