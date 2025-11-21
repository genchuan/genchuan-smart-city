package cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.crossdomexp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.crossdomexp.CrossDomExpDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 跨域经验交流 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CrossDomExpMapper extends BaseMapperX<CrossDomExpDO> {

    default PageResult<CrossDomExpDO> selectPage(CrossDomExpPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrossDomExpDO>()
                .eqIfPresent(CrossDomExpDO::getCrossDomExpId, reqVO.getCrossDomExpId())
                .eqIfPresent(CrossDomExpDO::getExpTitle, reqVO.getExpTitle())
                .eqIfPresent(CrossDomExpDO::getExpFieldCode, reqVO.getExpFieldCode())
                .likeIfPresent(CrossDomExpDO::getExpFieldName, reqVO.getExpFieldName())
                .eqIfPresent(CrossDomExpDO::getSourceCity, reqVO.getSourceCity())
                .eqIfPresent(CrossDomExpDO::getExpDetail, reqVO.getExpDetail())
                .eqIfPresent(CrossDomExpDO::getApplicableScenario, reqVO.getApplicableScenario())
                .eqIfPresent(CrossDomExpDO::getAttachId, reqVO.getAttachId())
                .eqIfPresent(CrossDomExpDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(CrossDomExpDO::getPublishStatus, reqVO.getPublishStatus())
                .eqIfPresent(CrossDomExpDO::getViewCount, reqVO.getViewCount())
                .eqIfPresent(CrossDomExpDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(CrossDomExpDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CrossDomExpDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(CrossDomExpDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(CrossDomExpDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(CrossDomExpDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CrossDomExpDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CrossDomExpDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(CrossDomExpDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(CrossDomExpDO::getId));
    }

}