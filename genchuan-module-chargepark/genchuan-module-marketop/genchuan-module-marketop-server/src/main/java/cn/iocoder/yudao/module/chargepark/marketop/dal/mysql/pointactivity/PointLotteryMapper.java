package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo.CycleReportDrillJoinUserCountRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface PointLotteryMapper extends BaseMapperX<PointLotteryDO> {

    default PageResult<PointLotteryDO> selectPage(PointLotteryPageReqVO reqVO) {
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<PointLotteryDO>()
                .likeIfPresent(PointLotteryDO::getNo, reqVO.getNo())
                .eqIfPresent(PointLotteryDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PointLotteryDO::getPrizeId, reqVO.getPrizeId())
                .eqIfPresent(PointLotteryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PointLotteryDO::getSyncStatus, reqVO.getSyncStatus())
                .eqIfPresent(PointLotteryDO::getSenderId, reqVO.getSenderId())
                .likeIfPresent(PointLotteryDO::getCheckResult, reqVO.getCheckResult())
                .betweenIfPresent(PointLotteryDO::getSendTime, reqVO.getSendTime())
                .orderByDesc(PointLotteryDO::getId);

        if (reqVO.getStartTime() != null) {
            wrapper.ge(PointLotteryDO::getLotteryTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()));
        }
        if (reqVO.getEndTime() != null) {
            wrapper.le(PointLotteryDO::getLotteryTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

    @Select("SELECT DATE(lottery_time) AS date, COUNT(*) AS count " +
            "FROM point_lottery " +
            "WHERE lottery_time >= #{startTime} " +
            "GROUP BY DATE(lottery_time) " +
            "ORDER BY DATE(lottery_time) ASC")
    List<Map<String, Object>> selectCountByDay(LocalDateTime startTime);

    @Select("SELECT COUNT(*) FROM point_lottery")
    Long selectTotalCount();

    @Select("SELECT COUNT(*) FROM point_lottery WHERE prize_id = 0")
    Long selectWinCount();

    IPage<PointLotteryRespVO> selectPageJoin(Page<?> page, @Param("reqVO") PointLotteryPageReqVO reqVO);

    PointLotteryRespVO selectByIdJoin(@Param("id") Long id);

    IPage<CycleReportDrillJoinUserCountRespVO> selectPageDrillJoinUserCount(Page<?> page,
                                                                            @Param("statStartTime") LocalDateTime statStartTime,
                                                                            @Param("statEndTime") LocalDateTime statEndTime);

}
