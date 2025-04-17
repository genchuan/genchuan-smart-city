package cn.iocoder.yudao.module.smartcity.service.keyworktasks;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.keyworktasks.KeyWorkTasksDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.keyworktasks.KeyWorkTasksMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 重点工作任务 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class KeyWorkTasksServiceImpl implements KeyWorkTasksService {

    @Resource
    private KeyWorkTasksMapper keyWorkTasksMapper;

    @Override
    public Long createKeyWorkTasks(KeyWorkTasksSaveReqVO createReqVO) {
        // 插入
        KeyWorkTasksDO keyWorkTasks = BeanUtils.toBean(createReqVO, KeyWorkTasksDO.class);
        keyWorkTasksMapper.insert(keyWorkTasks);
        // 返回
        return keyWorkTasks.getId();
    }

    @Override
    public void updateKeyWorkTasks(KeyWorkTasksSaveReqVO updateReqVO) {
        // 校验存在
        validateKeyWorkTasksExists(updateReqVO.getId());
        // 更新
        KeyWorkTasksDO updateObj = BeanUtils.toBean(updateReqVO, KeyWorkTasksDO.class);
        keyWorkTasksMapper.updateById(updateObj);
    }

    @Override
    public void deleteKeyWorkTasks(Long id) {
        // 校验存在
        validateKeyWorkTasksExists(id);
        // 删除
        keyWorkTasksMapper.deleteById(id);
    }

    private void validateKeyWorkTasksExists(Long id) {
        if (keyWorkTasksMapper.selectById(id) == null) {
            throw exception(KEY_WORK_TASKS_NOT_EXISTS);
        }
    }

    @Override
    public KeyWorkTasksDO getKeyWorkTasks(Long id) {
        return keyWorkTasksMapper.selectById(id);
    }

    @Override
    public PageResult<KeyWorkTasksDO> getKeyWorkTasksPage(KeyWorkTasksPageReqVO pageReqVO) {
        return keyWorkTasksMapper.selectPage(pageReqVO);
    }

}