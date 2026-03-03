package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 违规状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ViolationStatusMapper extends BaseMapperX<ViolationStatusDO> {

    default PageResult<ViolationStatusDO> selectPage(ViolationStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ViolationStatusDO>()
                .eqIfPresent(ViolationStatusDO::getViolationStatusId, reqVO.getViolationStatusId())
                .likeIfPresent(ViolationStatusDO::getViolationStatusName, reqVO.getViolationStatusName())
                .eqIfPresent(ViolationStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ViolationStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ViolationStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ViolationStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ViolationStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ViolationStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ViolationStatusDO::getId));
    }

}