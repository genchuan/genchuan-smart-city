package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompinfo.MonCompInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompInfoMapper extends BaseMapperX<MonCompInfoDO> {

    default PageResult<MonCompInfoDO> selectPage(MonCompInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompInfoDO>()
                .eqIfPresent(MonCompInfoDO::getMonCompId, reqVO.getMonCompId())
                .eqIfPresent(MonCompInfoDO::getCompCode, reqVO.getCompCode())
                .likeIfPresent(MonCompInfoDO::getCompName, reqVO.getCompName())
                .eqIfPresent(MonCompInfoDO::getCompCatId, reqVO.getCompCatId())
                .eqIfPresent(MonCompInfoDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(MonCompInfoDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MonCompInfoDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(MonCompInfoDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(MonCompInfoDO::getGridId, reqVO.getGridId())
                .likeIfPresent(MonCompInfoDO::getGridName, reqVO.getGridName())
                .eqIfPresent(MonCompInfoDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(MonCompInfoDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(MonCompInfoDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonCompInfoDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonCompInfoDO::getId));
    }

}