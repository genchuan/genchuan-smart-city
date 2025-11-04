package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridtopiclayer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridtopiclayer.GridTopicLayerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格专题图层管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridTopicLayerMapper extends BaseMapperX<GridTopicLayerDO> {

    default PageResult<GridTopicLayerDO> selectPage(GridTopicLayerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridTopicLayerDO>()
                .eqIfPresent(GridTopicLayerDO::getLayerId, reqVO.getLayerId())
                .likeIfPresent(GridTopicLayerDO::getLayerName, reqVO.getLayerName())
                .eqIfPresent(GridTopicLayerDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridTopicLayerDO::getScale, reqVO.getScale())
                .eqIfPresent(GridTopicLayerDO::getBoundaryStyleId, reqVO.getBoundaryStyleId())
                .eqIfPresent(GridTopicLayerDO::getAnnotateStyleId, reqVO.getAnnotateStyleId())
                .eqIfPresent(GridTopicLayerDO::getDisplayStatus, reqVO.getDisplayStatus())
                .eqIfPresent(GridTopicLayerDO::getLayerWo, reqVO.getLayerWo())
                .eqIfPresent(GridTopicLayerDO::getCreateUserId, reqVO.getCreateUserId())
                .eqIfPresent(GridTopicLayerDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GridTopicLayerDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GridTopicLayerDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GridTopicLayerDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GridTopicLayerDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(GridTopicLayerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridTopicLayerDO::getId));
    }

}