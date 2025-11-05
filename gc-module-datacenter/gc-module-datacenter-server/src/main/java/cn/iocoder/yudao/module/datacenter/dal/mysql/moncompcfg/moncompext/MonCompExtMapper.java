package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompext;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompext.MonCompExtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件扩展配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompExtMapper extends BaseMapperX<MonCompExtDO> {

    default PageResult<MonCompExtDO> selectPage(MonCompExtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompExtDO>()
                .eqIfPresent(MonCompExtDO::getExtId, reqVO.getExtId())
                .eqIfPresent(MonCompExtDO::getCompId, reqVO.getCompId())
                .eqIfPresent(MonCompExtDO::getExtKey, reqVO.getExtKey())
                .eqIfPresent(MonCompExtDO::getExtValue, reqVO.getExtValue())
                .eqIfPresent(MonCompExtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonCompExtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonCompExtDO::getId));
    }

}