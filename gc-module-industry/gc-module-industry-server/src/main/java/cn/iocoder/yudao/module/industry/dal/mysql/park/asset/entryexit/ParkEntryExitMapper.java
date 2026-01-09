package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.entryexit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.entryexit.ParkEntryExitDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出入口信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkEntryExitMapper extends BaseMapperX<ParkEntryExitDO> {

    default PageResult<ParkEntryExitDO> selectPage(ParkEntryExitPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkEntryExitDO>()
                .eqIfPresent(ParkEntryExitDO::getAssetExtendId, reqVO.getAssetExtendId())
                .eqIfPresent(ParkEntryExitDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkEntryExitDO::getDirection, reqVO.getDirection())
                .eqIfPresent(ParkEntryExitDO::getDeviceIds, reqVO.getDeviceIds())
                .eqIfPresent(ParkEntryExitDO::getPassRuleId, reqVO.getPassRuleId())
                .betweenIfPresent(ParkEntryExitDO::getOpenTime, reqVO.getOpenTime())
                .betweenIfPresent(ParkEntryExitDO::getCloseTime, reqVO.getCloseTime())
                .betweenIfPresent(ParkEntryExitDO::getEntryExitCreateTime, reqVO.getEntryExitCreateTime())
                .betweenIfPresent(ParkEntryExitDO::getEntryExitUpdateTime, reqVO.getEntryExitUpdateTime())
                .eqIfPresent(ParkEntryExitDO::getEntryExitRemark, reqVO.getEntryExitRemark())
                .betweenIfPresent(ParkEntryExitDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkEntryExitDO::getId));
    }

}