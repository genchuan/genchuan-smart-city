package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 意见建议 Mapper
 *
 * @author carservice
 */
@Mapper
public interface SuggestionMapper extends BaseMapperX<SuggestionDO> {

    default PageResult<SuggestionDO> selectPage(SuggestionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SuggestionDO>()
                .eqIfPresent(SuggestionDO::getUserId, reqVO.getUserId())
                .likeIfPresent(SuggestionDO::getContent, reqVO.getContent())
                .eqIfPresent(SuggestionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SuggestionDO::getSubmitTime, reqVO.getSubmitTime())
                .orderByDesc(SuggestionDO::getId));
    }

}
