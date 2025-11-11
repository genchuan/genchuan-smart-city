package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo.DomDivGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo.DomDivGridSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivgrid.DomDivGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivgrid.DomDivGridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivGridServiceImpl implements DomDivGridService {

    @Resource
    private DomDivGridMapper domDivGridMapper;

    @Override
    public Long createDomDivGrid(DomDivGridSaveReqVO createReqVO) {
        // 插入
        DomDivGridDO domDivGrid = BeanUtils.toBean(createReqVO, DomDivGridDO.class);
        domDivGridMapper.insert(domDivGrid);
        // 返回
        return domDivGrid.getId();
    }

    @Override
    public void updateDomDivGrid(DomDivGridSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivGridExists(updateReqVO.getId());
        // 更新
        DomDivGridDO updateObj = BeanUtils.toBean(updateReqVO, DomDivGridDO.class);
        domDivGridMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivGrid(Long id) {
        // 校验存在
        validateDomDivGridExists(id);
        // 删除
        domDivGridMapper.deleteById(id);
    }

    private void validateDomDivGridExists(Long id) {
        if (domDivGridMapper.selectById(id) == null) {
            throw exception(DOM_DIV_GRID_NOT_EXISTS);
        }
    }

    @Override
    public DomDivGridDO getDomDivGrid(Long id) {
        return domDivGridMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivGridDO> getDomDivGridPage(DomDivGridPageReqVO pageReqVO) {
        return domDivGridMapper.selectPage(pageReqVO);
    }

}