package cn.iocoder.yudao.module.facility.dal.mysql.sysuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysuser.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysUserMapper extends BaseMapperX<SysUserDO> {

    default PageResult<SysUserDO> selectPage(SysUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysUserDO>()
                .likeIfPresent(SysUserDO::getNickname, reqVO.getNickname())
                .likeIfPresent(SysUserDO::getUsername, reqVO.getUsername())
                .eqIfPresent(SysUserDO::getAccountId, reqVO.getAccountId())
                .eqIfPresent(SysUserDO::getRoleId, reqVO.getRoleId())
                .eqIfPresent(SysUserDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(SysUserDO::getPhone, reqVO.getPhone())
                .eqIfPresent(SysUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SysUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysUserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysUserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysUserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysUserDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysUserDO::getId));
    }

}
