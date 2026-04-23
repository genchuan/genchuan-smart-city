package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.decisionanalysis.stationopreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops.StationOpReportChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.decisionanalysis.stationopreport.StationOpReportDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Mapper
public interface StationOpReportMapper extends BaseMapperX<StationOpReportDO> {

    StationOpReportDO selectReportById(@Param("id") Long id);

//    PageResult<StationOpReportDO> selectReportPage(StationOpReportPageReqVO reqVO);

    StationOpReportChartRespVO.CardDataVO selectCardData(@Param("reportId") Long reportId);

    List<StationOpReportChartRespVO.OperateLineVO> selectOperateLine(@Param("reportId") Long reportId);

    List<StationOpReportChartRespVO.AreaBarVO> selectAreaBar(@Param("reportId") Long reportId);

//    Page<StationOpReportDO> selectReportPage(Page<StationOpReportDO> mpPage);
    Page<StationOpReportDO> selectReportPage(
            @Param("query") StationOpReportPageReqVO pageReqVO,
            Page<StationOpReportDO> page
    );
    /**
     * 分页查询报表（统计SQL）
     */
//    IPage<Map<String, Object>> selectReportPage(IPage<Map<String, Object>> page,
//                                                @Param("query") StationOpReportPageReqVO reqVO);
}
