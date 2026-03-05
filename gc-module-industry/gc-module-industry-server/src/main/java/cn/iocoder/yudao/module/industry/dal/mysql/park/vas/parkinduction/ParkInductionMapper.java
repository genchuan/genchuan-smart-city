package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkinduction;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkinduction.ParkInductionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 停车诱导配置 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkInductionMapper extends BaseMapperX<ParkInductionDO> {

    default PageResult<ParkInductionDO> selectPage(ParkInductionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkInductionDO>()
                .likeIfPresent(ParkInductionDO::getInductionName, reqVO.getInductionName())
                .eqIfPresent(ParkInductionDO::getRegion, reqVO.getRegion())
                .eqIfPresent(ParkInductionDO::getRelatedLotIds, reqVO.getRelatedLotIds())
                .eqIfPresent(ParkInductionDO::getPushStrategy, reqVO.getPushStrategy())
                .eqIfPresent(ParkInductionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkInductionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkInductionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkInductionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkInductionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkInductionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkInductionDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkInductionDO::getId));
    }

}
