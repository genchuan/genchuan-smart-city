package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarncustomcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警自定义统计配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnCustomCfgMapper extends BaseMapperX<EarlyWarnCustomCfgDO> {

    default PageResult<EarlyWarnCustomCfgDO> selectPage(EarlyWarnCustomCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnCustomCfgDO>()
                .eqIfPresent(EarlyWarnCustomCfgDO::getCustomStatCfgId, reqVO.getCustomStatCfgId())
                .likeIfPresent(EarlyWarnCustomCfgDO::getCfgName, reqVO.getCfgName())
                .eqIfPresent(EarlyWarnCustomCfgDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnCustomCfgDO::getStatDimensions, reqVO.getStatDimensions())
                .eqIfPresent(EarlyWarnCustomCfgDO::getFilterConditions, reqVO.getFilterConditions())
                .eqIfPresent(EarlyWarnCustomCfgDO::getGroupDimensions, reqVO.getGroupDimensions())
                .eqIfPresent(EarlyWarnCustomCfgDO::getStatIndicators, reqVO.getStatIndicators())
                .eqIfPresent(EarlyWarnCustomCfgDO::getCreateUserId, reqVO.getCreateUserId())
                .likeIfPresent(EarlyWarnCustomCfgDO::getCreateUserName, reqVO.getCreateUserName())
                .betweenIfPresent(EarlyWarnCustomCfgDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EarlyWarnCustomCfgDO::getIsFrequentlyUsed, reqVO.getIsFrequentlyUsed())
                .eqIfPresent(EarlyWarnCustomCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnCustomCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnCustomCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnCustomCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(EarlyWarnCustomCfgDO::getId));
    }

}