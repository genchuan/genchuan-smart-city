package cn.iocoder.yudao.module.smartcity.dal.mysql.dynamicinformationinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationinput.DynamicInformationInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo.*;

/**
 * 动态信息录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DynamicInformationInputMapper extends BaseMapperX<DynamicInformationInputDO> {

    default PageResult<DynamicInformationInputDO> selectPage(DynamicInformationInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DynamicInformationInputDO>()
                .eqIfPresent(DynamicInformationInputDO::getTitle, reqVO.getTitle())
                .betweenIfPresent(DynamicInformationInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DynamicInformationInputDO::getId));
    }

}