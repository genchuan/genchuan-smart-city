package cn.iocoder.yudao.module.datacenter.dal.mysql.homequickmenu;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.homequickmenu.HomeQuickMenuDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo.*;

/**
 * 系统首页快捷菜单配置 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface HomeQuickMenuMapper extends BaseMapperX<HomeQuickMenuDO> {

    default PageResult<HomeQuickMenuDO> selectPage(HomeQuickMenuPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HomeQuickMenuDO>()
                .likeIfPresent(HomeQuickMenuDO::getName, reqVO.getName())
                .eqIfPresent(HomeQuickMenuDO::getPath, reqVO.getPath())
                .eqIfPresent(HomeQuickMenuDO::getType, reqVO.getType())
                .eqIfPresent(HomeQuickMenuDO::getIcon, reqVO.getIcon())
                .eqIfPresent(HomeQuickMenuDO::getUserId, reqVO.getUserId())
                .eqIfPresent(HomeQuickMenuDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(HomeQuickMenuDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(HomeQuickMenuDO::getMenuDesc, reqVO.getMenuDesc())
                .eqIfPresent(HomeQuickMenuDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(HomeQuickMenuDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(HomeQuickMenuDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HomeQuickMenuDO::getId));
    }

}