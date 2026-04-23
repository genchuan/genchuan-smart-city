package cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.leaverecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 离场记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LeaveRecordMapper extends BaseMapperX<LeaveRecordDO> {

    default PageResult<LeaveRecordDO> selectPage(LeaveRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeaveRecordDO>()
                .eqIfPresent(LeaveRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(LeaveRecordDO::getParkDuration, reqVO.getParkDuration())
                .eqIfPresent(LeaveRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(LeaveRecordDO::getStationId, reqVO.getStationId())
                .eqIfPresent(LeaveRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(LeaveRecordDO::getProofImage, reqVO.getProofImage())
                .eqIfPresent(LeaveRecordDO::getIsCorrected, reqVO.getIsCorrected())
                .eqIfPresent(LeaveRecordDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(LeaveRecordDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(LeaveRecordDO::getCreator, reqVO.getCreator())
                .eqIfPresent(LeaveRecordDO::getUpdater, reqVO.getUpdater())
                .orderByDesc(LeaveRecordDO::getId));
    }

    IPage<LeaveRecordRespVO> selectPageJoin(Page<?> page, @Param("reqVO") LeaveRecordPageReqVO reqVO);

}