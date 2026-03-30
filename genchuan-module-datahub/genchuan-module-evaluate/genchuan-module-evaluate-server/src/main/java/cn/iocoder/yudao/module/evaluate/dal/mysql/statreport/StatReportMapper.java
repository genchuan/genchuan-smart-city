package cn.iocoder.yudao.module.evaluate.dal.mysql.statreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.statreport.StatReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 统计分析报 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StatReportMapper extends BaseMapperX<StatReportDO> {

    default PageResult<StatReportDO> selectPage(StatReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StatReportDO>()
                .eqIfPresent(StatReportDO::getReportId, reqVO.getReportId())
                .eqIfPresent(StatReportDO::getCode, reqVO.getCode())
                .likeIfPresent(StatReportDO::getName, reqVO.getName())
                .eqIfPresent(StatReportDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(StatReportDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(StatReportDO::getDimension, reqVO.getDimension())
                .eqIfPresent(StatReportDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StatReportDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(StatReportDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(StatReportDO::getExportTime, reqVO.getExportTime())
                .eqIfPresent(StatReportDO::getExportBy, reqVO.getExportBy())
                .eqIfPresent(StatReportDO::getFormat, reqVO.getFormat())
                .betweenIfPresent(StatReportDO::getDataUpdateTime, reqVO.getDataUpdateTime())
                .betweenIfPresent(StatReportDO::getCostTime, reqVO.getCostTime())
                .eqIfPresent(StatReportDO::getPreviewCount, reqVO.getPreviewCount())
                .betweenIfPresent(StatReportDO::getLatestPreviewTime, reqVO.getLatestPreviewTime())
                .eqIfPresent(StatReportDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(StatReportDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(StatReportDO::getExportCount, reqVO.getExportCount())
                .betweenIfPresent(StatReportDO::getLatestExportTime, reqVO.getLatestExportTime())
                .eqIfPresent(StatReportDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StatReportDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(StatReportDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(StatReportDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(StatReportDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StatReportDO::getId));
    }

}