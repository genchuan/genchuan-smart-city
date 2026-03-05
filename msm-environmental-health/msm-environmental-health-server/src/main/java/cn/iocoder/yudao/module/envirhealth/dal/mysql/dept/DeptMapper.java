package cn.iocoder.yudao.module.envirhealth.dal.mysql.dept;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dept.DeptDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dept.vo.*;

/**
 * 部门 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DeptMapper extends BaseMapperX<DeptDO> {

    default PageResult<DeptDO> selectPage(DeptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeptDO>()
                .eqIfPresent(DeptDO::getSysDeptId, reqVO.getSysDeptId())
                .likeIfPresent(DeptDO::getName, reqVO.getName())
                .eqIfPresent(DeptDO::getParentId, reqVO.getParentId())
                .eqIfPresent(DeptDO::getDeptCode, reqVO.getDeptCode())
                .eqIfPresent(DeptDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DeptDO::getSort, reqVO.getSort())
                .eqIfPresent(DeptDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DeptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeptDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DeptDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DeptDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(DeptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeptDO::getId));
    }

}