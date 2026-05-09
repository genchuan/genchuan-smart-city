package cn.iocoder.yudao.module.inspectop.service.inspectuser;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;
import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.InspectUserImportReqVO; // 假设您已创建此VO，见下一点说明
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*; // 您需要定义对应的错误码常量
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectuser.InspectUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检人员 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectUserServiceImpl implements InspectUserService {

    @Resource
    private InspectUserMapper inspectUserMapper;

    @Override
    public Long createInspectUser(InspectUserSaveReqVO createReqVO) {
        // 插入
        InspectUserDO inspectUser = BeanUtils.toBean(createReqVO, InspectUserDO.class);
        inspectUserMapper.insert(inspectUser);

        // 返回
        return inspectUser.getId();
    }

    @Override
    public void updateInspectUser(InspectUserSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectUserExists(updateReqVO.getId());
        // 更新
        InspectUserDO updateObj = BeanUtils.toBean(updateReqVO, InspectUserDO.class);
        inspectUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectUser(Long id) {
        // 校验存在
        validateInspectUserExists(id);
        // 删除
        inspectUserMapper.deleteById(id);
    }

    @Override
        public void deleteInspectUserListByIds(List<Long> ids) {
        // 删除
        inspectUserMapper.deleteByIds(ids);
        }


    private void validateInspectUserExists(Long id) {
        if (inspectUserMapper.selectById(id) == null) {
            throw exception(INSPECT_USER_NOT_EXISTS);
        }
    }

    @Override
    public InspectUserDO getInspectUser(Long id) {
        return inspectUserMapper.selectById(id);
    }

    @Override
    public PageResult<InspectUserDO> getInspectUserPage(InspectUserPageReqVO pageReqVO) {
        return inspectUserMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<InspectUserRespVO> getInspectUserPageWithTaskCount(InspectUserPageReqVO pageReqVO) {
        // 1. 获取基础分页数据
        PageResult<InspectUserDO> pageResult = inspectUserMapper.selectPage(pageReqVO);

        // 如果查询结果为空，返回空分页
        if (CollUtil.isEmpty(pageResult.getList())) {
            return new PageResult<>(Collections.emptyList(), pageResult.getTotal());
        }

        // 2. 获取所有人员ID
        List<Long> userIds = convertList(pageResult.getList(), InspectUserDO::getId);

        // 3. 批量查询任务数量
        List<Map<String, Object>> taskCountList = inspectUserMapper.selectTaskCountByUserIds(userIds);

        // 4. 将查询结果转换为Map<Long, Integer>格式
        Map<Long, Integer> taskCountMap = new HashMap<>();
        for (Map<String, Object> map : taskCountList) {
            // 注意：数据库返回的数值类型可能是Long，需要正确处理
            Long userId = null;
            Integer taskCount = 0;

            Object userIdObj = map.get("userId");
            Object taskCountObj = map.get("taskCount");

            if (userIdObj instanceof Number) {
                userId = ((Number) userIdObj).longValue();
            }

            if (taskCountObj instanceof Number) {
                taskCount = ((Number) taskCountObj).intValue();
            }

            if (userId != null) {
                taskCountMap.put(userId, taskCount);
            }
        }

        // 5. 转换DO为VO，并设置任务记录文本
        List<InspectUserRespVO> voList = new ArrayList<>();
        for (InspectUserDO inspectUser : pageResult.getList()) {
            // 转换基础属性
            InspectUserRespVO respVO = BeanUtils.toBean(inspectUser, InspectUserRespVO.class);

            // 获取任务数量并设置文本
            Integer taskCount = taskCountMap.get(inspectUser.getId());
            if (taskCount == null) {
                taskCount = 0;
            }
            respVO.setTaskRecordText(taskCount + "条任务记录");

            voList.add(respVO);
        }

        // 6. 返回VO分页结果
        return new PageResult<>(voList, pageResult.getTotal());
    }

    @Override
    public InspectUserRespVO getInspectUserWithTaskCount(Long id) {
        // 1. 获取巡检人员基本信息
        InspectUserDO inspectUser = getInspectUser(id);
        if (inspectUser == null) {
            return null;
        }
        // 2. 转换为RespVO
        InspectUserRespVO respVO = BeanUtils.toBean(inspectUser, InspectUserRespVO.class);
        // 3. 查询关联任务数量
        Integer taskCount = inspectUserMapper.selectTaskCountByUserId(id);
        if (taskCount == null) {
            taskCount = 0;
        }
        // 4. 拼接任务记录文本
        respVO.setTaskRecordText(taskCount + "条任务记录");
        return respVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportRespVO importInspectUser(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // 1. 使用Excel工具类读取文件数据，并映射到InspectUserImportReqVO列表
            List<InspectUserImportReqVO> importList = ExcelUtils.read(file, InspectUserImportReqVO.class);

            if (CollUtil.isEmpty(importList)) {
                throw exception(INSPECT_USER_IMPORT_DATA_EMPTY); // 请定义此错误码
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 2. 遍历处理每一行数据
            for (int i = 0; i < importList.size(); i++) {
                InspectUserImportReqVO importReqVO = importList.get(i);
                int rowIndex = i + 2; // Excel行号（从1开始，标题行占1行）

                try {
                    // 2.1 校验必填字段（请根据您的业务需求调整）
                    if (StrUtil.isBlank(importReqVO.getName())) {
                        throw exception(INSPECT_USER_NAME_NOT_NULL); // 请定义此错误码
                    }
                    if (StrUtil.isBlank(importReqVO.getPhone())) {
                        throw exception(INSPECT_USER_PHONE_NOT_NULL); // 请定义此错误码
                    }
                    if (StrUtil.isBlank(importReqVO.getStatus())) {
                        throw exception(INSPECT_USER_STATUS_NOT_NULL); // 请定义此错误码
                    }

                    // 2.2 根据唯一标识（例如手机号）查找是否已存在
                    InspectUserDO existUser = inspectUserMapper.selectOne(
                            new LambdaQueryWrapperX<InspectUserDO>()
                                    .eq(InspectUserDO::getPhone, importReqVO.getPhone())
                    );

                    if (existUser != null) {
                        if (updateSupport) {
                            // 更新已存在的记录
                            InspectUserDO updateObj = BeanUtils.toBean(importReqVO, InspectUserDO.class);
                            updateObj.setId(existUser.getId()); // 保留原有ID
                            inspectUserMapper.updateById(updateObj);
                        } else {
                            throw exception(INSPECT_USER_EXISTS, importReqVO.getPhone()); // 请定义此错误码
                        }
                    } else {
                        // 新增记录
                        InspectUserDO inspectUser = BeanUtils.toBean(importReqVO, InspectUserDO.class);
                        inspectUserMapper.insert(inspectUser);
                    }
                    successCount++;

                } catch (Exception e) {
                    // 2.3 记录本行导入失败的信息
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 3. 设置最终导入结果
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            // 4. 处理整体导入失败（如文件格式错误、读取异常等）
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableInspectUser(Long id) {
        // 校验人员是否存在
        validateInspectUserExists(id);

        // 创建更新对象，只更新状态字段为1（启用）
        InspectUserDO updateObj = new InspectUserDO();
        updateObj.setId(id);
        updateObj.setStatus("1"); // "1"表示启用状态
        updateObj.setOnlineStatus("1"); // "1"表示在线状态

        // 执行更新
        inspectUserMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableInspectUser(Long id) {
        // 校验人员是否存在
        validateInspectUserExists(id);

        // 创建更新对象，只更新状态字段为2（禁用）
        InspectUserDO updateObj = new InspectUserDO();
        updateObj.setId(id);
        updateObj.setStatus("2"); // "2"表示禁用状态
        updateObj.setOnlineStatus("2"); // "1"表示离线状态

        // 执行更新
        inspectUserMapper.updateById(updateObj);
    }

    @Override
    public InspectUserChartRespVO getInspectUserChart() {
        InspectUserChartRespVO respVO = new InspectUserChartRespVO();

        // 获取区域分布数据
        List<InspectUserChartRespVO.AreaData> areaData = inspectUserMapper.selectAreaData();
        respVO.setAreaData(areaData);

        // 获取卡片统计数据
        InspectUserChartRespVO.CardData cardData = inspectUserMapper.selectCardData();
        respVO.setCardData(cardData);

        return respVO;
    }

}