package cn.iocoder.yudao.module.smartcity.dal.mysql.eventlist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventlist.EventListDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.*;

/**
 * 事件列表 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EventListMapper extends BaseMapperX<EventListDO> {

    default PageResult<EventListDO> selectPage(EventListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventListDO>()
                .eqIfPresent(EventListDO::getEventNumber, reqVO.getEventNumber())
                .betweenIfPresent(EventListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventListDO::getId));
    }

}