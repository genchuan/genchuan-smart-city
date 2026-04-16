package cn.iocoder.yudao.module.inspectop.dal.mysql.spacemonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.spacemonitor.SpaceMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 车位状态监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface SpaceMonitorMapper extends BaseMapperX<SpaceMonitorDO> {

    default PageResult<SpaceMonitorDO> selectPage(SpaceMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpaceMonitorDO>()
                .eqIfPresent(SpaceMonitorDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(SpaceMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(SpaceMonitorDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(SpaceMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(SpaceMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .betweenIfPresent(SpaceMonitorDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(SpaceMonitorDO::getAlarmRemark, reqVO.getAlarmRemark())
                .eqIfPresent(SpaceMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(SpaceMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(SpaceMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(SpaceMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(SpaceMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(SpaceMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(SpaceMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(SpaceMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(SpaceMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(SpaceMonitorDO::getId));
    }

    /**
     * 【新增方法】关联查询分页方法
     * 使用自定义SQL进行关联查询，返回包含车位编号和场站名称的结果
     *
     * @param page 分页参数
     * @param reqVO 查询条件
     * @return 包含关联信息的分页结果
     */
    List<SpaceMonitorRespVO> selectPageWithJoin(@Param("page") com.baomidou.mybatisplus.extension.plugins.pagination.Page<SpaceMonitorRespVO> page,
                                                      @Param("reqVO") SpaceMonitorPageReqVO reqVO);
}