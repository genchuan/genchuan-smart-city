package cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 处置工单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DisposalOrderMapper extends BaseMapperX<DisposalOrderDO> {

    default PageResult<DisposalOrderDO> selectPage(DisposalOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DisposalOrderDO>()
                .eqIfPresent(DisposalOrderDO::getWarnId, reqVO.getWarnId())
                .eqIfPresent(DisposalOrderDO::getCoverId, reqVO.getCoverId())
                .eqIfPresent(DisposalOrderDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(DisposalOrderDO::getRiskLevelId, reqVO.getRiskLevelId())
                .eqIfPresent(DisposalOrderDO::getAssignStaffId, reqVO.getAssignStaffId())
                .betweenIfPresent(DisposalOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DisposalOrderDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(DisposalOrderDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(DisposalOrderDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(DisposalOrderDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DisposalOrderDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DisposalOrderDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DisposalOrderDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(DisposalOrderDO::getId));
    }

    /**
     * 查询工单列表
     *
     * @param reqVO 查询条件
     * @return 工单列表
     */
   IPage<ManholeCoverRepairOrderPageRespVO> selectRepairOrderPage(
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<ManholeCoverRepairOrderPageRespVO> page,
            @Param("reqVO") ManholeCoverRepairOrderPageReqVO reqVO
    );

}