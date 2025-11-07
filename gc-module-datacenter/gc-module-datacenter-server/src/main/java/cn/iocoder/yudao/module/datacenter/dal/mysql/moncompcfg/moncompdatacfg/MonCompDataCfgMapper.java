package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompdatacfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompdatacfg.MonCompDataCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件数据配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompDataCfgMapper extends BaseMapperX<MonCompDataCfgDO> {

    default PageResult<MonCompDataCfgDO> selectPage(MonCompDataCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompDataCfgDO>()
                .eqIfPresent(MonCompDataCfgDO::getMonCompDataId, reqVO.getMonCompDataId())
                .eqIfPresent(MonCompDataCfgDO::getCompCatId, reqVO.getCompCatId())
                .likeIfPresent(MonCompDataCfgDO::getCompCatName, reqVO.getCompCatName())
                .eqIfPresent(MonCompDataCfgDO::getDataType, reqVO.getDataType())
                .eqIfPresent(MonCompDataCfgDO::getCoordSystem, reqVO.getCoordSystem())
                .eqIfPresent(MonCompDataCfgDO::getAccuracyLevel, reqVO.getAccuracyLevel())
                .eqIfPresent(MonCompDataCfgDO::getElevationDatum, reqVO.getElevationDatum())
                .likeIfPresent(MonCompDataCfgDO::getFieldName, reqVO.getFieldName())
                .eqIfPresent(MonCompDataCfgDO::getFieldCode, reqVO.getFieldCode())
                .eqIfPresent(MonCompDataCfgDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonCompDataCfgDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonCompDataCfgDO::getId));
    }

}