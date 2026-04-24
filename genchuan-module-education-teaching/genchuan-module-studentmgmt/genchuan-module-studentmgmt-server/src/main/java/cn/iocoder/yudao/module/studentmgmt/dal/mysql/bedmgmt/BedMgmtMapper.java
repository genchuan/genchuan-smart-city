package cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt;

import java.math.BigDecimal;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 床位管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BedMgmtMapper extends BaseMapperX<BedMgmtDO> {

    default PageResult<BedMgmtDO> selectPage(BedMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BedMgmtDO>()
                .likeIfPresent(BedMgmtDO::getBuilding, reqVO.getBuilding())
                .eqIfPresent(BedMgmtDO::getFloor, reqVO.getFloor())
                .likeIfPresent(BedMgmtDO::getRoomNum, reqVO.getRoomNum())
                .likeIfPresent(BedMgmtDO::getBedNum, reqVO.getBedNum())
                .eqIfPresent(BedMgmtDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(BedMgmtDO::getAssignTime, reqVO.getAssignTime())
                .betweenIfPresent(BedMgmtDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(BedMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(BedMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BedMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(BedMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(BedMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BedMgmtDO::getId));
    }

    Integer selectTotalBed(@Param("building") String building, @Param("status") String status);

    BigDecimal selectUsageRate(@Param("building") String building);

    List<String> selectDistinctBuilding();

    JSONObject selectAssignAndAdjustCount();

    JSONObject selectTodayAssignAndAdjustCount();

    List<JSONObject> select7dayAssignCount();
    List<JSONObject> select7dayAdjustCount();
}