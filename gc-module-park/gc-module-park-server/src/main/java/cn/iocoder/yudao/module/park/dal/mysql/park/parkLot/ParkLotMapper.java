package cn.iocoder.yudao.module.park.dal.mysql.park.parkLot;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.parkLot.ParkLotDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 停车场列表
 *
 *
 */
@Mapper
public interface ParkLotMapper extends BaseMapperX<ParkLotDO> {
    /**
     * 分页查询车场信息
     * @param reqVO 分页查询条件（含分页参数+查询条件）
     * @return 车场信息分页结果
     */
    default PageResult<ParkLotDO> selectPage(ParkLotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkLotDO>()
                // ========== 字符串字段：模糊查询 ==========
                // 车场UUID 模糊查询
                .likeIfPresent(ParkLotDO::getLotId, reqVO.getLotId())
                // 12位地区码 模糊查询
                .likeIfPresent(ParkLotDO::getRegionFullCode, reqVO.getRegionFullCode())
                // 车场类型 模糊查询（如输入"地"匹配"地面"/"地下"）
                .likeIfPresent(ParkLotDO::getParkType, reqVO.getParkType())
                // 开放时间 模糊查询（字符串格式，如"08:"匹配"08:00:00"）
                .likeIfPresent(ParkLotDO::getOpenTime, reqVO.getOpenTime())
                // 关闭时间 模糊查询
                .likeIfPresent(ParkLotDO::getCloseTime, reqVO.getCloseTime())
                // 运营商户ID 模糊查询
                .likeIfPresent(ParkLotDO::getManagementMerchantId, reqVO.getManagementMerchantId())
                // 费率策略ID 模糊查询
                .likeIfPresent(ParkLotDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                // 车场备注/名称 模糊查询
                .likeIfPresent(ParkLotDO::getLotRemark, reqVO.getLotRemark())
                // 扩展字段1-4 模糊查询
                .likeIfPresent(ParkLotDO::getExtCommon1, reqVO.getExtCommon1())
                .likeIfPresent(ParkLotDO::getExtCommon2, reqVO.getExtCommon2())
                .likeIfPresent(ParkLotDO::getExtCommon3, reqVO.getExtCommon3())
                .likeIfPresent(ParkLotDO::getExtCommon4, reqVO.getExtCommon4())
                // 创建人 模糊查询
                .likeIfPresent(ParkLotDO::getCreator, reqVO.getCreator())
                // 更新人 模糊查询
                .likeIfPresent(ParkLotDO::getUpdater, reqVO.getUpdater())

                // ========== 数字字段：范围查询 ==========
                // 关联资产扩展ID 范围查询（≥）
                .geIfPresent(ParkLotDO::getAssetExtendId, reqVO.getAssetExtendIdStart())
                // 关联资产扩展ID 范围查询（≤）
                .leIfPresent(ParkLotDO::getAssetExtendId, reqVO.getAssetExtendIdEnd())
                // 总车位数 范围查询（≥）
                .geIfPresent(ParkLotDO::getTotalSpace, reqVO.getTotalSpaceStart())
                // 总车位数 范围查询（≤）
                .leIfPresent(ParkLotDO::getTotalSpace, reqVO.getTotalSpaceEnd())
                // 可用车位数 范围查询（≥）
                .geIfPresent(ParkLotDO::getAvailableSpace, reqVO.getAvailableSpaceStart())
                // 可用车位数 范围查询（≤）
                .leIfPresent(ParkLotDO::getAvailableSpace, reqVO.getAvailableSpaceEnd())
                // 租户ID 范围查询（≥）
                .geIfPresent(ParkLotDO::getTenantId, reqVO.getTenantIdStart())
                // 租户ID 范围查询（≤）
                .leIfPresent(ParkLotDO::getTenantId, reqVO.getTenantIdEnd())

                // ========== 时间字段：范围查询 ==========
                // 业务创建时间 范围查询
                .betweenIfPresent(ParkLotDO::getLotCreateTime, reqVO.getLotCreateTimeStart(), reqVO.getLotCreateTimeEnd())
                // 业务更新时间 范围查询
                .betweenIfPresent(ParkLotDO::getLotUpdateTime, reqVO.getLotUpdateTimeStart(), reqVO.getLotUpdateTimeEnd())
                // 数据创建时间 范围查询
                .betweenIfPresent(ParkLotDO::getCreateTime, reqVO.getCreateTimeStart(), reqVO.getCreateTimeEnd())
                // 数据更新时间 范围查询
                .betweenIfPresent(ParkLotDO::getUpdateTime, reqVO.getUpdateTimeStart(), reqVO.getUpdateTimeEnd())

                // ========== 布尔字段：精准查询 ==========
                // 逻辑删除标识 精准查询（0=未删，1=已删）
                .eqIfPresent(ParkLotDO::getDeleted, reqVO.getDeleted())

                // ========== 排序规则 ==========
                // 按更新时间降序 → 创建时间降序
                .orderByDesc(ParkLotDO::getUpdateTime)
                .orderByDesc(ParkLotDO::getCreateTime));
    }



    /**
     * 检查车场ID是否已存在
     * @param lotId 车场ID
     * @return 存在返回true，否则false
     */
    default boolean checkLotIdExists(String lotId) {
        return selectCount(new LambdaQueryWrapperX<ParkLotDO>()
                .eq(ParkLotDO::getLotId, lotId)
                .eq(ParkLotDO::getDeleted, false)) > 0;
    }
}
