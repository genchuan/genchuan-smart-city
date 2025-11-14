package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivappscene;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivappscene.DomDivAppSceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用场景分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivAppSceneMapper extends BaseMapperX<DomDivAppSceneDO> {

    default PageResult<DomDivAppSceneDO> selectPage(DomDivAppScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivAppSceneDO>()
                .eqIfPresent(DomDivAppSceneDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivAppSceneDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivAppSceneDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivAppSceneDO::getAppSceneMajorId, reqVO.getAppSceneMajorId())
                .likeIfPresent(DomDivAppSceneDO::getAppSceneMajorName, reqVO.getAppSceneMajorName())
                .eqIfPresent(DomDivAppSceneDO::getAppSceneMinorId, reqVO.getAppSceneMinorId())
                .likeIfPresent(DomDivAppSceneDO::getAppSceneMinorName, reqVO.getAppSceneMinorName())
                .eqIfPresent(DomDivAppSceneDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivAppSceneDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivAppSceneDO::getRelEvtCount, reqVO.getRelEvtCount())
                .eqIfPresent(DomDivAppSceneDO::getSceneMngrId, reqVO.getSceneMngrId())
                .likeIfPresent(DomDivAppSceneDO::getSceneMngrName, reqVO.getSceneMngrName())
                .eqIfPresent(DomDivAppSceneDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivAppSceneDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivAppSceneDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivAppSceneDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivAppSceneDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivAppSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivAppSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivAppSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivAppSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivAppSceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivAppSceneDO::getId));
    }

}