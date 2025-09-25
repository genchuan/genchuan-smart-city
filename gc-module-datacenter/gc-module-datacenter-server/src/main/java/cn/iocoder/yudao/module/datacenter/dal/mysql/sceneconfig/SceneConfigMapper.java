package cn.iocoder.yudao.module.datacenter.dal.mysql.sceneconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.sceneconfig.SceneConfigDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo.*;

/**
 * 场景分类 Mapper
 *
 * @author zcq
 */
@Mapper
public interface SceneConfigMapper extends BaseMapperX<SceneConfigDO> {

    default List<SceneConfigDO> selectList(SceneConfigListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SceneConfigDO>()
                .eqIfPresent(SceneConfigDO::getPid, reqVO.getPid())
                .likeIfPresent(SceneConfigDO::getName, reqVO.getName())
                .likeIfPresent(SceneConfigDO::getDeviceConfigName, reqVO.getDeviceConfigName())
                .likeIfPresent(SceneConfigDO::getAssetConfigName, reqVO.getAssetConfigName())
                .likeIfPresent(SceneConfigDO::getFlowConfigName, reqVO.getFlowConfigName())
                .eqIfPresent(SceneConfigDO::getInfo, reqVO.getInfo())
                .eqIfPresent(SceneConfigDO::getInfo1, reqVO.getInfo1())
                .eqIfPresent(SceneConfigDO::getInfo2, reqVO.getInfo2())
                .eqIfPresent(SceneConfigDO::getInfo3, reqVO.getInfo3())
                .eqIfPresent(SceneConfigDO::getInfo4, reqVO.getInfo4())
                .betweenIfPresent(SceneConfigDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SceneConfigDO::getId));
    }

	default SceneConfigDO selectByPidAndName(Long pid, String name) {
	    return selectOne(SceneConfigDO::getPid, pid, SceneConfigDO::getName, name);
	}

    default Long selectCountByPid(Long pid) {
        return selectCount(SceneConfigDO::getPid, pid);
    }

}