package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.griddataqualck;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.griddataqualck.GridDataQualCkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格数据质量检查 Mapper
 *
 * @author zcq
 */
@Mapper
public interface GridDataQualCkMapper extends BaseMapperX<GridDataQualCkDO> {

    default PageResult<GridDataQualCkDO> selectPage(GridDataQualCkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridDataQualCkDO>()
                .eqIfPresent(GridDataQualCkDO::getQualCkId, reqVO.getQualCkId())
                .eqIfPresent(GridDataQualCkDO::getGridId, reqVO.getGridId())
                .eqIfPresent(GridDataQualCkDO::getGridType, reqVO.getGridType())
                .eqIfPresent(GridDataQualCkDO::getGridCode, reqVO.getGridCode())
                .eqIfPresent(GridDataQualCkDO::getCkItem, reqVO.getCkItem())
                .eqIfPresent(GridDataQualCkDO::getCkResult, reqVO.getCkResult())
                .eqIfPresent(GridDataQualCkDO::getErrorDesc, reqVO.getErrorDesc())
                .eqIfPresent(GridDataQualCkDO::getRectifySugg, reqVO.getRectifySugg())
                .betweenIfPresent(GridDataQualCkDO::getCkTime, reqVO.getCkTime())
                .eqIfPresent(GridDataQualCkDO::getCkUserId, reqVO.getCkUserId())
                .eqIfPresent(GridDataQualCkDO::getRectifyStatus, reqVO.getRectifyStatus())
                .betweenIfPresent(GridDataQualCkDO::getRectifyTime, reqVO.getRectifyTime())
                .eqIfPresent(GridDataQualCkDO::getRectifyUserId, reqVO.getRectifyUserId())
                .eqIfPresent(GridDataQualCkDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(GridDataQualCkDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(GridDataQualCkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GridDataQualCkDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(GridDataQualCkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GridDataQualCkDO::getId));
    }

}