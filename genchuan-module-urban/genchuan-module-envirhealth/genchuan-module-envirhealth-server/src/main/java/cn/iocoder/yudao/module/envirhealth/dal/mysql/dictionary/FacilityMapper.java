package cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilityPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.FacilityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设施字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FacilityMapper extends BaseMapperX<FacilityDO> {

    default PageResult<FacilityDO> selectPage(FacilityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FacilityDO>()
                .eqIfPresent(FacilityDO::getSysFacilityId, reqVO.getSysFacilityId())
                .likeIfPresent(FacilityDO::getName, reqVO.getName())
                .eqIfPresent(FacilityDO::getCode, reqVO.getCode())
                .eqIfPresent(FacilityDO::getType, reqVO.getType())
                .eqIfPresent(FacilityDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FacilityDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FacilityDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(FacilityDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(FacilityDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(FacilityDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(FacilityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FacilityDO::getId));
    }

}