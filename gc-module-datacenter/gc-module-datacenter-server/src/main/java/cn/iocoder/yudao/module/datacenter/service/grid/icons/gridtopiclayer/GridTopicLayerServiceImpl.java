package cn.iocoder.yudao.module.datacenter.service.grid.icons.gridtopiclayer;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridtopiclayer.GridTopicLayerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridtopiclayer.GridTopicLayerMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格专题图层管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridTopicLayerServiceImpl implements GridTopicLayerService {

    @Resource
    private GridTopicLayerMapper gridTopicLayerMapper;

    @Override
    public Long createGridTopicLayer(GridTopicLayerSaveReqVO createReqVO) {
        // 插入
        GridTopicLayerDO gridTopicLayer = BeanUtils.toBean(createReqVO, GridTopicLayerDO.class);
        gridTopicLayerMapper.insert(gridTopicLayer);
        // 返回
        return gridTopicLayer.getId();
    }

    @Override
    public void updateGridTopicLayer(GridTopicLayerSaveReqVO updateReqVO) {
        // 校验存在
        validateGridTopicLayerExists(updateReqVO.getId());
        // 更新
        GridTopicLayerDO updateObj = BeanUtils.toBean(updateReqVO, GridTopicLayerDO.class);
        gridTopicLayerMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridTopicLayer(Long id) {
        // 校验存在
        validateGridTopicLayerExists(id);
        // 删除
        gridTopicLayerMapper.deleteById(id);
    }

    private void validateGridTopicLayerExists(Long id) {
        if (gridTopicLayerMapper.selectById(id) == null) {
            throw exception(GRID_TOPIC_LAYER_NOT_EXISTS);
        }
    }

    @Override
    public GridTopicLayerDO getGridTopicLayer(Long id) {
        return gridTopicLayerMapper.selectById(id);
    }

    @Override
    public PageResult<GridTopicLayerDO> getGridTopicLayerPage(GridTopicLayerPageReqVO pageReqVO) {
        return gridTopicLayerMapper.selectPage(pageReqVO);
    }

}