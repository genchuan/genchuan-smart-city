package cn.iocoder.yudao.module.smartcity.dal.mysql.keyworktasks;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.keyworktasks.KeyWorkTasksDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo.*;

/**
 * 重点工作任务 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface KeyWorkTasksMapper extends BaseMapperX<KeyWorkTasksDO> {

    default PageResult<KeyWorkTasksDO> selectPage(KeyWorkTasksPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<KeyWorkTasksDO>()
                .eqIfPresent(KeyWorkTasksDO::getTaskNumber, reqVO.getTaskNumber())
                .eqIfPresent(KeyWorkTasksDO::getTask, reqVO.getTask())
                .betweenIfPresent(KeyWorkTasksDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(KeyWorkTasksDO::getId));
    }

}