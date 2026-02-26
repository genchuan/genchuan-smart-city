package cn.iocoder.yudao.module.evaluate.dal.mysql.subjectmember;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subjectmember.vo.SubjectMemberPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember.SubjectMemberDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价主体成员 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SubjectMemberMapper extends BaseMapperX<SubjectMemberDO> {

    default PageResult<SubjectMemberDO> selectPage(SubjectMemberPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SubjectMemberDO>()
                .eqIfPresent(SubjectMemberDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(SubjectMemberDO::getSubjectId, reqVO.getSubjectId())
                .eqIfPresent(SubjectMemberDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(SubjectMemberDO::getJoinTime, reqVO.getJoinTime())
                .eqIfPresent(SubjectMemberDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(SubjectMemberDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(SubjectMemberDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SubjectMemberDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SubjectMemberDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SubjectMemberDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(SubjectMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubjectMemberDO::getId));
    }

}