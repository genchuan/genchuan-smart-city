package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.servicereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.servicereport.CycleReportDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * 周期报表 Mapper
 */
@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .eqIfPresent(CycleReportDO::getGenerateStatus, reqVO.getGenerateStatus())
                .geIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
                .leIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
                .orderByDesc(CycleReportDO::getGenerateTime)
                .orderByDesc(CycleReportDO::getId));
    }

    /** 仅按时间范围(窗口完整落在 [start,end] 内) */
    default PageResult<CycleReportDO> selectPageByTimeRange(LocalDateTime start, LocalDateTime end,
                                                            Integer pageNo, Integer pageSize) {
        CycleReportPageReqVO req = new CycleReportPageReqVO();
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);
        return selectPage(req, new LambdaQueryWrapperX<CycleReportDO>()
                .geIfPresent(CycleReportDO::getStatStartTime, start)
                .leIfPresent(CycleReportDO::getStatEndTime, end)
                .orderByDesc(CycleReportDO::getGenerateTime)
                .orderByDesc(CycleReportDO::getId));
    }

    /**
     * 查重工具(当前未被 Service 层调用):保留以供后续排障/管理员查重场景。
     * MP 逻辑删除会自动追加 deleted=0。
     */
    default CycleReportDO selectByCycleAndRange(String reportCycle,
                                                 LocalDateTime statStart, LocalDateTime statEnd) {
        return selectOne(new LambdaQueryWrapperX<CycleReportDO>()
                .eq(CycleReportDO::getReportCycle, reportCycle)
                .eq(CycleReportDO::getStatStartTime, statStart)
                .eq(CycleReportDO::getStatEndTime, statEnd));
    }

}
