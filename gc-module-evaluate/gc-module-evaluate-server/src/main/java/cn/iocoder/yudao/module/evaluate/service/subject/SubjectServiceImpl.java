package cn.iocoder.yudao.module.evaluate.service.subject;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subject.SubjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subjecttype.SubjectTypeMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.user.UserMapper;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.SUBJECT_NOT_EXISTS;

/**
 * 评价主体 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;
//    @Resource
//    private SubjectMemberMapper subjectMemberMapper;
    @Override
    public Long createSubject(SubjectSaveReqVO createReqVO) {
        // 插入
        SubjectDO subject = BeanUtils.toBean(createReqVO, SubjectDO.class);
        //object.setObjectId(UUID.randomUUID().toString());
        subject.setSubjectId(UUID.randomUUID().toString());
        subjectMapper.insert(subject);

        // 返回
        return subject.getId();
    }

    @Override
    public void updateSubject(SubjectSaveReqVO updateReqVO) {
        // 校验存在
        validateSubjectExists(updateReqVO.getId());
        // 更新
        SubjectDO updateObj = BeanUtils.toBean(updateReqVO, SubjectDO.class);
        subjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubject(Long id) {
        // 校验存在
        validateSubjectExists(id);
        // 删除
        subjectMapper.deleteById(id);
    }

    @Override
        public void deleteSubjectListByIds(List<Long> ids) {
        // 删除
        subjectMapper.deleteByIds(ids);
        }


    private void validateSubjectExists(Long id) {
        if (subjectMapper.selectById(id) == null) {
            throw exception(SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public SubjectDO getSubject(Long id) {
        return subjectMapper.selectById(id);
    }

    @Override
    public PageResult<SubjectDO> getSubjectPage(SubjectPageReqVO pageReqVO) {
        return subjectMapper.selectPage(pageReqVO);
    }
    // -------------------------- 新增联表查询 Service 方法 --------------------------
//    /**
//     * 联表分页查询（列表页展示用，含关联表名称字段）
//     * @param pageReqVO 分页+筛选参数
//     * @return 含联表字段的分页结果
//     */
//    @Override
//    public PageResult<SubjectRespVO> getSubjectPageWithJoin(SubjectPageReqVO pageReqVO) {
//        // 复用原有筛选条件逻辑
//        LambdaQueryWrapperX<SubjectDO> queryWrapper = new LambdaQueryWrapperX<SubjectDO>()
//                .eqIfPresent(SubjectDO::getSubjectId, pageReqVO.getSubjectId())
//                .likeIfPresent(SubjectDO::getName, pageReqVO.getName()) // 支持模糊查询
//                .eqIfPresent(SubjectDO::getCode, pageReqVO.getCode())
//                .eqIfPresent(SubjectDO::getSubjectTypeId, pageReqVO.getSubjectTypeId())
//                .eqIfPresent(SubjectDO::getContactId, pageReqVO.getContactId())
//                .eqIfPresent(SubjectDO::getStatusId, pageReqVO.getStatusId())
//                .betweenIfPresent(SubjectDO::getBizCreateTime, pageReqVO.getBizCreateTime())
//                .betweenIfPresent(SubjectDO::getCreateTime, pageReqVO.getCreateTime());
//
//        // 执行联表分页查询
//        Page<SubjectRespVO> page = subjectMapper.selectSubjectPageWithJoin(
//                Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize()), queryWrapper);
//        return new PageResult<>(page.getRecords(), page.getTotal());
//    }
//
//    /**
//     * 联表查询主体详情（含成员列表）
//     * @param detailReqVO 主体ID参数
//     * @return 完整详情（人工主体含成员列表）
//     */
//    @Override
//    public SubjectRespVO getSubjectDetailWithJoin(SubjectDetailReqVO detailReqVO) {
//        // 1. 查询主体基础联表信息
//        SubjectRespVO subjectDetail = subjectMapper.selectSubjectDetailWithJoin(detailReqVO.getSubjectId());
//        if (subjectDetail == null) {
//            throw exception(SUBJECT_NOT_EXISTS);
//        }
//
//        // 2. 若为人工主体，查询成员列表（通过主体类型名称判断，或查询sys_subject_type确认）
//        if ("人工主体".equals(subjectDetail.getSubjectTypeName())) {
//            List<SubjectRespVO.SubjectMemberRespVO> memberList = subjectMapper.selectSubjectMemberList(detailReqVO.getSubjectId());
//            subjectDetail.setMemberList(memberList);
//        }
//
//        return subjectDetail;
//    }
//
//    /**
//     * 查询评价主体统计指标
//     * @return 统计结果（总数量、人工/系统主体数、启用数）
//     */
//    @Override
//    public SubjectStatRespVO getSubjectStat() {
//        return subjectMapper.selectSubjectStat();
//    }
    @Override
    public PageResult<SubjectRespVO> getSubjectJoinPage(SubjectPageReqVO reqVO) {
        return subjectMapper.selectSubjectJoinPage(reqVO);
    }
        //


    // 辅助判断方法
