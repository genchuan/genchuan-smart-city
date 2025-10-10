package cn.iocoder.yudao.module.datacenter.service.gridcommunity;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.gridcommunity.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.gridcommunity.GridCommunityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.gridcommunity.GridCommunityMapper;

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

}