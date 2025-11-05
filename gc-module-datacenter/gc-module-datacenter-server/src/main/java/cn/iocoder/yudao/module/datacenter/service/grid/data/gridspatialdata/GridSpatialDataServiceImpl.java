package cn.iocoder.yudao.module.datacenter.service.grid.data.gridspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.gridspatialdata.GridSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.gridspatialdata.GridSpatialDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格空间数据 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class GridSpatialDataServiceImpl implements GridSpatialDataService {

    @Resource
    private GridSpatialDataMapper gridSpatialDataMapper;

    @Override
    public Long createGridSpatialData(GridSpatialDataSaveReqVO createReqVO) {
        // 插入
        GridSpatialDataDO gridSpatialData = BeanUtils.toBean(createReqVO, GridSpatialDataDO.class);
        gridSpatialDataMapper.insert(gridSpatialData);
        // 返回
        return gridSpatialData.getId();
    }

    @Override
    public void updateGridSpatialData(GridSpatialDataSaveReqVO updateReqVO) {
        // 校验存在
        validateGridSpatialDataExists(updateReqVO.getId());
        // 更新
        GridSpatialDataDO updateObj = BeanUtils.toBean(updateReqVO, GridSpatialDataDO.class);
        gridSpatialDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridSpatialData(Long id) {
        // 校验存在
        validateGridSpatialDataExists(id);
        // 删除
        gridSpatialDataMapper.deleteById(id);
    }

    private void validateGridSpatialDataExists(Long id) {
        if (gridSpatialDataMapper.selectById(id) == null) {
            throw exception(GRID_SPATIAL_DATA_NOT_EXISTS);
        }
    }

    @Override
    public GridSpatialDataDO getGridSpatialData(Long id) {
        return gridSpatialDataMapper.selectById(id);
    }

    @Override
    public PageResult<GridSpatialDataDO> getGridSpatialDataPage(GridSpatialDataPageReqVO pageReqVO) {
        return gridSpatialDataMapper.selectPage(pageReqVO);
    }

}