package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletConsumableDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Arrays;
import java.util.List;

/**
 * 公厕耗材配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ToiletConsumableMapper extends BaseMapperX<ToiletConsumableDO> {

    default PageResult<ToiletConsumableDO> selectPage(ToiletConsumablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletConsumableDO>()
                .eqIfPresent(ToiletConsumableDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletConsumableDO::getConsumableId, reqVO.getConsumableId())
                .eqIfPresent(ToiletConsumableDO::getConsumableStock, reqVO.getConsumableStock())
                .eqIfPresent(ToiletConsumableDO::getConsumableThreshold, reqVO.getConsumableThreshold())
                .eqIfPresent(ToiletConsumableDO::getConsumableWarning, reqVO.getConsumableWarning())
                .betweenIfPresent(ToiletConsumableDO::getLastSupplyTime, reqVO.getLastSupplyTime())
                .eqIfPresent(ToiletConsumableDO::getSupplyCycle, reqVO.getSupplyCycle())
                .eqIfPresent(ToiletConsumableDO::getConsumableGap, reqVO.getConsumableGap())
                .eqIfPresent(ToiletConsumableDO::getManagerId, reqVO.getManagerId())
                .betweenIfPresent(ToiletConsumableDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletConsumableDO::getId));
    }

    /**
     * 分页查询待补充的耗材配置
     */
    // 详情分页
    List<ToiletConsumableDetailDO> selectDetailPage(@Param("reqVO") ToiletConsumablePageReqVO pageReqVO);

    // 查询总数
    Long selectCount(@Param("reqVO") ToiletConsumablePageReqVO pageReqVO);

    /**
     * 统计物资待补充的数量
     */
    @Select("SELECT COUNT(*) FROM public_toilet_consumable " +
            "WHERE deleted = 0 ")
    Long countConsumable();
}