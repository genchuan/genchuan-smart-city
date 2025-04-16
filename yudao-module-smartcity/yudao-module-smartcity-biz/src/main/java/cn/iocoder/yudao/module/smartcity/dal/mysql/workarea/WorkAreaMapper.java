package cn.iocoder.yudao.module.smartcity.dal.mysql.workarea;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.workarea.WorkAreaDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.*;

/**
 * 作业区域 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface WorkAreaMapper extends BaseMapperX<WorkAreaDO> {

    default PageResult<WorkAreaDO> selectPage(WorkAreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorkAreaDO>()
                .eqIfPresent(WorkAreaDO::getAreaNumber, reqVO.getAreaNumber())
                .likeIfPresent(WorkAreaDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(WorkAreaDO::getRegionalLocation, reqVO.getRegionalLocation())
                .betweenIfPresent(WorkAreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WorkAreaDO::getId));
    }

}