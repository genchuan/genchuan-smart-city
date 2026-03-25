package cn.iocoder.yudao.module.smartcity.dal.mysql.basicillegal;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.basicillegal.BasicIllegalDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.*;

/**
 * 违建基本信息 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface BasicIllegalMapper extends BaseMapperX<BasicIllegalDO> {

    default PageResult<BasicIllegalDO> selectPage(BasicIllegalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicIllegalDO>()
                .eqIfPresent(BasicIllegalDO::getBuildingNumber, reqVO.getBuildingNumber())
                .betweenIfPresent(BasicIllegalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicIllegalDO::getId));
    }

}