package cn.iocoder.yudao.module.envir.dal.mysql.operationstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.operationstatus.OperationStatusDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.operationstatus.vo.*;

/**
 * 运营状态字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OperationStatusMapper extends BaseMapperX<OperationStatusDO> {

    default PageResult<OperationStatusDO> selectPage(OperationStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OperationStatusDO>()
                .eqIfPresent(OperationStatusDO::getSysOperationStatusId, reqVO.getSysOperationStatusId())
                .likeIfPresent(OperationStatusDO::getName, reqVO.getName())
                .eqIfPresent(OperationStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(OperationStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OperationStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(OperationStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OperationStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OperationStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OperationStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(OperationStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OperationStatusDO::getId));
    }

}