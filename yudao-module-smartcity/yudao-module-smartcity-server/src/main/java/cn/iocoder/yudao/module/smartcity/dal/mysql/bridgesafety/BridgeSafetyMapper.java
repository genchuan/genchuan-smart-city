package cn.iocoder.yudao.module.smartcity.dal.mysql.bridgesafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.bridgesafety.BridgeSafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo.*;

/**
 * 桥梁安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface BridgeSafetyMapper extends BaseMapperX<BridgeSafetyDO> {

    default PageResult<BridgeSafetyDO> selectPage(BridgeSafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BridgeSafetyDO>()
                .likeIfPresent(BridgeSafetyDO::getBridgeName, reqVO.getBridgeName())
                .likeIfPresent(BridgeSafetyDO::getBridgeNumber, reqVO.getBridgeNumber())
                .likeIfPresent(BridgeSafetyDO::getBridgeLocation, reqVO.getBridgeLocation())
                .eqIfPresent(BridgeSafetyDO::getBridgeType, reqVO.getBridgeType())
                .betweenIfPresent(BridgeSafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BridgeSafetyDO::getId));
    }

}