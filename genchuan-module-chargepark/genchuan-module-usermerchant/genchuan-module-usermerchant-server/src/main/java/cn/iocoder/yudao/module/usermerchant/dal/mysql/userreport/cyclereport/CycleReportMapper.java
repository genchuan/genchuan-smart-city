package cn.iocoder.yudao.module.usermerchant.dal.mysql.userreport.cyclereport;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周期报表存储 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .betweenIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
                .betweenIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
                .orderByDesc(CycleReportDO::getId));
    }

}