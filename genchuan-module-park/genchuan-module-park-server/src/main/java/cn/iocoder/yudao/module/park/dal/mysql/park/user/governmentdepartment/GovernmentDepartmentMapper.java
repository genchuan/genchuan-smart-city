package cn.iocoder.yudao.module.park.dal.mysql.park.user.governmentdepartment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.governmentdepartment.GovernmentDepartmentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 政府部门 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface GovernmentDepartmentMapper extends BaseMapperX<GovernmentDepartmentDO> {

    default PageResult<GovernmentDepartmentDO> selectPage(GovernmentDepartmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GovernmentDepartmentDO>()
                .likeIfPresent(GovernmentDepartmentDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(GovernmentDepartmentDO::getDeptCode, reqVO.getDeptCode())
                .eqIfPresent(GovernmentDepartmentDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(GovernmentDepartmentDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(GovernmentDepartmentDO::getRegionCode, reqVO.getRegionCode())
                .eqIfPresent(GovernmentDepartmentDO::getResponsibility, reqVO.getResponsibility())
                .betweenIfPresent(GovernmentDepartmentDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GovernmentDepartmentDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GovernmentDepartmentDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GovernmentDepartmentDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GovernmentDepartmentDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GovernmentDepartmentDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(GovernmentDepartmentDO::getId));
    }

}
