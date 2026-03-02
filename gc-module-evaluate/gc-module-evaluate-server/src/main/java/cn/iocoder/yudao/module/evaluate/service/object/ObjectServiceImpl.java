package cn.iocoder.yudao.module.evaluate.service.object;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.object.ObjectMapper;
import groovy.util.logging.Slf4j;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NOT_EXISTS;

/**
 * 评价对象 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class ObjectServiceImpl implements ObjectService {

    @Resource
    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(ObjectServiceImpl.class);
    @Override
    public Long createObject(ObjectSaveReqVO createReqVO) {
        // 插入
        ObjectDO object = BeanUtils.toBean(createReqVO, ObjectDO.class);
        objectMapper.insert(object);
        // 返回
        return object.getId();
    }

    @Override
    public void updateObject(ObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateObjectExists(updateReqVO.getId());
        // 更新
        ObjectDO updateObj = BeanUtils.toBean(updateReqVO, ObjectDO.class);
        objectMapper.updateById(updateObj);
    }

    @Override
    public void deleteObject(Long id) {
        // 校验存在
        validateObjectExists(id);
        // 删除
        objectMapper.deleteById(id);
    }

    private void validateObjectExists(Long id) {
        if (objectMapper.selectById(id) == null) {
            throw exception(OBJECT_NOT_EXISTS);
        }
    }

    @Override
    public ObjectDO getObject(Long id) {
        return objectMapper.selectById(id);
    }

    @Override
    public PageResult<ObjectDO> getObjectPage(ObjectPageReqVO pageReqVO) {
        return objectMapper.selectPage(pageReqVO);
    }

    /**
     * 详情查询（文档弹窗需求）
     */
    @Override
    public ObjectRespVO getDetail(Long id) {
        return objectMapper.selectJoinDetail(id);
    }



    /**
     * 新增（文档右侧抽屉，含数据校验）
     * 框架生成：方法壳、事务注解、DO赋值、Mapper调用
     * 手动补充：文档要求的唯一性校验、手机号校验、变更日志
     */
    @Override
    public PageResult<ObjectRespVO> pageJoinQuery(@Valid ObjectPageReqVO pageReqVO) {
        // 设置默认分页参数
        if (pageReqVO.getPageNo() == null) {
            pageReqVO.setPageNo(1);
        }
        if (pageReqVO.getPageSize() == null) {
            pageReqVO.setPageSize(10);
        }

        // 查询数据
        List<ObjectRespVO> list = objectMapper.selectJoinPage(pageReqVO);
        Long total = objectMapper.selectJoinCount(pageReqVO);

        // 截取变更日志前50字
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(vo -> {
                if (StrUtil.isNotBlank(vo.getChangeLog())) {
                    String changeLog = vo.getChangeLog();
                    vo.setChangeLog(changeLog.length() > 50 ? changeLog.substring(0, 50) + "..." : changeLog);
                }
            });
        }

        return new PageResult<>(list, total);
    }

    @Override
    public ObjectRespVO getDetailById(String objectId) {
        return objectMapper.selectDetailById(objectId);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importObjects(List<ObjectSaveReqVO> importList) {
        if (CollUtil.isEmpty(importList)) {
            return;
        }

        // 批量导入
        for (ObjectSaveReqVO saveReqVO : importList) {
            try {
                createObject(saveReqVO);
            } catch (ServiceException e) {
                log.error("导入评价对象失败：{}", saveReqVO.getName(), e);
                throw e;
            }
        }
    }

    @Override
    public void validateNameUnique(String name, String areaCode, String excludeObjectId) {
        ObjectDO object = objectMapper.selectByNameAndArea(name, areaCode);
        if (object != null && !object.getObjectId().equals(excludeObjectId)) {
            throw exception(OBJECT_NAME_DUPLICATE);
        }
    }
//新
    @Override
    public PageResult<ObjectRespVO> getAllObjectPage(PageParam pageParam) {
        // 直接调用Mapper的联表方法
        return  objectMapper.selectAllObjectJoinPage(pageParam);
    }
}