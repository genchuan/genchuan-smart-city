package cn.iocoder.yudao.module.smartcity.service.pipegallerysafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pipegallerysafety.PipeGallerySafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.pipegallerysafety.PipeGallerySafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 管廊安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PipeGallerySafetyServiceImpl implements PipeGallerySafetyService {

    @Resource
    private PipeGallerySafetyMapper pipeGallerySafetyMapper;

    @Override
    public Long createPipeGallerySafety(PipeGallerySafetySaveReqVO createReqVO) {
        // 插入
        PipeGallerySafetyDO pipeGallerySafety = BeanUtils.toBean(createReqVO, PipeGallerySafetyDO.class);
        pipeGallerySafetyMapper.insert(pipeGallerySafety);
        // 返回
        return pipeGallerySafety.getId();
    }

    @Override
    public void updatePipeGallerySafety(PipeGallerySafetySaveReqVO updateReqVO) {
        // 校验存在
        validatePipeGallerySafetyExists(updateReqVO.getId());
        // 更新
        PipeGallerySafetyDO updateObj = BeanUtils.toBean(updateReqVO, PipeGallerySafetyDO.class);
        pipeGallerySafetyMapper.updateById(updateObj);
    }

    @Override
    public void deletePipeGallerySafety(Long id) {
        // 校验存在
        validatePipeGallerySafetyExists(id);
        // 删除
        pipeGallerySafetyMapper.deleteById(id);
    }

    private void validatePipeGallerySafetyExists(Long id) {
        if (pipeGallerySafetyMapper.selectById(id) == null) {
            throw exception(PIPE_GALLERY_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public PipeGallerySafetyDO getPipeGallerySafety(Long id) {
        return pipeGallerySafetyMapper.selectById(id);
    }

    @Override
    public PageResult<PipeGallerySafetyDO> getPipeGallerySafetyPage(PipeGallerySafetyPageReqVO pageReqVO) {
        return pipeGallerySafetyMapper.selectPage(pageReqVO);
    }

}