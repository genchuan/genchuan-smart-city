package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.managedmattermajor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo.ManagedMatterMajorListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo.ManagedMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor.ManagedMatterMajorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项分类表（含大类和小类） Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManagedMatterMajorMapper extends BaseMapperX<ManagedMatterMajorDO> {


    default List<ManagedMatterMajorDO> selectList(ManagedMatterMajorListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ManagedMatterMajorDO>()
                .likeIfPresent(ManagedMatterMajorDO::getMatterName, reqVO.getName())
                .eqIfPresent(ManagedMatterMajorDO::getEnableStatus, reqVO.getEnableStatus()));
    }
    default PageResult<ManagedMatterMajorDO> selectPage(ManagedMatterMajorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManagedMatterMajorDO>()
                .eqIfPresent(ManagedMatterMajorDO::getParentId, reqVO.getParentId())
                .eqIfPresent(ManagedMatterMajorDO::getMatterCode, reqVO.getMatterCode())
                .likeIfPresent(ManagedMatterMajorDO::getMatterName, reqVO.getMatterName())
                .eqIfPresent(ManagedMatterMajorDO::getMatterDesc, reqVO.getMatterDesc())
                .eqIfPresent(ManagedMatterMajorDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(ManagedMatterMajorDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(ManagedMatterMajorDO::getIsExtend, reqVO.getIsExtend())
                .eqIfPresent(ManagedMatterMajorDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(ManagedMatterMajorDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(ManagedMatterMajorDO::getFlowInstanceId, reqVO.getFlowInstanceId())
                .eqIfPresent(ManagedMatterMajorDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(ManagedMatterMajorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ManagedMatterMajorDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(ManagedMatterMajorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ManagedMatterMajorDO::getId));
    }

}
