package cn.iocoder.yudao.module.smartcity.service.basicillegal;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.basicillegal.BasicIllegalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.basicillegal.BasicIllegalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 违建基本信息 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class BasicIllegalServiceImpl implements BasicIllegalService {

    @Resource
    private BasicIllegalMapper basicIllegalMapper;

    @Override
    public Long createBasicIllegal(BasicIllegalSaveReqVO createReqVO) {
        // 插入
        BasicIllegalDO basicIllegal = BeanUtils.toBean(createReqVO, BasicIllegalDO.class);
        basicIllegalMapper.insert(basicIllegal);
        // 返回
        return basicIllegal.getId();
    }

    @Override
    public void updateBasicIllegal(BasicIllegalSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicIllegalExists(updateReqVO.getId());
        // 更新
        BasicIllegalDO updateObj = BeanUtils.toBean(updateReqVO, BasicIllegalDO.class);
        basicIllegalMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicIllegal(Long id) {
        // 校验存在
        validateBasicIllegalExists(id);
        // 删除
        basicIllegalMapper.deleteById(id);
    }

    private void validateBasicIllegalExists(Long id) {
        if (basicIllegalMapper.selectById(id) == null) {
            throw exception(BASIC_ILLEGAL_NOT_EXISTS);
        }
    }

    @Override
    public BasicIllegalDO getBasicIllegal(Long id) {
        return basicIllegalMapper.selectById(id);
    }

    @Override
    public PageResult<BasicIllegalDO> getBasicIllegalPage(BasicIllegalPageReqVO pageReqVO) {
        return basicIllegalMapper.selectPage(pageReqVO);
    }

}