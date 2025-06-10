package cn.iocoder.yudao.module.smartcity.service.workarea;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.workarea.WorkAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.workarea.WorkAreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 作业区域 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class WorkAreaServiceImpl implements WorkAreaService {

    @Resource
    private WorkAreaMapper workAreaMapper;

    @Override
    public Long createWorkArea(WorkAreaSaveReqVO createReqVO) {
        // 插入
        WorkAreaDO workArea = BeanUtils.toBean(createReqVO, WorkAreaDO.class);
        workAreaMapper.insert(workArea);
        // 返回
        return workArea.getId();
    }

    @Override
    public void updateWorkArea(WorkAreaSaveReqVO updateReqVO) {
        // 校验存在
        validateWorkAreaExists(updateReqVO.getId());
        // 更新
        WorkAreaDO updateObj = BeanUtils.toBean(updateReqVO, WorkAreaDO.class);
        workAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkArea(Long id) {
        // 校验存在
        validateWorkAreaExists(id);
        // 删除
        workAreaMapper.deleteById(id);
    }

    private void validateWorkAreaExists(Long id) {
        if (workAreaMapper.selectById(id) == null) {
            throw exception(WORK_AREA_NOT_EXISTS);
        }
    }

    @Override
    public WorkAreaDO getWorkArea(Long id) {
        return workAreaMapper.selectById(id);
    }

    @Override
    public PageResult<WorkAreaDO> getWorkAreaPage(WorkAreaPageReqVO pageReqVO) {
        return workAreaMapper.selectPage(pageReqVO);
    }

}