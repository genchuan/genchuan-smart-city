package cn.iocoder.yudao.module.evaluate.dal.mysql.subjecttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 主体类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SubjectTypeMapper extends BaseMapperX<SubjectTypeDO> {

    default PageResult<SubjectTypeDO> selectPage(SubjectTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SubjectTypeDO>()
                .eqIfPresent(SubjectTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(SubjectTypeDO::getName, reqVO.getName())
                .eqIfPresent(SubjectTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(SubjectTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(SubjectTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(SubjectTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(SubjectTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SubjectTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SubjectTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SubjectTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(SubjectTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubjectTypeDO::getId));
    }

}