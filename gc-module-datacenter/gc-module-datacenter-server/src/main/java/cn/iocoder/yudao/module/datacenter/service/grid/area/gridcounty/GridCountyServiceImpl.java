package cn.iocoder.yudao.module.datacenter.service.grid.area.gridcounty;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountyPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountySaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty.GridCountyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridcounty.GridCountyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 县级及以上行政区划配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridCountyServiceImpl implements GridCountyService {

    @Resource
    private GridCountyMapper gridCountyMapper;

    @Override
    public Long createGridCounty(GridCountySaveReqVO createReqVO) {
        // 插入
        GridCountyDO gridCounty = BeanUtils.toBean(createReqVO, GridCountyDO.class);
        gridCountyMapper.insert(gridCounty);
        // 返回
        return gridCounty.getId();
    }

    @Override
    public void updateGridCounty(GridCountySaveReqVO updateReqVO) {
        // 校验存在
        validateGridCountyExists(updateReqVO.getId());
        // 更新
        GridCountyDO updateObj = BeanUtils.toBean(updateReqVO, GridCountyDO.class);
        gridCountyMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridCounty(Long id) {
        // 校验存在
        validateGridCountyExists(id);
        // 删除
        gridCountyMapper.deleteById(id);
    }

    private void validateGridCountyExists(Long id) {
        if (gridCountyMapper.selectById(id) == null) {
            throw exception(GRID_COUNTY_NOT_EXISTS);
        }
    }

    @Override
    public GridCountyDO getGridCounty(Long id) {
        return gridCountyMapper.selectById(id);
    }

    @Override
    public PageResult<GridCountyDO> getGridCountyPage(GridCountyPageReqVO pageReqVO) {
        return gridCountyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GridCountyDO> getGridCountyList() {
        return gridCountyMapper.selectList();
    }

    @Override
    public List<GridCountyDO> getGridCountyListByParentId(String parentAdminId) {
        return gridCountyMapper.selectList(new LambdaQueryWrapperX<GridCountyDO>()
                .eq(GridCountyDO::getParentAdminId, parentAdminId)
                .orderByDesc(GridCountyDO::getId));
    }

    @Override
    public List<GridCountyDO> getGridCountyListByLevel(Integer adminLevel) {
        return gridCountyMapper.selectList(new LambdaQueryWrapperX<GridCountyDO>()
                .eq(GridCountyDO::getAdminLevel, adminLevel)
                .orderByDesc(GridCountyDO::getId));
    }

}