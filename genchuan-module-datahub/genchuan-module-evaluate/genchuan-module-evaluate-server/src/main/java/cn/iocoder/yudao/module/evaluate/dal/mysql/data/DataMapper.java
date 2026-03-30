package cn.iocoder.yudao.module.evaluate.dal.mysql.data;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.data.DataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 上报数据 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DataMapper extends BaseMapperX<DataDO> {

    default PageResult<DataDO> selectPage(DataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DataDO>()
                .eqIfPresent(DataDO::getReportId, reqVO.getReportId())
                .eqIfPresent(DataDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(DataDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(DataDO::getIndexId, reqVO.getIndexId())
                .eqIfPresent(DataDO::getDataValue, reqVO.getDataValue())
                .betweenIfPresent(DataDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(DataDO::getReportBy, reqVO.getReportBy())
                .eqIfPresent(DataDO::getDataStatusId, reqVO.getDataStatusId())
                .eqIfPresent(DataDO::getVerifyResultId, reqVO.getVerifyResultId())
                .eqIfPresent(DataDO::getErrorReason, reqVO.getErrorReason())
                .betweenIfPresent(DataDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(DataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DataDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DataDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DataDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(DataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DataDO::getId));
    }

}