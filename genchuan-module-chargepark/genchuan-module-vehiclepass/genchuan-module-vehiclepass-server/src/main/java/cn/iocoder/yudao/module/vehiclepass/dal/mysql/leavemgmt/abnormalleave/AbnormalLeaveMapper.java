package cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.abnormalleave;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeavePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.abnormalleave.AbnormalLeaveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 异常离场 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AbnormalLeaveMapper extends BaseMapperX<AbnormalLeaveDO> {

    default PageResult<AbnormalLeaveDO> selectPage(AbnormalLeavePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AbnormalLeaveDO>()
                .likeIfPresent(AbnormalLeaveDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(AbnormalLeaveDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(AbnormalLeaveDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AbnormalLeaveDO::getStationId, reqVO.getStationId())
                .likeIfPresent(AbnormalLeaveDO::getStationName, reqVO.getStationName())
                .eqIfPresent(AbnormalLeaveDO::getHandleUserId, reqVO.getHandleUserId())
                .likeIfPresent(AbnormalLeaveDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AbnormalLeaveDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AbnormalLeaveDO::getReserve2, reqVO.getReserve2())
                .orderByDesc(AbnormalLeaveDO::getId));
    }

    IPage<AbnormalLeaveRespVO> selectPageJoin(Page<?> page, @Param("reqVO") AbnormalLeavePageReqVO reqVO);

    AbnormalLeaveRespVO selectByIdJoinStation(@Param("id") Long id);

    /**
     * 统计异常离场趋势
     */
    List<AbnormalLeaveChartRespVO.AbnormalLeaveTrend> selectChartTrend(@Param("reqVO") AbnormalLeaveChartReqVO reqVO);

    /**
     * 统计各场站异常数
     */
    List<AbnormalLeaveChartRespVO.StationAbnormalCount> selectStationAbnormalCount(@Param("reqVO") AbnormalLeaveChartReqVO reqVO);

    /**
     * 统计待处置数量
     */
    Long selectWaitHandleCount(@Param("reqVO") AbnormalLeaveChartReqVO reqVO);

    /**
     * 统计处置完成率
     */
    Double selectHandleCompleteRate(@Param("reqVO") AbnormalLeaveChartReqVO reqVO);

}