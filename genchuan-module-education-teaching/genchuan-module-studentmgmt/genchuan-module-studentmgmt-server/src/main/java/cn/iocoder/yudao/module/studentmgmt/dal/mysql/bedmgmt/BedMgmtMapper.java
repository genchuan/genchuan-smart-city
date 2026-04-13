package cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;

/**
 * 床位管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BedMgmtMapper extends BaseMapperX<BedMgmtDO> {

    default PageResult<BedMgmtDO> selectPage(BedMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BedMgmtDO>()
                .eqIfPresent(BedMgmtDO::getBuilding, reqVO.getBuilding())
                .eqIfPresent(BedMgmtDO::getFloor, reqVO.getFloor())
                .eqIfPresent(BedMgmtDO::getRoomNum, reqVO.getRoomNum())
                .eqIfPresent(BedMgmtDO::getBedNum, reqVO.getBedNum())
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

}