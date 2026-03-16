package cn.iocoder.yudao.module.evaluate.service.standardcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.standardcategory.StandardCategoryMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.STANDARD_CATEGORY_NOT_EXISTS;

/**
 * 标准分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Slf4j
@Validated
public class StandardCategoryServiceImpl implements StandardCategoryService {

    @Resource
    private StandardCategoryMapper standardCategoryMapper;

    @Override
    public Long createStandardCategory(StandardCategorySaveReqVO createReqVO) {
        // 插入
        StandardCategoryDO standardCategory = BeanUtils.toBean(createReqVO, StandardCategoryDO.class);
        standardCategoryMapper.insert(standardCategory);
        // 返回
        return standardCategory.getId();
    }

    @Override
    public void updateStandardCategory(StandardCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateStandardCategoryExists(updateReqVO.getId());
        // 更新
        StandardCategoryDO updateObj = BeanUtils.toBean(updateReqVO, StandardCategoryDO.class);
        standardCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteStandardCategory(Long id) {
        // 校验存在
        validateStandardCategoryExists(id);
        // 删除
        standardCategoryMapper.deleteById(id);
    }

    private void validateStandardCategoryExists(Long id) {
        if (standardCategoryMapper.selectById(id) == null) {
            throw exception(STANDARD_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public StandardCategoryDO getStandardCategory(Long id) {
        return standardCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<StandardCategoryDO> getStandardCategoryPage(StandardCategoryPageReqVO pageReqVO) {
        return standardCategoryMapper.selectPage(pageReqVO);
    }
    /**
     * 标准分类联表分页查询（支持钻取筛选）
     * @param reqVO 分页查询参数（含筛选、钻取条件）
     * @return 分页结果（包含所有联表展示字段）
     */
    @Override
    public PageResult<StandardCategoryRespVO> getStandardCategoryJoinPage(StandardCategoryPageReqVO reqVO) {
        // 1. 参数校验（可选，防止空指针/非法参数）
        if (reqVO == null) {
            log.warn("标准分类分页查询参数为空，使用默认分页参数");
            reqVO = new StandardCategoryPageReqVO();
        }
        // 2. 补充分页默认值（防止前端不传pageNo/pageSize）
        if (reqVO.getPageNo() == null || reqVO.getPageNo() < 1) {
            reqVO.setPageNo(1);
        }
        if (reqVO.getPageSize() == null || reqVO.getPageSize() < 1 || reqVO.getPageSize() > 100) {
            reqVO.setPageSize(10); // 限制最大页大小，防止查询过多数据
        }
        // 3. 调用Mapper层联表查询方法
        try {
            PageResult<StandardCategoryRespVO> pageResult = standardCategoryMapper.selectStandardCategoryJoinPage(reqVO);
            log.info("标准分类联表分页查询成功，页码：{}，页大小：{}，总条数：{}",
                    reqVO.getPageNo(), reqVO.getPageSize(), pageResult.getTotal());
            return pageResult;
        } catch (Exception e) {
            log.error("标准分类联表分页查询失败，参数：{}", reqVO, e);
            throw new RuntimeException("标准分类查询失败"); // 也可抛自定义业务异常
        }
    }
}