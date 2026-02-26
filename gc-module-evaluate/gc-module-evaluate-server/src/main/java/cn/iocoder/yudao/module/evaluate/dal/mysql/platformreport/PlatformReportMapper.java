package cn.iocoder.yudao.module.evaluate.dal.mysql.platformreport;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.platformreport.PlatformReportDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo.*;

/**
 * 平台上报 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PlatformReportMapper extends BaseMapperX<PlatformReportDO> {

    default PageResult<PlatformReportDO> selectPage(PlatformReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlatformReportDO>()
                .eqIfPresent(PlatformReportDO::getReportId, reqVO.getReportId())
                .eqIfPresent(PlatformReportDO::getBatchNo, reqVO.getBatchNo())
                .eqIfPresent(PlatformReportDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(PlatformReportDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(PlatformReportDO::getReportTime, reqVO.getReportTime())
                .likeIfPresent(PlatformReportDO::getFileName, reqVO.getFileName())
                .eqIfPresent(PlatformReportDO::getDataCount, reqVO.getDataCount())
                .eqIfPresent(PlatformReportDO::getSuccessCount, reqVO.getSuccessCount())
                .eqIfPresent(PlatformReportDO::getFailCount, reqVO.getFailCount())
                .eqIfPresent(PlatformReportDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PlatformReportDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(PlatformReportDO::getCheckBy, reqVO.getCheckBy())
                .eqIfPresent(PlatformReportDO::getErrorFileUrl, reqVO.getErrorFileUrl())
                .eqIfPresent(PlatformReportDO::getTemplateStatus, reqVO.getTemplateStatus())
                .eqIfPresent(PlatformReportDO::getFilePreviewUrl, reqVO.getFilePreviewUrl())
                .eqIfPresent(PlatformReportDO::getReuploadCount, reqVO.getReuploadCount())
                .betweenIfPresent(PlatformReportDO::getLastReuploadTime, reqVO.getLastReuploadTime())
                .eqIfPresent(PlatformReportDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(PlatformReportDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PlatformReportDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PlatformReportDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PlatformReportDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PlatformReportDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlatformReportDO::getId));
    }

}