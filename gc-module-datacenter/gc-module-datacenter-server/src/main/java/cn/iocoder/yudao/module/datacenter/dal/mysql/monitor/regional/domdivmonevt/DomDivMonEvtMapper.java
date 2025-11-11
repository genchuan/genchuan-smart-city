package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmonevt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmonevt.DomDivMonEvtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivMonEvtMapper extends BaseMapperX<DomDivMonEvtDO> {

    default PageResult<DomDivMonEvtDO> selectPage(DomDivMonEvtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivMonEvtDO>()
                .eqIfPresent(DomDivMonEvtDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivMonEvtDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivMonEvtDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivMonEvtDO::getMonEvtMajorId, reqVO.getMonEvtMajorId())
                .likeIfPresent(DomDivMonEvtDO::getMonEvtMajorName, reqVO.getMonEvtMajorName())
                .eqIfPresent(DomDivMonEvtDO::getMonEvtMinorId, reqVO.getMonEvtMinorId())
                .likeIfPresent(DomDivMonEvtDO::getMonEvtMinorName, reqVO.getMonEvtMinorName())
                .eqIfPresent(DomDivMonEvtDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivMonEvtDO::getRelEvtCount, reqVO.getRelEvtCount())
                .eqIfPresent(DomDivMonEvtDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .eqIfPresent(DomDivMonEvtDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivMonEvtDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivMonEvtDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivMonEvtDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivMonEvtDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivMonEvtDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivMonEvtDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivMonEvtDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivMonEvtDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivMonEvtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivMonEvtDO::getId));
    }

}