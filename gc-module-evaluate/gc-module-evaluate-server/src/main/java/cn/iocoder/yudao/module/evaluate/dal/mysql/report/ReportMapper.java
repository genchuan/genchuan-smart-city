package cn.iocoder.yudao.module.evaluate.dal.mysql.report;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.report.ReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价报告 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ReportMapper extends BaseMapperX<ReportDO> {

    default PageResult<ReportDO> selectPage(ReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReportDO>()
                .eqIfPresent(ReportDO::getReportId, reqVO.getReportId())
                .eqIfPresent(ReportDO::getCode, reqVO.getCode())
                .eqIfPresent(ReportDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(ReportDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(ReportDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ReportDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(ReportDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(ReportDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(ReportDO::getCreateType, reqVO.getCreateType())
                .eqIfPresent(ReportDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(ReportDO::getDownloadCount, reqVO.getDownloadCount())
                .betweenIfPresent(ReportDO::getLatestDownloadTime, reqVO.getLatestDownloadTime())
                .eqIfPresent(ReportDO::getEditCount, reqVO.getEditCount())
                .betweenIfPresent(ReportDO::getStartCreateTime, reqVO.getStartCreateTime())
                .eqIfPresent(ReportDO::getCreateProgress, reqVO.getCreateProgress())
                .eqIfPresent(ReportDO::getProcessNode, reqVO.getProcessNode())
                .betweenIfPresent(ReportDO::getExpectCompleteTime, reqVO.getExpectCompleteTime())
                .eqIfPresent(ReportDO::getDataSyncStatus, reqVO.getDataSyncStatus())
                .eqIfPresent(ReportDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(ReportDO::getDataCheckResult, reqVO.getDataCheckResult())
                .eqIfPresent(ReportDO::getEditStatus, reqVO.getEditStatus())
                .eqIfPresent(ReportDO::getRecreateCount, reqVO.getRecreateCount())
                .betweenIfPresent(ReportDO::getLatestRecreateTime, reqVO.getLatestRecreateTime())
                .eqIfPresent(ReportDO::getPreviewCount, reqVO.getPreviewCount())
                .eqIfPresent(ReportDO::getNeedEditChapter, reqVO.getNeedEditChapter())
                .eqIfPresent(ReportDO::getEditProgress, reqVO.getEditProgress())
                .eqIfPresent(ReportDO::getEditedChapterNum, reqVO.getEditedChapterNum())
                .eqIfPresent(ReportDO::getTotalNeedChapterNum, reqVO.getTotalNeedChapterNum())
                .eqIfPresent(ReportDO::getAttachStatus, reqVO.getAttachStatus())
                .betweenIfPresent(ReportDO::getLatestEditTime, reqVO.getLatestEditTime())
                .betweenIfPresent(ReportDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(ReportDO::getDistDeptNum, reqVO.getDistDeptNum())
                .eqIfPresent(ReportDO::getArchiveStatus, reqVO.getArchiveStatus())
                .eqIfPresent(ReportDO::getEditRecordNum, reqVO.getEditRecordNum())
                .eqIfPresent(ReportDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ReportDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ReportDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ReportDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ReportDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ReportDO::getId));
    }

}