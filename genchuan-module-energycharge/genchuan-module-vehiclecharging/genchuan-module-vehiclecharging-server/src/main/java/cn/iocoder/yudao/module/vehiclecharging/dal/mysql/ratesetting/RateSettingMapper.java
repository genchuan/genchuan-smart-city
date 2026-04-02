package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.ratesetting;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.RateSettingPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.RateSettingRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.ratesetting.RateSettingDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 费率设置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RateSettingMapper extends BaseMapperX<RateSettingDO> {
    /**
     * 分页查询费率设置列表
     */
    List<RateSettingRespVO> selectPage(RateSettingPageReqVO pageReqVO);

    /**
     * 统计分页总数
     */
    long selectPageCount(RateSettingPageReqVO pageReqVO);


//    default PageResult<RateSettingDO> selectPage(RateSettingPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<RateSettingDO>()
//                .eqIfPresent(RateSettingDO::getRateCode, reqVO.getRateCode())
//                .likeIfPresent(RateSettingDO::getRateName, reqVO.getRateName())
//                .eqIfPresent(RateSettingDO::getApplyScene, reqVO.getApplyScene())
//                .eqIfPresent(RateSettingDO::getRateRule, reqVO.getRateRule())
//                .betweenIfPresent(RateSettingDO::getEffectTime, reqVO.getEffectTime())
//                .betweenIfPresent(RateSettingDO::getExpireTime, reqVO.getExpireTime())
//                .eqIfPresent(RateSettingDO::getApplyStation, reqVO.getApplyStation())
//                .eqIfPresent(RateSettingDO::getApplyGroup, reqVO.getApplyGroup())
//                .eqIfPresent(RateSettingDO::getRateStatus, reqVO.getRateStatus())
//                .eqIfPresent(RateSettingDO::getRemark, reqVO.getRemark())
//                .eqIfPresent(RateSettingDO::getReserve1, reqVO.getReserve1())
//                .eqIfPresent(RateSettingDO::getReserve2, reqVO.getReserve2())
//                .betweenIfPresent(RateSettingDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(RateSettingDO::getId));
//    }

}
