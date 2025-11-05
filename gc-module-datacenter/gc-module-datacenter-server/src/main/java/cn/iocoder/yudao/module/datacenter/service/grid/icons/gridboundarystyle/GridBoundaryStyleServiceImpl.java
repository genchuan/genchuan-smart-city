package cn.iocoder.yudao.module.datacenter.service.grid.icons.gridboundarystyle;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStylePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStyleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridboundarystyle.GridBoundaryStyleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridboundarystyle.GridBoundaryStyleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格边界线样式配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridBoundaryStyleServiceImpl implements GridBoundaryStyleService {

    @Resource
    private GridBoundaryStyleMapper gridBoundaryStyleMapper;

    @Override
    public Long createGridBoundaryStyle(GridBoundaryStyleSaveReqVO createReqVO) {
        // 插入
        GridBoundaryStyleDO gridBoundaryStyle = BeanUtils.toBean(createReqVO, GridBoundaryStyleDO.class);
        gridBoundaryStyleMapper.insert(gridBoundaryStyle);
        // 返回
        return gridBoundaryStyle.getId();
    }

    @Override
    public void updateGridBoundaryStyle(GridBoundaryStyleSaveReqVO updateReqVO) {
        // 校验存在
        validateGridBoundaryStyleExists(updateReqVO.getId());
        // 更新
        GridBoundaryStyleDO updateObj = BeanUtils.toBean(updateReqVO, GridBoundaryStyleDO.class);
        gridBoundaryStyleMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridBoundaryStyle(Long id) {
        // 校验存在
        validateGridBoundaryStyleExists(id);
        // 删除
        gridBoundaryStyleMapper.deleteById(id);
    }

    private void validateGridBoundaryStyleExists(Long id) {
        if (gridBoundaryStyleMapper.selectById(id) == null) {
            throw exception(GRID_BOUNDARY_STYLE_NOT_EXISTS);
        }
    }

    @Override
    public GridBoundaryStyleDO getGridBoundaryStyle(Long id) {
        return gridBoundaryStyleMapper.selectById(id);
    }

    @Override
    public PageResult<GridBoundaryStyleDO> getGridBoundaryStylePage(GridBoundaryStylePageReqVO pageReqVO) {
        return gridBoundaryStyleMapper.selectPage(pageReqVO);
    }

}