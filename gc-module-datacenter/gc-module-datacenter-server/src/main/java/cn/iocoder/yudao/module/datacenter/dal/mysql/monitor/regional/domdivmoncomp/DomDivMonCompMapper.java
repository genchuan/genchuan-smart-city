package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmoncomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmoncomp.DomDivMonCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivMonCompMapper extends BaseMapperX<DomDivMonCompDO> {

    default PageResult<DomDivMonCompDO> selectPage(DomDivMonCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivMonCompDO>()
                .eqIfPresent(DomDivMonCompDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivMonCompDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivMonCompDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivMonCompDO::getMonCompMajorId, reqVO.getMonCompMajorId())
                .likeIfPresent(DomDivMonCompDO::getMonCompMajorName, reqVO.getMonCompMajorName())
                .eqIfPresent(DomDivMonCompDO::getMonCompMinorId, reqVO.getMonCompMinorId())
                .likeIfPresent(DomDivMonCompDO::getMonCompMinorName, reqVO.getMonCompMinorName())
                .eqIfPresent(DomDivMonCompDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivMonCompDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivMonCompDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomDivMonCompDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivMonCompDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivMonCompDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivMonCompDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivMonCompDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivMonCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivMonCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivMonCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivMonCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivMonCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivMonCompDO::getId));
    }

}