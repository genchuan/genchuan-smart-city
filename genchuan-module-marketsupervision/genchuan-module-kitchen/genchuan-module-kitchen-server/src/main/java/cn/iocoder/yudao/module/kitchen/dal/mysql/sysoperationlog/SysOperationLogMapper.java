package cn.iocoder.yudao.module.kitchen.dal.mysql.sysoperationlog;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.SysOperationLogPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysoperationlog.SysOperationLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统操作审计日志表，存储平台全模块所有操作的审计日志信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysOperationLogMapper extends BaseMapperX<SysOperationLogDO> {

    default PageResult<SysOperationLogDO> selectPage(SysOperationLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysOperationLogDO>()
                .eqIfPresent(SysOperationLogDO::getOperUserId, reqVO.getOperUserId())
                .likeIfPresent(SysOperationLogDO::getOperUserName, reqVO.getOperUserName())
                .betweenIfPresent(SysOperationLogDO::getOperTime, reqVO.getOperTime())
                .eqIfPresent(SysOperationLogDO::getOperType, reqVO.getOperType())
                .eqIfPresent(SysOperationLogDO::getOperObject, reqVO.getOperObject())
                .eqIfPresent(SysOperationLogDO::getOperResult, reqVO.getOperResult())
                .eqIfPresent(SysOperationLogDO::getBatchSelectInfo, reqVO.getBatchSelectInfo())
                .eqIfPresent(SysOperationLogDO::getOperIp, reqVO.getOperIp())
                .eqIfPresent(SysOperationLogDO::getOperDesc, reqVO.getOperDesc())
                .betweenIfPresent(SysOperationLogDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysOperationLogDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysOperationLogDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysOperationLogDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysOperationLogDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysOperationLogDO::getId));
    }

}
