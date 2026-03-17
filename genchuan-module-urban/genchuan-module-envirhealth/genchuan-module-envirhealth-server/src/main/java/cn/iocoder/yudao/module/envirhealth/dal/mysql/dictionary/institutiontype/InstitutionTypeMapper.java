package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.institutiontype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.institutiontype.vo.InstitutionTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.institutiontype.InstitutionTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机构类型字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface InstitutionTypeMapper extends BaseMapperX<InstitutionTypeDO> {

    default PageResult<InstitutionTypeDO> selectPage(InstitutionTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstitutionTypeDO>()
                .eqIfPresent(InstitutionTypeDO::getSysInstitutionTypeId, reqVO.getSysInstitutionTypeId())
                .likeIfPresent(InstitutionTypeDO::getName, reqVO.getName())
                .eqIfPresent(InstitutionTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(InstitutionTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InstitutionTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InstitutionTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InstitutionTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(InstitutionTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(InstitutionTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(InstitutionTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstitutionTypeDO::getId));
    }

}