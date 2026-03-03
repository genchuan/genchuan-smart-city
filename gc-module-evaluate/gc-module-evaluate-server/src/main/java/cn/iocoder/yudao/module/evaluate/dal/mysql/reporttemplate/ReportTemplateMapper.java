package cn.iocoder.yudao.module.evaluate.dal.mysql.reporttemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.reporttemplate.ReportTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报告模板 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ReportTemplateMapper extends BaseMapperX<ReportTemplateDO> {

    default PageResult<ReportTemplateDO> selectPage(ReportTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReportTemplateDO>()
                .eqIfPresent(ReportTemplateDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(ReportTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(ReportTemplateDO::getName, reqVO.getName())
                .eqIfPresent(ReportTemplateDO::getTaskTypeId, reqVO.getTaskTypeId())
                .eqIfPresent(ReportTemplateDO::getVersion, reqVO.getVersion())
                .eqIfPresent(ReportTemplateDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(ReportTemplateDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(ReportTemplateDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(ReportTemplateDO::getUseCount, reqVO.getUseCount())
                .betweenIfPresent(ReportTemplateDO::getLatestUseTime, reqVO.getLatestUseTime())
                .eqIfPresent(ReportTemplateDO::getMapRuleNum, reqVO.getMapRuleNum())
                .eqIfPresent(ReportTemplateDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(ReportTemplateDO::getFileFormat, reqVO.getFileFormat())
                .eqIfPresent(ReportTemplateDO::getVersionLog, reqVO.getVersionLog())
                .eqIfPresent(ReportTemplateDO::getUseRate, reqVO.getUseRate())
                .eqIfPresent(ReportTemplateDO::getMapCompleteRate, reqVO.getMapCompleteRate())
                .betweenIfPresent(ReportTemplateDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(ReportTemplateDO::getDeptDist, reqVO.getDeptDist())
                .eqIfPresent(ReportTemplateDO::getStopBy, reqVO.getStopBy())
                .betweenIfPresent(ReportTemplateDO::getStopTime, reqVO.getStopTime())
                .eqIfPresent(ReportTemplateDO::getStopReason, reqVO.getStopReason())
                .eqIfPresent(ReportTemplateDO::getStopHour, reqVO.getStopHour())
                .eqIfPresent(ReportTemplateDO::getFileStatus, reqVO.getFileStatus())
                .eqIfPresent(ReportTemplateDO::getMapValid, reqVO.getMapValid())
                .eqIfPresent(ReportTemplateDO::getLatestVersionLog, reqVO.getLatestVersionLog())
                .eqIfPresent(ReportTemplateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ReportTemplateDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ReportTemplateDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ReportTemplateDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ReportTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ReportTemplateDO::getId));
    }

}