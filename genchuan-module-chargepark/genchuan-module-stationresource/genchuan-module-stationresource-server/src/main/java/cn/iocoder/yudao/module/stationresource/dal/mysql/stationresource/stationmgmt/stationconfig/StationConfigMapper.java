package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.statistics.StationConfigChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationconfig.StationConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 场站配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StationConfigMapper extends BaseMapperX<StationConfigDO> {

    default PageResult<StationConfigDO> selectPage(StationConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StationConfigDO>()
                .eqIfPresent(StationConfigDO::getStationId, reqVO.getStationId())
                .eqIfPresent(StationConfigDO::getType, reqVO.getType())
                .eqIfPresent(StationConfigDO::getContent, reqVO.getContent())
                .eqIfPresent(StationConfigDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(StationConfigDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(StationConfigDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(StationConfigDO::getSyncTime, reqVO.getSyncTime())
                .eqIfPresent(StationConfigDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StationConfigDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StationConfigDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(StationConfigDO::getCreator, reqVO.getCreator())
                .eqIfPresent(StationConfigDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(StationConfigDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(StationConfigDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(StationConfigDO::getId));
    }

    // ========== 图表统计相关 ==========
    /**
     * 按配置类型 type 分组统计数量
     */
//    List<Map<String, Object>> selectTypeGroupCount();
    List<StationConfigChartRespVO.TypePieDTO> selectTypeGroupCount();

    /**
     * 已配置场站数量（去重 station_id）
     */
    Integer selectConfigedStationCount();

    /**
     * 已生效配置数量（status = 已生效）
     */
    Integer selectEnableConfigCount();
}
