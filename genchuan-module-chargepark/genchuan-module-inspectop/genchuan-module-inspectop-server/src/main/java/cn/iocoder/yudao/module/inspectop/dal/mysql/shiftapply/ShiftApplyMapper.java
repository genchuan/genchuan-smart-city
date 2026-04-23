package cn.iocoder.yudao.module.inspectop.dal.mysql.shiftapply;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply.ShiftApplyDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 换班申请 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ShiftApplyMapper extends BaseMapperX<ShiftApplyDO> {

    default PageResult<ShiftApplyDO> selectPage(ShiftApplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShiftApplyDO>()
                .eqIfPresent(ShiftApplyDO::getApplyUserId, reqVO.getApplyUserId())
                .eqIfPresent(ShiftApplyDO::getTargetUserId, reqVO.getTargetUserId())
                .betweenIfPresent(ShiftApplyDO::getOldDate, reqVO.getOldDate())
                .betweenIfPresent(ShiftApplyDO::getNewDate, reqVO.getNewDate())
                .eqIfPresent(ShiftApplyDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ShiftApplyDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(ShiftApplyDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(ShiftApplyDO::getEffectTime, reqVO.getEffectTime())
                .eqIfPresent(ShiftApplyDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ShiftApplyDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ShiftApplyDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ShiftApplyDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ShiftApplyDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ShiftApplyDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ShiftApplyDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询申请人姓名和换班对象姓名
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含申请人姓名和换班对象姓名的分页结果
     */
    Page<ShiftApplyRespVO> selectPageWithJoin(@Param("page") Page<ShiftApplyRespVO> page,
                                              @Param("reqVO") ShiftApplyPageReqVO reqVO);

    /**
     * 查询换班申请趋势统计数据
     * 按天统计申请数量
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 趋势统计数据
     */
    List<Map<String, Object>> selectTrendStatistics(@Param("startTime") LocalDateTime startTime,
                                                    @Param("endTime") LocalDateTime endTime);

    /**
     * 查询换班申请卡片统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 卡片统计数据
     */
    Map<String, Object> selectCardStatistics(@Param("startTime") LocalDateTime startTime,
                                             @Param("endTime") LocalDateTime endTime);

}