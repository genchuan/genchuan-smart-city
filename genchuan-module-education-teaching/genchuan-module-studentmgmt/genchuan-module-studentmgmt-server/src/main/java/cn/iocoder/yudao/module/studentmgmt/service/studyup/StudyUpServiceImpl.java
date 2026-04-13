package cn.iocoder.yudao.module.studentmgmt.service.studyup;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studyup.StudyUpMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 升学管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudyUpServiceImpl implements StudyUpService {

    @Resource
    private StudyUpMapper studyUpMapper;

    @Override
    public Long createStudyUp(StudyUpSaveReqVO createReqVO) {
        // 插入
        StudyUpDO studyUp = BeanUtils.toBean(createReqVO, StudyUpDO.class);
        studyUpMapper.insert(studyUp);

        // 返回
        return studyUp.getId();
    }

    @Override
    public void updateStudyUp(StudyUpSaveReqVO updateReqVO) {
        // 校验存在
        validateStudyUpExists(updateReqVO.getId());
        // 更新
        StudyUpDO updateObj = BeanUtils.toBean(updateReqVO, StudyUpDO.class);
        studyUpMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudyUp(Long id) {
        // 校验存在
        validateStudyUpExists(id);
        // 删除
        studyUpMapper.deleteById(id);
    }

    @Override
        public void deleteStudyUpListByIds(List<Long> ids) {
        // 删除
        studyUpMapper.deleteByIds(ids);
        }


    private void validateStudyUpExists(Long id) {
        if (studyUpMapper.selectById(id) == null) {
            throw exception(STUDY_UP_NOT_EXISTS);
        }
    }

    @Override
    public StudyUpDO getStudyUp(Long id) {
        return studyUpMapper.selectById(id);
    }

    @Override
    public PageResult<StudyUpDO> getStudyUpPage(StudyUpPageReqVO pageReqVO) {
        return studyUpMapper.selectPage(pageReqVO);
    }

}