package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectledgerarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectledgerarch.InspectLedgerArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检台账档案 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectLedgerArchMapper extends BaseMapperX<InspectLedgerArchDO> {

    default PageResult<InspectLedgerArchDO> selectPage(InspectLedgerArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectLedgerArchDO>()
                .eqIfPresent(InspectLedgerArchDO::getLedgerArchId, reqVO.getLedgerArchId())
                .eqIfPresent(InspectLedgerArchDO::getLedgerArchCode, reqVO.getLedgerArchCode())
                .likeIfPresent(InspectLedgerArchDO::getLedgerArchName, reqVO.getLedgerArchName())
                .eqIfPresent(InspectLedgerArchDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectLedgerArchDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectLedgerArchDO::getLedgerCycle, reqVO.getLedgerCycle())
                .eqIfPresent(InspectLedgerArchDO::getCoreIdxJson, reqVO.getCoreIdxJson())
                .eqIfPresent(InspectLedgerArchDO::getIncludeArchTypes, reqVO.getIncludeArchTypes())
                .eqIfPresent(InspectLedgerArchDO::getLedgerAttachUrl, reqVO.getLedgerAttachUrl())
                .eqIfPresent(InspectLedgerArchDO::getLedgerArchUser, reqVO.getLedgerArchUser())
                .betweenIfPresent(InspectLedgerArchDO::getLedgerArchTime, reqVO.getLedgerArchTime())
                .eqIfPresent(InspectLedgerArchDO::getLedgerArchStatus, reqVO.getLedgerArchStatus())
                .betweenIfPresent(InspectLedgerArchDO::getLedgerDestroyTime, reqVO.getLedgerDestroyTime())
                .eqIfPresent(InspectLedgerArchDO::getLedgerArchDesc, reqVO.getLedgerArchDesc())
                .eqIfPresent(InspectLedgerArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectLedgerArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectLedgerArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectLedgerArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectLedgerArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectLedgerArchDO::getId));
    }

}