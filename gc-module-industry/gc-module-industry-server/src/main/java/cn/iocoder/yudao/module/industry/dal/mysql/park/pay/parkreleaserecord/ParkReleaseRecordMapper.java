package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkreleaserecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreleaserecord.vo.ParkReleaseRecordPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreleaserecord.ParkReleaseRecordDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 放行记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkReleaseRecordMapper extends BaseMapperX<ParkReleaseRecordDO> {

    default PageResult<ParkReleaseRecordDO> selectPage(ParkReleaseRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkReleaseRecordDO>()
                .eqIfPresent(ParkReleaseRecordDO::getPayId, reqVO.getPayId())
                .eqIfPresent(ParkReleaseRecordDO::getReleaseCmdId, reqVO.getReleaseCmdId())
                .eqIfPresent(ParkReleaseRecordDO::getReleaseStatus, reqVO.getReleaseStatus())
                .betweenIfPresent(ParkReleaseRecordDO::getReleaseTime, reqVO.getReleaseTime())
                .eqIfPresent(ParkReleaseRecordDO::getGateId, reqVO.getGateId())
                .likeIfPresent(ParkReleaseRecordDO::getOperatorName, reqVO.getOperatorName())
                .betweenIfPresent(ParkReleaseRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkReleaseRecordDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkReleaseRecordDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkReleaseRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkReleaseRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkReleaseRecordDO::getId));
    }

}
