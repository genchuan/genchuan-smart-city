package cn.iocoder.yudao.module.datacenter.service.grid.area.gridstreet;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridstreet.GridStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridstreet.GridStreetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 街道（镇、乡）行政区划配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridStreetServiceImpl implements GridStreetService {

    @Resource
    private GridStreetMapper gridStreetMapper;

    @Override
    public Long createGridStreet(GridStreetSaveReqVO createReqVO) {
        // 插入
        GridStreetDO gridStreet = BeanUtils.toBean(createReqVO, GridStreetDO.class);
        gridStreetMapper.insert(gridStreet);
        // 返回
        return gridStreet.getId();
    }

    @Override
    public void updateGridStreet(GridStreetSaveReqVO updateReqVO) {
        // 校验存在
        validateGridStreetExists(updateReqVO.getId());
        // 更新
        GridStreetDO updateObj = BeanUtils.toBean(updateReqVO, GridStreetDO.class);
        gridStreetMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridStreet(Long id) {
        // 校验存在
        validateGridStreetExists(id);
        // 删除
        gridStreetMapper.deleteById(id);
    }

    private void validateGridStreetExists(Long id) {
        if (gridStreetMapper.selectById(id) == null) {
            throw exception(GRID_STREET_NOT_EXISTS);
        }
    }

    @Override
    public GridStreetDO getGridStreet(Long id) {
        return gridStreetMapper.selectById(id);
    }

    @Override
    public PageResult<GridStreetDO> getGridStreetPage(GridStreetPageReqVO pageReqVO) {
        return gridStreetMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GridStreetDO> getGridStreetList() {
        return gridStreetMapper.selectList();
    }

    @Override
    public List<GridStreetDO> getGridStreetListByCountyId(String countyAdminId) {
        return gridStreetMapper.selectList(new LambdaQueryWrapperX<GridStreetDO>()
                .eq(GridStreetDO::getCountyAdminId, countyAdminId)
                .orderByDesc(GridStreetDO::getId));
    }

    @Override
    public List<GridStreetDO> getGridStreetListByLevel(Integer streetLevel) {
        return gridStreetMapper.selectList(new LambdaQueryWrapperX<GridStreetDO>()
                .eq(GridStreetDO::getStreetLevel, streetLevel)
                .orderByDesc(GridStreetDO::getId));
    }

}