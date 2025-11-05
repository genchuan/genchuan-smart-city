package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtdatacfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtdatacfg.MonEvtDataCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件数据配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtDataCfgMapper extends BaseMapperX<MonEvtDataCfgDO> {

    default PageResult<MonEvtDataCfgDO> selectPage(MonEvtDataCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtDataCfgDO>()
                .eqIfPresent(MonEvtDataCfgDO::getMonEvtDataId, reqVO.getMonEvtDataId())
                .eqIfPresent(MonEvtDataCfgDO::getEvtCatId, reqVO.getEvtCatId())
                .likeIfPresent(MonEvtDataCfgDO::getEvtCatName, reqVO.getEvtCatName())
                .likeIfPresent(MonEvtDataCfgDO::getFieldName, reqVO.getFieldName())
                .eqIfPresent(MonEvtDataCfgDO::getFieldCode, reqVO.getFieldCode())
                .eqIfPresent(MonEvtDataCfgDO::getFieldType, reqVO.getFieldType())
                .eqIfPresent(MonEvtDataCfgDO::getFieldLength, reqVO.getFieldLength())
                .eqIfPresent(MonEvtDataCfgDO::getConstraintType, reqVO.getConstraintType())
                .eqIfPresent(MonEvtDataCfgDO::getValueRange, reqVO.getValueRange())
                .eqIfPresent(MonEvtDataCfgDO::getFieldDesc, reqVO.getFieldDesc())
                .eqIfPresent(MonEvtDataCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(MonEvtDataCfgDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MonEvtDataCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(MonEvtDataCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MonEvtDataCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MonEvtDataCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonEvtDataCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MonEvtDataCfgDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonEvtDataCfgDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonEvtDataCfgDO::getId));
    }

}