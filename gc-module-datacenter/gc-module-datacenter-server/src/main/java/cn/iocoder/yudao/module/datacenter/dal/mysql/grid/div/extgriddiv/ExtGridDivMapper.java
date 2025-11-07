package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.extgriddiv;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.extgriddiv.ExtGridDivDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扩展网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface ExtGridDivMapper extends BaseMapperX<ExtGridDivDO> {

    default PageResult<ExtGridDivDO> selectPage(ExtGridDivPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtGridDivDO>()
                .eqIfPresent(ExtGridDivDO::getExtGridId, reqVO.getExtGridId())
                .likeIfPresent(ExtGridDivDO::getExtGridName, reqVO.getExtGridName())
                .eqIfPresent(ExtGridDivDO::getExtType, reqVO.getExtType())
                .eqIfPresent(ExtGridDivDO::getIncludedBasicIds, reqVO.getIncludedBasicIds())
                .eqIfPresent(ExtGridDivDO::getBasicGridType, reqVO.getBasicGridType())
                .eqIfPresent(ExtGridDivDO::getArea, reqVO.getArea())
                .eqIfPresent(ExtGridDivDO::getBoundaryCoords, reqVO.getBoundaryCoords())
                .eqIfPresent(ExtGridDivDO::getApplyReason, reqVO.getApplyReason())
                .eqIfPresent(ExtGridDivDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(ExtGridDivDO::getApplyUserId, reqVO.getApplyUserId())
                .betweenIfPresent(ExtGridDivDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(ExtGridDivDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(ExtGridDivDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(ExtGridDivDO::getAuditOpinion, reqVO.getAuditOpinion())
                .eqIfPresent(ExtGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ExtGridDivDO::getExtCat2, reqVO.getExtCat2())
                .betweenIfPresent(ExtGridDivDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExtGridDivDO::getId));
    }

}