package cn.iocoder.yudao.module.evaluate.dal.mysql.collecttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype.CollectTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采集方式字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CollectTypeMapper extends BaseMapperX<CollectTypeDO> {

    default PageResult<CollectTypeDO> selectPage(CollectTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CollectTypeDO>()
                .eqIfPresent(CollectTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(CollectTypeDO::getName, reqVO.getName())
                .eqIfPresent(CollectTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(CollectTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(CollectTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(CollectTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(CollectTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CollectTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CollectTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CollectTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CollectTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CollectTypeDO::getId));
    }

}