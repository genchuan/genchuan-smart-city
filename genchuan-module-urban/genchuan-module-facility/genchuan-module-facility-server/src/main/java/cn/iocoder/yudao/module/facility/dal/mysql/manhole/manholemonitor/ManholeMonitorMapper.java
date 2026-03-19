package cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 窨井盖监测 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManholeMonitorMapper extends BaseMapperX<ManholeMonitorDO> {

    default PageResult<ManholeMonitorDO> selectPage(ManholeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManholeMonitorDO>()
                .eqIfPresent(ManholeMonitorDO::getCoverId, reqVO.getCoverId())
                .eqIfPresent(ManholeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(ManholeMonitorDO::getStaffId, reqVO.getStaffId())
                .eqIfPresent(ManholeMonitorDO::getOpenStatusId, reqVO.getOpenStatusId())
                .eqIfPresent(ManholeMonitorDO::getTiltAngle, reqVO.getTiltAngle())
                .eqIfPresent(ManholeMonitorDO::getVibrationData, reqVO.getVibrationData())
                .eqIfPresent(ManholeMonitorDO::getRiskLevelId, reqVO.getRiskLevelId())
                .eqIfPresent(ManholeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .betweenIfPresent(ManholeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ManholeMonitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ManholeMonitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ManholeMonitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ManholeMonitorDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ManholeMonitorDO::getId));
    }

    /**
     * 查询窨井盖监测列表（支持所有筛选条件）
     *
     * @param coverNo 井盖编号
     * @param roadName 路段名称
     * @param statusName 开合状态
     * @param onlineStatus 设备在线状态
     * @param monitorStatus 监测状态
     * @param riskLevel 安全风险等级
     * @param abnormalVibrationFlag 异常振动标识（1=是/0=否）
     * @return 监测列表
     */
    List<ManholeMonitorVO> selectManholeMonitorList(
            @Param("coverNo") String coverNo,
            @Param("roadName") String roadName,
            @Param("statusName") String statusName,
            @Param("onlineStatus") String onlineStatus,
            @Param("monitorStatus") String monitorStatus,
            @Param("riskLevel") String riskLevel,
            @Param("abnormalVibrationFlag") Integer abnormalVibrationFlag
    );

    /**
     * 按井盖编号查询详情
     * @param coverNo 井盖编号
     * @return 窨井盖详情
     */
    ManholeMonitorVO selectManholeDetailByCoverNo(@Param("coverNo") String coverNo);

    /**
     * 批量更新窨井盖监测状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param monitorStatus 监测状态
     * @return 更新成功的记录数
     */
    Integer batchUpdateMonitorStatus(@Param("coverIds") List<Long> coverIds,
                                     @Param("monitorStatus") String monitorStatus);

    /**
     * 批量更新关联设备状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param onlineStatus 设备在线状态
     * @return 更新成功的记录数
     */
    Integer batchUpdateDeviceStatus(@Param("coverIds") List<Long> coverIds,
                                    @Param("onlineStatus") String onlineStatus);

}