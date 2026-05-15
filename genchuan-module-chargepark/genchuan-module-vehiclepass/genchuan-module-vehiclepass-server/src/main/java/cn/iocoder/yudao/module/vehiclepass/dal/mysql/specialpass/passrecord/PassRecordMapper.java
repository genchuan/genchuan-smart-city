package cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.passrecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord.PassRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 放行记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PassRecordMapper extends BaseMapperX<PassRecordDO> {

    default PageResult<PassRecordDO> selectPage(PassRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PassRecordDO>()
                .eqIfPresent(PassRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(PassRecordDO::getPassReason, reqVO.getPassReason())
                .eqIfPresent(PassRecordDO::getImageUrl, reqVO.getImageUrl())
                .eqIfPresent(PassRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PassRecordDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PassRecordDO::getOperatorId, reqVO.getOperatorId())
                .eqIfPresent(PassRecordDO::getCheckResult, reqVO.getCheckResult())
                .eqIfPresent(PassRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PassRecordDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PassRecordDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PassRecordDO::getCreator, reqVO.getCreator())
                .eqIfPresent(PassRecordDO::getUpdater, reqVO.getUpdater())
                .orderByDesc(PassRecordDO::getId));
    }

    IPage<PassRecordRespVO> selectPageJoin(Page<?> page, @Param("reqVO") PassRecordPageReqVO reqVO);

    PassRecordRespVO selectByIdJoinStation(@Param("id") Long id);

    /**
     * 查询放行量趋势（按天统计）
     */
    List<Map<String, Object>> selectPassCountTrend(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询今日放行量和异常放行占比
     */
    Map<String, Object> selectPassStats(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

}