//    private boolean isManualSubject(SubjectRespVO vo) {
//        // 根据你的业务判断，比如通过 subjectTypeName 或者 subjectTypeId
//        // 示例：return "人工主体".equals(vo.getSubjectTypeName());
//        return true; // 这里先默认都返回，你自己加判断
//    }



    /**
     * 公共方法：构造基础查询条件（过滤deleted=1）
     * @return LambdaQueryWrapper<SubjectDO>
     */
    private LambdaQueryWrapper<SubjectDO> getBaseWrapper() {
        // 固定过滤deleted=1的记录，所有统计都基于这个基础条件
        return new LambdaQueryWrapper<SubjectDO>()
                .ne(SubjectDO::getDeleted, 1);
    }
    @Override
    public SubjectRespVO getStatusCount(Integer statusId) {
        SubjectRespVO respVO = new SubjectRespVO();

        // 1. 构造基础条件 + 动态拼接statusId
        LambdaQueryWrapper<SubjectDO> baseWrapper = getBaseWrapper();
        if (statusId != null) {
            baseWrapper.eq(SubjectDO::getStatusId, statusId);
        }

        // 2. 统计总数量（核心修改：count → selectCount）
        Long totalCount = subjectMapper.selectCount(baseWrapper);
        respVO.setTotalCount(totalCount);

        // 3. 统计status_id=1的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<SubjectDO> status1Wrapper = baseWrapper.clone();
        status1Wrapper.eq(SubjectDO::getStatusId, 1);
        respVO.setStatus1Count(subjectMapper.selectCount(status1Wrapper));

        // 4. 统计status_id=2的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<SubjectDO> status2Wrapper = baseWrapper.clone();
        status2Wrapper.eq(SubjectDO::getStatusId, 2);
        respVO.setStatus2Count(subjectMapper.selectCount(status2Wrapper));

        return respVO;
    }
    @Resource
    private UserMapper sysUserMapper;
    @Resource
    private SubjectTypeMapper sysSubjectTypeMapper;
