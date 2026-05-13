package cn.iocoder.yudao.module.ordertrade.dal.mysql.orderreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.orderreport.CycleReportDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .eqIfPresent(CycleReportDO::getGenerateStatus, reqVO.getGenerateStatus())
                .likeIfPresent(CycleReportDO::getOperator, reqVO.getOperator())
                .geIfPresent(CycleReportDO::getCreateTime, reqVO.getCreateTimeStart())
                .leIfPresent(CycleReportDO::getCreateTime, reqVO.getCreateTimeEnd())
                .orderByDesc(CycleReportDO::getId));
    }
}
