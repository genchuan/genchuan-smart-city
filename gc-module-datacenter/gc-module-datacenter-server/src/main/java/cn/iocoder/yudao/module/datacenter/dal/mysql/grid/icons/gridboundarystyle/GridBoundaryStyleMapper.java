package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridboundarystyle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStylePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridboundarystyle.GridBoundaryStyleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格边界线样式配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridBoundaryStyleMapper extends BaseMapperX<GridBoundaryStyleDO> {

    default PageResult<GridBoundaryStyleDO> selectPage(GridBoundaryStylePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridBoundaryStyleDO>()
                .eqIfPresent(GridBoundaryStyleDO::getStyleId, reqVO.getStyleId())
                .eqIfPresent(GridBoundaryStyleDO::getScale, reqVO.getScale())
                .eqIfPresent(GridBoundaryStyleDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridBoundaryStyleDO::getLineWidth, reqVO.getLineWidth())
                .eqIfPresent(GridBoundaryStyleDO::getColorC, reqVO.getColorC())
                .eqIfPresent(GridBoundaryStyleDO::getColorM, reqVO.getColorM())
                .eqIfPresent(GridBoundaryStyleDO::getColorY, reqVO.getColorY())
                .eqIfPresent(GridBoundaryStyleDO::getColorK, reqVO.getColorK())
                .eqIfPresent(GridBoundaryStyleDO::getCreateUserId, reqVO.getCreateUserId())
                .eqIfPresent(GridBoundaryStyleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GridBoundaryStyleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GridBoundaryStyleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GridBoundaryStyleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GridBoundaryStyleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(GridBoundaryStyleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridBoundaryStyleDO::getId));
    }

}