//    @Resource
//    private StatusMapper sysStatusMapper;

    @Override
    public List<SubjectImportRespVO> importSubjectExcel(MultipartFile file) {
        // 1. 读取 Excel
        List<SubjectImportExcelVO> excelList;
        try {
            excelList = EasyExcel.read(file.getInputStream()).head(SubjectImportExcelVO.class).sheet().doReadSync();
        } catch (IOException e) {
            throw new RuntimeException("Excel 解析失败", e);
        }

        if (CollUtil.isEmpty(excelList)) {
            throw new RuntimeException("Excel 数据为空");
        }

        List<SubjectImportRespVO> resultList = new ArrayList<>();
        List<SubjectDO> toSaveList = new ArrayList<>();

        // 2. 预加载缓存（优化性能：避免循环查数据库）
        // 提取所有需要查询的关联名称
        Set<String> subjectTypeNames = excelList.stream().map(SubjectImportExcelVO::getSubjectTypeName).collect(Collectors.toSet());
        Set<String> userNames = excelList.stream().map(SubjectImportExcelVO::getContactUserName).collect(Collectors.toSet());
        // ... 可以把成员列表、创建人也加进来

// 批量查询主体类型，构建 名称->主键ID 的Map，自带防重名冲突处理
        Map<String, String> subjectTypeMap = sysSubjectTypeMapper.selectList(
                new LambdaQueryWrapperX<SubjectTypeDO>()
                        .in(SubjectTypeDO::getName, subjectTypeNames)
        ).stream().collect(Collectors.toMap(
                SubjectTypeDO::getName,
                SubjectTypeDO::getTypeId,
                (oldValue, newValue) -> oldValue // 防止重名导致Map报错，保留先出现的那条数据
        ));
// 批量查询系统用户，构建 用户名->用户完整对象 的Map
        Map<String, UserDO> userMap = sysUserMapper.selectList(
                new LambdaQueryWrapperX<UserDO>()
                        .in(UserDO::getUserName, userNames)
        ).stream().collect(Collectors.toMap(
                UserDO::getUserName,
                user -> user,
                (oldValue, newValue) -> oldValue
        ));
        // 3. 遍历校验每一行
        int lineNo = 1; // 行号，假设第一行是表头，数据从第2行开始
        for (SubjectImportExcelVO excelVO : excelList) {
            lineNo++;
            StringBuilder errorMsg = new StringBuilder();

            // --- 3.1 基础校验 ---
            if (StrUtil.isBlank(excelVO.getName())) errorMsg.append("主体名称为空;");
            if (StrUtil.isBlank(excelVO.getCode())) errorMsg.append("主体编码为空;");
            // ... 其他必填项校验

            // --- 3.2 关联数据校验 & 组装 DO ---
            SubjectDO subject = new SubjectDO();

            // 校验主体类型
            if (StrUtil.isNotBlank(excelVO.getSubjectTypeName())) {
                if (!subjectTypeMap.containsKey(excelVO.getSubjectTypeName())) {
                    errorMsg.append("主体类型不存在;");
                } else {
                    subject.setSubjectTypeId(subjectTypeMap.get(excelVO.getSubjectTypeName()));
                }
            }

            // 校验联系人 & 自动填充电话
            if (StrUtil.isNotBlank(excelVO.getContactUserName())) {
                if (!userMap.containsKey(excelVO.getContactUserName())) {
                    errorMsg.append("联系人不存在;");
                } else {
                    UserDO contact = userMap.get(excelVO.getContactUserName());
                    subject.setContactId(contact.getUserId());
                }
            }

            // ... 省略状态、创建人等其他关联校验，逻辑同上 ...

            // 处理变更日志截取
            if (StrUtil.isNotBlank(excelVO.getChangeLog())) {
                String log = excelVO.getChangeLog();
                subject.setChangeLog(log.length() > 50 ? log.substring(0, 50) : log);
            }

            // --- 3.3 判定结果 ---
            if (!errorMsg.isEmpty()) {
                // 失败
                resultList.add(new SubjectImportRespVO(lineNo, excelVO.getName(), false, errorMsg.toString()));
            } else {
                // 成功：补全基础信息
                BeanUtils.copyProperties(excelVO, subject);
                // 成员数量、成员列表的处理需要根据你的具体表结构来写
                // subject.setMemberCount(...);
                toSaveList.add(subject);
                resultList.add(new SubjectImportRespVO(lineNo, excelVO.getName(), true, "导入成功"));
            }
        }

        // 4. 批量入库 (只有校验通过的才保存)
        if (CollUtil.isNotEmpty(toSaveList)) {
            subjectMapper.insertBatch(toSaveList);
            // 注意：如果还需要保存成员列表(eval_subject_member)，这里需要再写逻辑
        }

        return resultList;
    }
    @Override
    public EvalSubjectOverviewVO getEvalSubjectOverview() {
        EvalSubjectOverviewVO vo = new EvalSubjectOverviewVO();

        // 1. 组装卡片核心数据
        EvalSubjectOverviewVO.CardData cardData = subjectMapper.selectCardCoreData();
        vo.setCardData(cardData);

        // 2. 组装圆环图数据
        List<EvalSubjectOverviewVO.PieChartItem> typePieChart = subjectMapper.selectTypePieChart();
        vo.setTypePieChart(typePieChart);

        List<EvalSubjectOverviewVO.PieChartItem> statusPieChart = subjectMapper.selectStatusPieChart();
        vo.setStatusPieChart(statusPieChart);

        // 3. 组装柱状图数据
        List<EvalSubjectOverviewVO.BarChartItem> memberCountBarChart = subjectMapper.selectMemberCountBarChart();
        vo.setMemberCountBarChart(memberCountBarChart);

        return vo;
    }
}