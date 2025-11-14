package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngcomp.DomDivMngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivMngCompMapper extends BaseMapperX<DomDivMngCompDO> {

    default PageResult<DomDivMngCompDO> selectPage(DomDivMngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivMngCompDO>()
                .eqIfPresent(DomDivMngCompDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivMngCompDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivMngCompDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivMngCompDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(DomDivMngCompDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(DomDivMngCompDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(DomDivMngCompDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(DomDivMngCompDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivMngCompDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivMngCompDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivMngCompDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivMngCompDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivMngCompDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DomDivMngCompDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DomDivMngCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivMngCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivMngCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivMngCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivMngCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivMngCompDO::getId));
    }

}