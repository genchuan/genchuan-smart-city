package cn.iocoder.yudao.module.inspectop.dal.mysql.scheduleview;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.ScheduleViewChartData;
import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 排班查看 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ScheduleViewMapper extends BaseMapperX<ScheduleViewDO> {

    default PageResult<ScheduleViewDO> selectPage(ScheduleViewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScheduleViewDO>()
                .eqIfPresent(ScheduleViewDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ScheduleViewDO::getScheduleDate, reqVO.getScheduleDate())
                .eqIfPresent(ScheduleViewDO::getShiftType, reqVO.getShiftType())
                .eqIfPresent(ScheduleViewDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ScheduleViewDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ScheduleViewDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ScheduleViewDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ScheduleViewDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ScheduleViewDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ScheduleViewDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ScheduleViewDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询人员姓名
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含巡检人员姓名的分页结果
     */
    Page<ScheduleViewRespVO> selectPageWithJoin(@Param("page") Page<ScheduleViewRespVO> page,
                                                @Param("reqVO") ScheduleViewPageReqVO reqVO);

    /**
     * 查询排班统计图表数据
     * 如果reqVO.month为空，则统计所有数据
     *
     * @param reqVO 查询参数
     * @return 排班统计图表数据
     */
    @MapKey("userId")
    Map<Long, ScheduleViewChartData> selectScheduleViewChartData(@Param("reqVO") ScheduleViewChartReqVO reqVO);

    /**
     * 查询排班统计卡片数据
     * 如果reqVO.month为空，则统计所有数据
     *
     * @param reqVO 查询参数
     * @return 排班统计卡片数据
     */
    ScheduleViewChartCardData selectScheduleViewCardData(@Param("reqVO") ScheduleViewChartReqVO reqVO);

}