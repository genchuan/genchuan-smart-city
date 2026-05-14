package cn.iocoder.yudao.module.studentmgmt.dal.mysql.promotemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.DormAssignChartRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt.PromoteMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.*;

/**
 * 宣传管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PromoteMgmtMapper extends BaseMapperX<PromoteMgmtDO> {

    default PageResult<PromoteMgmtDO> selectPage(PromoteMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PromoteMgmtDO>()
                .likeIfPresent(PromoteMgmtDO::getTaskName, reqVO.getTaskName())
                .likeIfPresent(PromoteMgmtDO::getSite, reqVO.getSite())
                .eqIfPresent(PromoteMgmtDO::getPromoteNum, reqVO.getPromoteNum())
                .eqIfPresent(PromoteMgmtDO::getIntentNum, reqVO.getIntentNum())
                .eqIfPresent(PromoteMgmtDO::getExecuteUser, reqVO.getExecuteUser())
                .betweenIfPresent(PromoteMgmtDO::getExecuteTime, reqVO.getExecuteTime())
                .eqIfPresent(PromoteMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PromoteMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PromoteMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PromoteMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(PromoteMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PromoteMgmtDO::getId));
    }

    PromoteMgmtChartRespVO selectTotalCount(Integer year);

    List<JSONObject> selectDateCountList(Integer year);

    List<JSONObject> selectPromoteCount(Integer year);
}