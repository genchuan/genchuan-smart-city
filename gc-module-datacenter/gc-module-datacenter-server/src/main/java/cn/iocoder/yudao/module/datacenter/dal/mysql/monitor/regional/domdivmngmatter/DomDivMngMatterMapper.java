package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmngmatter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngmatter.DomDivMngMatterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivMngMatterMapper extends BaseMapperX<DomDivMngMatterDO> {

    default PageResult<DomDivMngMatterDO> selectPage(DomDivMngMatterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivMngMatterDO>()
                .eqIfPresent(DomDivMngMatterDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivMngMatterDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivMngMatterDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivMngMatterDO::getMatterMajorId, reqVO.getMatterMajorId())
                .likeIfPresent(DomDivMngMatterDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(DomDivMngMatterDO::getMatterMinorId, reqVO.getMatterMinorId())
                .likeIfPresent(DomDivMngMatterDO::getMatterMinorName, reqVO.getMatterMinorName())
                .eqIfPresent(DomDivMngMatterDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivMngMatterDO::getRelEvtCount, reqVO.getRelEvtCount())
                .eqIfPresent(DomDivMngMatterDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivMngMatterDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivMngMatterDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivMngMatterDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivMngMatterDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivMngMatterDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivMngMatterDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivMngMatterDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivMngMatterDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivMngMatterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivMngMatterDO::getId));
    }

}