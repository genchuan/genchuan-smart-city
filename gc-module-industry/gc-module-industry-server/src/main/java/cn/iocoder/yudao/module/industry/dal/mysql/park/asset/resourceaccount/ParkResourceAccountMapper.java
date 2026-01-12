package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.resourceaccount;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.resourceaccount.ParkResourceAccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源台账 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkResourceAccountMapper extends BaseMapperX<ParkResourceAccountDO> {

    default PageResult<ParkResourceAccountDO> selectPage(ParkResourceAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkResourceAccountDO>()
                .eqIfPresent(ParkResourceAccountDO::getAccountId, reqVO.getAccountId())
                .eqIfPresent(ParkResourceAccountDO::getAssetType, reqVO.getAssetType())
                .eqIfPresent(ParkResourceAccountDO::getAssetExtendId, reqVO.getAssetExtendId())
                .betweenIfPresent(ParkResourceAccountDO::getAccountDate, reqVO.getAccountDate())
                .betweenIfPresent(ParkResourceAccountDO::getAccountUpdateDate, reqVO.getAccountUpdateDate())
                .eqIfPresent(ParkResourceAccountDO::getDataContent, reqVO.getDataContent())
                .eqIfPresent(ParkResourceAccountDO::getGenerateBy, reqVO.getGenerateBy())
                .eqIfPresent(ParkResourceAccountDO::getAccountStatus, reqVO.getAccountStatus())
                .betweenIfPresent(ParkResourceAccountDO::getAccountCreateTime, reqVO.getAccountCreateTime())
                .betweenIfPresent(ParkResourceAccountDO::getAccountUpdateTime, reqVO.getAccountUpdateTime())
                .eqIfPresent(ParkResourceAccountDO::getAccountRemark, reqVO.getAccountRemark())
                .betweenIfPresent(ParkResourceAccountDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkResourceAccountDO::getId));
    }

}