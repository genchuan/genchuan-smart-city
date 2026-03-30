package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 违规类型字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ViolationTypeMapper extends BaseMapperX<ViolationTypeDO> {

    default PageResult<ViolationTypeDO> selectPage(ViolationTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ViolationTypeDO>()
                .eqIfPresent(ViolationTypeDO::getViolationTypeId, reqVO.getViolationTypeId())
                .likeIfPresent(ViolationTypeDO::getViolationName, reqVO.getViolationName())
                .eqIfPresent(ViolationTypeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ViolationTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ViolationTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ViolationTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ViolationTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ViolationTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ViolationTypeDO::getId));
    }

}