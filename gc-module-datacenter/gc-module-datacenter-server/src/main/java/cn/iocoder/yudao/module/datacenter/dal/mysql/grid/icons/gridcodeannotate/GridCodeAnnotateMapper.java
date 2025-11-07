package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridcodeannotate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotatePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridcodeannotate.GridCodeAnnotateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格标识码注记配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridCodeAnnotateMapper extends BaseMapperX<GridCodeAnnotateDO> {

    default PageResult<GridCodeAnnotateDO> selectPage(GridCodeAnnotatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCodeAnnotateDO>()
                .eqIfPresent(GridCodeAnnotateDO::getAnnotateId, reqVO.getAnnotateId())
                .eqIfPresent(GridCodeAnnotateDO::getScale, reqVO.getScale())
                .eqIfPresent(GridCodeAnnotateDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridCodeAnnotateDO::getFontHeight, reqVO.getFontHeight())
                .eqIfPresent(GridCodeAnnotateDO::getFontType, reqVO.getFontType())
                .eqIfPresent(GridCodeAnnotateDO::getColorC, reqVO.getColorC())
                .eqIfPresent(GridCodeAnnotateDO::getColorM, reqVO.getColorM())
                .eqIfPresent(GridCodeAnnotateDO::getColorY, reqVO.getColorY())
                .eqIfPresent(GridCodeAnnotateDO::getColorK, reqVO.getColorK())
                .eqIfPresent(GridCodeAnnotateDO::getCreateUserId, reqVO.getCreateUserId())
                .eqIfPresent(GridCodeAnnotateDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GridCodeAnnotateDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GridCodeAnnotateDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GridCodeAnnotateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GridCodeAnnotateDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(GridCodeAnnotateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridCodeAnnotateDO::getId));
    }

}