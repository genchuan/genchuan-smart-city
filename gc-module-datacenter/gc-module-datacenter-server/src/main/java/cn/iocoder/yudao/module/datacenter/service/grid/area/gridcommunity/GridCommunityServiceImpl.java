package cn.iocoder.yudao.module.datacenter.service.grid.area.gridcommunity;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunitySaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcommunity.GridCommunityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridcommunity.GridCommunityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 社区（村）行政区划配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridCommunityServiceImpl implements GridCommunityService {

    @Resource
    private GridCommunityMapper gridCommunityMapper;

    @Override
    public Long createGridCommunity(GridCommunitySaveReqVO createReqVO) {
        // 插入
        GridCommunityDO gridCommunity = BeanUtils.toBean(createReqVO, GridCommunityDO.class);
        gridCommunityMapper.insert(gridCommunity);
        // 返回
        return gridCommunity.getId();
    }

    @Override
    public void updateGridCommunity(GridCommunitySaveReqVO updateReqVO) {
        // 校验存在
        validateGridCommunityExists(updateReqVO.getId());
        // 更新
        GridCommunityDO updateObj = BeanUtils.toBean(updateReqVO, GridCommunityDO.class);
        gridCommunityMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridCommunity(Long id) {
        // 校验存在
        validateGridCommunityExists(id);
        // 删除
        gridCommunityMapper.deleteById(id);
    }

    private void validateGridCommunityExists(Long id) {
        if (gridCommunityMapper.selectById(id) == null) {
            throw exception(GRID_COMMUNITY_NOT_EXISTS);
        }
    }

    @Override
    public GridCommunityDO getGridCommunity(Long id) {
        return gridCommunityMapper.selectById(id);
    }

    @Override
    public PageResult<GridCommunityDO> getGridCommunityPage(GridCommunityPageReqVO pageReqVO) {
        return gridCommunityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GridCommunityDO> getGridCommunityList() {
        return gridCommunityMapper.selectList();
    }

    @Override
    public List<GridCommunityDO> getGridCommunityListByStreetId(String streetId) {
        return gridCommunityMapper.selectListByStreetId(streetId);
    }

    @Override
    public List<GridCommunityDO> getGridCommunityListByStreetIds(List<String> streetIds) {
        return gridCommunityMapper.selectListByStreetIds(streetIds);
    }

    @Override
    public List<GridCommunityDO> getGridCommunityListByName(String communityName) {
        return gridCommunityMapper.selectListByName(communityName);
    }

}