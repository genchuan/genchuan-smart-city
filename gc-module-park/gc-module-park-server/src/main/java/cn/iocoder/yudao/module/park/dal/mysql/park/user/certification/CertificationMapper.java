package cn.iocoder.yudao.module.park.dal.mysql.park.user.certification;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.certification.CertificationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 认证记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CertificationMapper extends BaseMapperX<CertificationDO> {

    default PageResult<CertificationDO> selectPage(CertificationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CertificationDO>()
                .eqIfPresent(CertificationDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CertificationDO::getUserType, reqVO.getUserType())
                .eqIfPresent(CertificationDO::getCertType, reqVO.getCertType())
                .eqIfPresent(CertificationDO::getCertFiles, reqVO.getCertFiles())
                .eqIfPresent(CertificationDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(CertificationDO::getCreditCode, reqVO.getCreditCode())
                .betweenIfPresent(CertificationDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(CertificationDO::getAuditBy, reqVO.getAuditBy())
                .betweenIfPresent(CertificationDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(CertificationDO::getAuditResult, reqVO.getAuditResult())
                .eqIfPresent(CertificationDO::getAuditOpinion, reqVO.getAuditOpinion())
                .betweenIfPresent(CertificationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CertificationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CertificationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CertificationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CertificationDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CertificationDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(CertificationDO::getId));
    }

}
