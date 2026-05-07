package cn.iocoder.yudao.module.studentmgmt.dal.mysql.parentreply;

import java.time.LocalDateTime;
import java.util.List;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.parentreply.ParentReplyDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo.*;

/**
 * 家长回复 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ParentReplyMapper extends BaseMapperX<ParentReplyDO> {

    default PageResult<ParentReplyDO> selectPage(ParentReplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParentReplyDO>()
                .eqIfPresent(ParentReplyDO::getCommunicateId, reqVO.getCommunicateId())
                .eqIfPresent(ParentReplyDO::getStudentId, reqVO.getStudentId())
                .likeIfPresent(ParentReplyDO::getStudentName, reqVO.getStudentName())
                .eqIfPresent(ParentReplyDO::getParentReplyContent, reqVO.getParentReplyContent())
                .betweenIfPresent(ParentReplyDO::getParentReplyTime, reqVO.getParentReplyTime())
                .eqIfPresent(ParentReplyDO::getTeacherReplyContent, reqVO.getTeacherReplyContent())
                .betweenIfPresent(ParentReplyDO::getTeacherReplyTime, reqVO.getTeacherReplyTime())
                .eqIfPresent(ParentReplyDO::getReadStatus, reqVO.getReadStatus())
                .eqIfPresent(ParentReplyDO::getReplyStatus, reqVO.getReplyStatus())
                .eqIfPresent(ParentReplyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParentReplyDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ParentReplyDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ParentReplyDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ParentReplyDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ParentReplyDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ParentReplyDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ParentReplyDO::getId));
    }

    ParentReplyChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String status, String repliedStatus);

    List<ChartTrendVO> select7dayTrendCount();

    List<JSONObject> selectStudentReplyCount(LocalDateTime startTime, LocalDateTime endTime);

    List<ChartCountVO> selectClassReplyCount(LocalDateTime startTime, LocalDateTime endTime);

    List<JSONObject> selectReplyTimeDistribute(LocalDateTime startTime, LocalDateTime endTime);
}