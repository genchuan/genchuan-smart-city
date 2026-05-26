package cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt;

import java.math.BigDecimal;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.ViolateMgmtPageRespVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang3.StringUtils;
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


    default PageResult<BedMgmtRespVO> selectJoinPage(BedMgmtPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<BedMgmtRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<BedMgmtDO> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(BedMgmtDO.class);
        wrapper.selectAs(StudentInfoDO::getName, ViolateMgmtPageRespVO::getStudentName);
        wrapper.leftJoin(StudentInfoDO.class, StudentInfoDO::getId, BedMgmtDO::getStudentId);

        if (null != reqVO.getStudentId()) {
            wrapper.eq(BedMgmtDO::getStudentId, reqVO.getStudentId());
        }
        if (StringUtils.isNotBlank(reqVO.getRoomNum())) {
            wrapper.like(BedMgmtDO::getRoomNum, reqVO.getRoomNum());
        }
        if (StringUtils.isNotBlank(reqVO.getBuilding())) {
            wrapper.like(BedMgmtDO::getBuilding, reqVO.getBuilding());
        }
        if (StringUtils.isNotBlank(reqVO.getBedNum())) {
            wrapper.like(BedMgmtDO::getBedNum, reqVO.getBedNum());
        }

        if (StringUtils.isNotBlank(reqVO.getStatus())) {
            wrapper.eq(BedMgmtDO::getStatus, reqVO.getStatus());
        }
        if (StringUtils.isNotBlank(reqVO.getRemark())) {
            wrapper.like(BedMgmtDO::getRemark, reqVO.getRemark());
        }
        wrapper.orderByDesc(BedMgmtDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<BedMgmtRespVO> resultPage = selectJoinPage(page, BedMgmtRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    Integer selectTotalBed(@Param("building") String building, @Param("status") String status);

    BigDecimal selectUsageRate(@Param("building") String building);

    List<String> selectDistinctBuilding();

    JSONObject selectAssignAndAdjustCount();

    JSONObject selectTodayAssignAndAdjustCount();

    List<JSONObject> select7dayAssignCount();
    List<JSONObject> select7dayAdjustCount();
}