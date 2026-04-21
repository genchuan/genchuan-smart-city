package cn.iocoder.yudao.module.inspectop.dal.mysql.inspectreport;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;

/**
 * 巡检上报 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectReportMapper extends BaseMapperX<InspectReportDO> {

    default PageResult<InspectReportDO> selectPage(InspectReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectReportDO>()
                .eqIfPresent(InspectReportDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectReportDO::getType, reqVO.getType())
                .betweenIfPresent(InspectReportDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(InspectReportDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectReportDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(InspectReportDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(InspectReportDO::getProcessUserId, reqVO.getProcessUserId())
                .betweenIfPresent(InspectReportDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(InspectReportDO::getContent, reqVO.getContent())
                .eqIfPresent(InspectReportDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InspectReportDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectReportDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectReportDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectReportDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectReportDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectReportDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectReportDO::getId));
    }

}