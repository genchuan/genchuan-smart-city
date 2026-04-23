package cn.iocoder.yudao.module.inspectop.dal.mysql.handoverlog;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog.HandoverLogDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 交接日志 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface HandoverLogMapper extends BaseMapperX<HandoverLogDO> {

    default PageResult<HandoverLogDO> selectPage(HandoverLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HandoverLogDO>()
                .eqIfPresent(HandoverLogDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(HandoverLogDO::getHandoverDate, reqVO.getHandoverDate())
                .eqIfPresent(HandoverLogDO::getContent, reqVO.getContent())
                .eqIfPresent(HandoverLogDO::getStatus, reqVO.getStatus())
                .eqIfPresent(HandoverLogDO::getConfirmUserId, reqVO.getConfirmUserId())
                .betweenIfPresent(HandoverLogDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(HandoverLogDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(HandoverLogDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(HandoverLogDO::getCreator, reqVO.getCreator())
                .eqIfPresent(HandoverLogDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(HandoverLogDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(HandoverLogDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(HandoverLogDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询人员姓名
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含巡检人员姓名的分页结果
     */
    Page<HandoverLogRespVO> selectPageWithJoin(@Param("page") Page<HandoverLogRespVO> page,
                                               @Param("reqVO") HandoverLogPageReqVO reqVO);

    // 在 HandoverLogMapper.java 中添加以下方法
    /**
     * 查询交接日志趋势统计数据
     * 按天统计日志数量
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 趋势统计数据
     */
    List<Map<String, Object>> selectTrendStatistics(@Param("startTime") LocalDateTime startTime,
                                                    @Param("endTime") LocalDateTime endTime);

    /**
     * 查询交接日志卡片统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 卡片统计数据
     */
    Map<String, Object> selectCardStatistics(@Param("startTime") LocalDateTime startTime,
                                             @Param("endTime") LocalDateTime endTime);

}