package cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 窨井盖设施 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManholeCoverMapper extends BaseMapperX<ManholeCoverDO> {

    default PageResult<ManholeCoverDO> selectPage(ManholeCoverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManholeCoverDO>()
                .eqIfPresent(ManholeCoverDO::getCoverNo, reqVO.getCoverNo())
                .eqIfPresent(ManholeCoverDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(ManholeCoverDO::getCoverType, reqVO.getCoverType())
                .eqIfPresent(ManholeCoverDO::getSpecification, reqVO.getSpecification())
                .betweenIfPresent(ManholeCoverDO::getInstallTime, reqVO.getInstallTime())
                .eqIfPresent(ManholeCoverDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ManholeCoverDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ManholeCoverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ManholeCoverDO::getId));
    }

}