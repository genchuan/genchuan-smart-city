package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcat.MonCompCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompCatMapper extends BaseMapperX<MonCompCatDO> {

    default PageResult<MonCompCatDO> selectPage(MonCompCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompCatDO>()
                .eqIfPresent(MonCompCatDO::getMonCompCatId, reqVO.getMonCompCatId())
                .eqIfPresent(MonCompCatDO::getParentCatId, reqVO.getParentCatId())
                .eqIfPresent(MonCompCatDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(MonCompCatDO::getCatCode, reqVO.getCatCode())
                .likeIfPresent(MonCompCatDO::getCatName, reqVO.getCatName())
                .eqIfPresent(MonCompCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(MonCompCatDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonCompCatDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonCompCatDO::getId));
    }

}