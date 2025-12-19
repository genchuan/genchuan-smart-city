<template>
  <div class="global-filter-container" :class="{ 'filter-updating': isUpdating }">
    <!-- 顶部筛选条件配置区 -->
    <div class="filter-bar">
      <!-- 左侧筛选条件配置 -->
      <div class="filter-conditions">
        <!-- 时间范围 -->
        <div class="filter-item time-range-item">
          <label class="filter-label">时间范围</label>
          <div class="filter-control">
            <div class="time-range-selector">
              <select
                v-model="currentConditions.timeRange.type"
                @change="handleTimeRangeChange"
                class="time-select"
              >
                <option v-for="type in TIME_RANGE_TYPES" :key="type.value" :value="type.value">
                  {{ type.label }}
                </option>
              </select>
              <div v-if="showTimeRangeInput" class="time-range-input">
                <input
                  v-if="currentConditions.timeRange.type === 'lastNHours'"
                  v-model.number="currentConditions.timeRange.value"
                  type="number"
                  min="1"
                  max="720"
                  placeholder="小时"
                  @input="handleTimeRangeInput"
                  class="hour-input"
                />
                <div v-else-if="currentConditions.timeRange.type === 'custom'" class="custom-date-inputs">
                  <input
                    v-model="currentConditions.timeRange.startTime"
                    type="date"
                    @change="handleCustomDateChange"
                    class="date-input"
                  />
                  <span class="date-separator">至</span>
                  <input
                    v-model="currentConditions.timeRange.endTime"
                    type="date"
                    @change="handleCustomDateChange"
                    class="date-input"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 区域范围 -->
        <div class="filter-item area-range-item">
          <label class="filter-label">区域范围</label>
          <div class="filter-control">
            <div class="area-selector">
              <select
                v-model="currentConditions.areaRange.type"
                @change="handleAreaRangeChange"
                class="area-select"
              >
                <option v-for="type in AREA_RANGE_TYPES" :key="type.value" :value="type.value">
                  {{ type.label }}
                </option>
              </select>
              <div v-if="showAreaCodes" class="area-codes-input">
                <input
                  v-model="areaCodesText"
                  placeholder="区域编码（多个用逗号分隔）"
                  @change="handleAreaCodesChange"
                  class="codes-input"
                />
              </div>
            </div>
          </div>
        </div>

        <!-- 数据类型 -->
        <div class="filter-item data-type-item">
          <label class="filter-label">数据类型</label>
          <div class="filter-control">
            <div class="checkbox-group">
              <label v-for="option in dataTypeOptions" :key="option.value" class="checkbox-label">
                <input
                  type="checkbox"
                  :value="option.value"
                  v-model="currentConditions.dataType"
                  @change="handleDataTypeChange"
                  :disabled="option.disabled"
                  class="checkbox-input"
                />
                <span class="checkbox-text">{{ option.label }}</span>
              </label>
            </div>
          </div>
        </div>

        <!-- 状态类型 -->
        <div class="filter-item status-type-item">
          <label class="filter-label">状态类型</label>
          <div class="filter-control">
            <div class="checkbox-group">
              <label v-for="option in statusTypeOptions" :key="option.value" class="checkbox-label">
                <input
                  type="checkbox"
                  :value="option.value"
                  v-model="currentConditions.statusType"
                  @change="handleStatusTypeChange"
                  :disabled="option.disabled"
                  class="checkbox-input"
                />
                <span class="checkbox-text">{{ option.label }}</span>
              </label>
            </div>
          </div>
        </div>
      </div>

      <!-- 右侧操作区 -->
      <div class="filter-actions">
        <!-- 操作按钮组 -->
        <div class="action-buttons">
          <button
            class="action-btn save-btn"
            :disabled="loading || FilterUtils.isEmptyConditions(currentConditions)"
            @click="handleSaveFilter"
            title="保存当前筛选条件"
          >
            <span class="btn-icon">💾</span>
            <span class="btn-text">保存</span>
          </button>

          <div class="dropdown-wrapper">
            <button
              class="action-btn recall-btn"
              @click="toggleRecallDropdown"
              title="召回已保存的筛选条件"
            >
              <span class="btn-icon">📥</span>
              <span class="btn-text">召回</span>
              <span class="dropdown-arrow">▼</span>
            </button>
            <div v-if="showRecallDropdown" class="dropdown-menu recall-dropdown">
              <div class="dropdown-header">
                <span>最近使用的筛选条件</span>
                <span class="count-badge">{{ recallOptions.length }}</span>
              </div>
              <div class="dropdown-body">
                <div
                  v-for="item in recallOptions"
                  :key="item.value"
                  class="dropdown-item"
                  @click="handleRecallFilter(item)"
                >
                  <div class="item-name">{{ item.content }}</div>
                  <div class="item-meta">
                    <span class="item-category">{{ item.category }}</span>
                    <span class="item-count">使用: {{ item.useCount }}</span>
                  </div>
                </div>
              </div>
              <div class="dropdown-footer" @click="showFilterManager = true">
                管理所有筛选条件 →
              </div>
            </div>
          </div>

          <button
            class="action-btn clear-btn"
            @click="handleClearFilter"
            title="清空所有筛选条件"
          >
            <span class="btn-icon">🗑️</span>
            <span class="btn-text">清空</span>
          </button>

          <button
            class="action-btn manage-btn"
            @click="showFilterManager = true"
            title="筛选条件管理"
          >
            <span class="btn-icon">⚙️</span>
            <span class="btn-text">管理</span>
          </button>
        </div>

        <!-- 筛选结果计数 -->
        <div class="result-counter">
          <div class="counter-content">
            <div class="counter-label">筛选结果</div>
            <div class="counter-value">{{ formatResultCount }}</div>
          </div>
          <div class="counter-refresh">
            <button
              @click="calculateResultCount"
              :disabled="loading"
              class="refresh-btn"
              title="重新计算"
            >
              🔄
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 已选条件标签展示区 -->
    <div class="selected-tags-container" v-if="selectedTags.length > 0">
      <div class="tags-header">
        <div class="tags-info">
          <span class="tags-icon">🏷️</span>
          <span class="tags-title">已选条件</span>
          <span class="tags-count">{{ selectedTags.length }}项</span>
        </div>
        <button
          class="clear-all-btn"
          @click="clearAllTags"
          title="清除所有已选条件"
        >
          全部清除
        </button>
      </div>
      <div class="selected-tags">
        <transition-group name="tag-slide">
          <div
            v-for="tag in selectedTags"
            :key="tag.id"
            class="tag"
            @click="handleTagClick(tag)"
          >
            <span class="tag-icon">{{ getTagIcon(tag.type) }}</span>
            <span class="tag-text">{{ tag.text }}</span>
            <button
              class="tag-close"
              @click.stop="removeTag(tag)"
              title="删除此条件"
            >
              ×
            </button>
          </div>
        </transition-group>
      </div>
    </div>

    <!-- 筛选结果示例区域 -->
    <div class="results-demo-section">
      <div class="results-header">
        <div class="header-left">
          <h3 class="results-title">筛选结果示例</h3>
          <div class="results-subtitle">基于当前筛选条件生成的模拟数据</div>
        </div>
        <div class="header-right">
          <button
            class="results-toggle"
            @click="toggleResultsDemo"
            :title="showResultsDemo ? '隐藏结果' : '显示结果'"
          >
            <span class="toggle-icon">{{ showResultsDemo ? '▼' : '▶' }}</span>
            <span class="toggle-text">{{ showResultsDemo ? '收起' : '展开' }}</span>
          </button>
        </div>
      </div>

      <div v-if="showResultsDemo" class="results-demo-content">
        <!-- 结果概览 -->
        <div class="results-overview">
          <div class="overview-stats">
            <div class="stat-item" v-for="stat in resultStats" :key="stat.label">
              <div class="stat-icon" :style="{ color: stat.color }">{{ stat.icon }}</div>
              <div class="stat-content">
                <div class="stat-value">{{ stat.value }}</div>
                <div class="stat-label">{{ stat.label }}</div>
              </div>
            </div>
          </div>

          <!-- 结果分组导航 -->
          <div class="results-navigation">
            <div
              v-for="group in filteredResults.groups"
              :key="group.type"
              class="nav-item"
              :class="{ active: activeResultGroup === group.type }"
              @click="activeResultGroup = group.type"
            >
              <div class="nav-icon" :style="{ backgroundColor: group.color }">
                {{ group.icon }}
              </div>
              <div class="nav-content">
                <div class="nav-label">{{ group.label }}</div>
                <div class="nav-count">{{ group.count }}项</div>
              </div>
            </div>
          </div>
        </div>

        <!-- 结果详情 -->
        <div class="results-detail">
          <div class="detail-header">
            <div class="header-title">
              <span class="title-icon">{{ getActiveGroupIcon }}</span>
              <span class="title-text">{{ getActiveGroupLabel }}</span>
              <span class="title-count">{{ getActiveGroupCount }}项</span>
            </div>
            <div class="header-actions">
              <button class="action-btn export-btn" @click="exportResults">
                <span class="btn-icon">📥</span>
                <span class="btn-text">导出</span>
              </button>
              <button class="action-btn refresh-btn" @click="refreshResults">
                <span class="btn-icon">🔄</span>
                <span class="btn-text">刷新</span>
              </button>
            </div>
          </div>

          <!-- 结果列表 -->
          <div class="results-list">
            <div class="list-container" ref="listContainer">
              <div
                v-for="item in getActiveGroupItems"
                :key="item.id"
                class="result-item"
                :class="`status-${item.status}`"
              >
                <div class="item-header">
                  <div class="item-icon" :style="{ color: item.color }">
                    {{ item.icon || '📄' }}
                  </div>
                  <div class="item-title">
                    <h4 class="title-main">{{ item.name }}</h4>
                    <div class="title-sub">{{ item.time }} · {{ item.area }}</div>
                  </div>
                  <div class="item-status">
                    <span class="status-badge" :style="{ backgroundColor: getStatusColor(item.status) }">
                      {{ getStatusLabel(item.status) }}
                    </span>
                  </div>
                </div>

                <div class="item-body">
                  <div class="item-meta">
                    <div class="meta-tags">
                      <span
                        v-for="tag in item.tags"
                        :key="tag"
                        class="meta-tag"
                      >
                        {{ tag }}
                      </span>
                    </div>
                    <div class="meta-value" v-if="item.value">
                      <span class="value-number">{{ item.value }}</span>
                      <span class="value-unit">{{ item.unit }}</span>
                    </div>
                  </div>

                  <div class="item-actions">
                    <button class="action-icon detail-btn" @click="showDetail(item)">
                      <span>🔍</span>
                      <span class="action-text">详情</span>
                    </button>
                    <button class="action-icon map-btn" @click="showOnMap(item)">
                      <span>🗺️</span>
                      <span class="action-text">地图</span>
                    </button>
                    <button class="action-icon chart-btn" @click="showChart(item)">
                      <span>📈</span>
                      <span class="action-text">图表</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 分页 -->
          <div v-if="showPagination" class="results-pagination">
            <div class="pagination-info">
              显示 {{ startIndex + 1 }}-{{ endIndex }} 条，共 {{ getActiveGroupCount }} 条
            </div>
            <div class="pagination-controls">
              <button
                class="page-btn prev-btn"
                :disabled="currentPage === 1"
                @click="currentPage--"
              >
                上一页
              </button>
              <div class="page-numbers">
                <button
                  v-for="page in visiblePages"
                  :key="page"
                  class="page-number"
                  :class="{ active: page === currentPage }"
                  @click="currentPage = page"
                >
                  {{ page }}
                </button>
                <span v-if="showEllipsis" class="page-ellipsis">...</span>
              </div>
              <button
                class="page-btn next-btn"
                :disabled="currentPage === totalPages"
                @click="currentPage++"
              >
                下一页
              </button>
            </div>
          </div>

          <!-- 空状态 -->
          <div v-if="getActiveGroupCount === 0" class="empty-results">
            <div class="empty-icon">📊</div>
            <div class="empty-title">暂无数据</div>
            <div class="empty-desc">
              当前筛选条件下未找到匹配的数据<br/>
              请尝试调整筛选条件
            </div>
            <button class="empty-action" @click="resetFilters">
              重置筛选条件
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 筛选条件管理对话框 -->
    <div v-if="showFilterManager" class="modal-overlay" @click.self="handleManagerClose">
      <div class="modal-content manager-modal">
        <div class="modal-header">
          <div class="header-left">
            <h3 class="modal-title">筛选条件管理</h3>
            <div class="modal-subtitle">共 {{ savedFilters.length }} 个筛选条件</div>
          </div>
          <div class="header-right">
            <button class="header-btn import-btn" @click="handleImportClick">
              <span class="btn-icon">📤</span>
              导入
            </button>
            <button class="header-btn export-btn" @click="handleExportClick">
              <span class="btn-icon">📥</span>
              导出
            </button>
            <button class="modal-close" @click="handleManagerClose">×</button>
          </div>
        </div>

        <div class="modal-body">
          <!-- 搜索和筛选 -->
          <div class="manager-toolbar">
            <div class="search-box">
              <input
                v-model="searchFilterName"
                placeholder="搜索筛选条件名称或描述..."
                @input="handleSearchFilter"
                class="search-input"
              />
              <span class="search-icon">🔍</span>
            </div>
            <div class="filter-controls">
              <select v-model="filterCategory" @change="handleCategoryFilter" class="category-select">
                <option value="">全部分类</option>
                <option v-for="cat in categoryOptions" :key="cat.value" :value="cat.value">
                  {{ cat.label }}
                </option>
              </select>
              <select v-model="sortBy" @change="handleSortChange" class="sort-select">
                <option value="updateTime_desc">更新时间↓</option>
                <option value="useCount_desc">使用次数↓</option>
                <option value="createTime_desc">创建时间↓</option>
              </select>
            </div>
          </div>

          <!-- 批量操作 -->
          <div v-if="selectedFilterIds.length > 0" class="batch-actions">
            <div class="batch-info">
              <span class="batch-count">已选中 {{ selectedFilterIds.length }} 项</span>
            </div>
            <div class="batch-buttons">
              <button
                class="batch-btn delete-btn"
                @click="handleBatchDelete"
                :disabled="batchDeleting"
              >
                <span class="btn-icon">🗑️</span>
                批量删除
              </button>
              <button
                class="batch-btn export-btn"
                @click="handleBatchExport"
                :disabled="batchExporting"
              >
                <span class="btn-icon">📥</span>
                导出选中
              </button>
            </div>
          </div>

          <!-- 筛选条件列表 -->
          <div class="filter-list-container">
            <div class="list-header">
              <div class="header-cell select-cell">
                <input
                  type="checkbox"
                  :checked="selectAllChecked"
                  :indeterminate="selectAllIndeterminate"
                  @change="handleSelectAll"
                  class="select-all-checkbox"
                />
              </div>
              <div class="header-cell name-cell">名称</div>
              <div class="header-cell category-cell">分类</div>
              <div class="header-cell status-cell">状态</div>
              <div class="header-cell count-cell">使用次数</div>
              <div class="header-cell time-cell">更新时间</div>
              <div class="header-cell action-cell">操作</div>
            </div>

            <div class="list-body">
              <template v-if="paginatedFilters.length > 0">
                <div
                  v-for="filter in paginatedFilters"
                  :key="filter.id"
                  class="filter-row"
                  :class="{
                    'row-selected': selectedFilterIds.includes(filter.id!),
                    'row-top': filter.isTop
                  }"
                >
                  <div class="row-cell select-cell">
                    <input
                      type="checkbox"
                      :checked="selectedFilterIds.includes(filter.id!)"
                      @change="handleFilterSelect(filter.id!, $event)"
                      class="row-checkbox"
                    />
                  </div>
                  <div class="row-cell name-cell">
                    <div class="filter-name">
                      <span v-if="filter.isTop" class="top-indicator">🔥</span>
                      <span class="name-text" :title="filter.name">{{ filter.name }}</span>
                      <div v-if="filter.description" class="filter-desc">
                        {{ filter.description }}
                      </div>
                    </div>
                  </div>
                  <div class="row-cell category-cell">
                    <span class="category-tag" :class="`category-${filter.category}`">
                      {{ getCategoryLabel(filter.category) }}
                    </span>
                  </div>
                  <div class="row-cell status-cell">
                    <div class="status-badges">
                      <span v-if="filter.isTop" class="status-badge top-badge" title="置顶">置顶</span>
                      <span v-if="filter.isShared" class="status-badge shared-badge" title="共享">共享</span>
                      <span v-if="filter.isPrivate" class="status-badge private-badge" title="私有">私有</span>
                    </div>
                  </div>
                  <div class="row-cell count-cell">
                    <div class="use-count">
                      <span class="count-number">{{ filter.useCount }}</span>
                    </div>
                  </div>
                  <div class="row-cell time-cell">
                    <div class="update-time">
                      {{ formatUpdateTime(filter.updateTime || filter.createTime) }}
                    </div>
                  </div>
                  <div class="row-cell action-cell">
                    <div class="action-buttons">
                      <button
                        class="action-icon recall-icon"
                        @click="handleRecallFilter(filter)"
                        title="召回"
                      >
                        📥
                      </button>
                      <button
                        class="action-icon top-icon"
                        @click="handleToggleTop(filter)"
                        :title="filter.isTop ? '取消置顶' : '置顶'"
                      >
                        {{ filter.isTop ? '⭐' : '☆' }}
                      </button>
                      <button
                        class="action-icon delete-icon"
                        @click="handleDeleteFilter(filter)"
                        title="删除"
                      >
                        🗑️
                      </button>
                    </div>
                  </div>
                </div>
              </template>
              <div v-else class="empty-state">
                <div class="empty-icon">🔍</div>
                <div class="empty-text">暂无筛选条件</div>
                <div class="empty-hint">点击"保存"按钮创建第一个筛选条件</div>
              </div>
            </div>
          </div>

          <!-- 分页 -->
          <div v-if="totalPages > 1" class="pagination-container">
            <div class="pagination">
              <button
                @click="currentPage--"
                :disabled="currentPage === 1"
                class="page-btn prev-btn"
              >
                上一页
              </button>
              <span class="page-info">
                第 {{ currentPage }} 页 / 共 {{ totalPages }} 页
              </span>
              <button
                @click="currentPage++"
                :disabled="currentPage === totalPages"
                class="page-btn next-btn"
              >
                下一页
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 保存筛选条件对话框 -->
    <div v-if="showSaveDialog" class="modal-overlay" @click.self="handleSaveCancel">
      <div class="modal-content save-modal">
        <div class="modal-header">
          <h3 class="modal-title">保存筛选条件</h3>
          <button class="modal-close" @click="handleSaveCancel">×</button>
        </div>
        <div class="modal-body">
          <div class="save-preview">
            <div class="preview-title">筛选条件预览</div>
            <div class="preview-content">
              <div class="preview-row">
                <span class="preview-label">时间范围:</span>
                <span class="preview-value">{{ FilterUtils.formatTimeRange(currentConditions.timeRange) }}</span>
              </div>
              <div class="preview-row">
                <span class="preview-label">区域范围:</span>
                <span class="preview-value">{{ FilterUtils.formatAreaRange(currentConditions.areaRange) }}</span>
              </div>
              <div class="preview-row">
                <span class="preview-label">数据类型:</span>
                <span class="preview-value">
                  {{ currentConditions.dataType.map(t => getDataTypeLabel(t)).join('、') }}
                </span>
              </div>
              <div class="preview-row">
                <span class="preview-label">状态类型:</span>
                <span class="preview-value">
                  {{ currentConditions.statusType.map(t => getStatusTypeLabel(t)).join('、') }}
                </span>
              </div>
            </div>
          </div>

          <div class="save-form">
            <div class="form-group">
              <label class="form-label required">名称</label>
              <input
                v-model="saveFormData.name"
                placeholder="请输入筛选条件名称（最多50字）"
                maxlength="50"
                class="form-input"
              />
              <div class="form-hint">{{ saveFormData.name.length }}/50</div>
            </div>

            <div class="form-group">
              <label class="form-label">分类</label>
              <div class="category-buttons">
                <button
                  v-for="cat in categoryOptions"
                  :key="cat.value"
                  @click="saveFormData.category = cat.value"
                  :class="{ 'active': saveFormData.category === cat.value }"
                  class="category-btn"
                >
                  {{ cat.label }}
                </button>
              </div>
            </div>

            <div class="form-group">
              <label class="form-label">设置</label>
              <div class="setting-options">
                <label class="setting-option">
                  <input
                    type="checkbox"
                    v-model="saveFormData.isTop"
                    class="setting-checkbox"
                  />
                  <span class="setting-text">置顶显示</span>
                </label>
                <label class="setting-option">
                  <input
                    type="checkbox"
                    v-model="saveFormData.isShared"
                    class="setting-checkbox"
                  />
                  <span class="setting-text">共享给他人</span>
                </label>
                <label class="setting-option">
                  <input
                    type="checkbox"
                    v-model="saveFormData.isPrivate"
                    class="setting-checkbox"
                  />
                  <span class="setting-text">设为私有</span>
                </label>
              </div>
            </div>

            <div class="form-group">
              <label class="form-label">描述</label>
              <textarea
                v-model="saveFormData.description"
                placeholder="请输入筛选条件描述（可选，最多200字）"
                maxlength="200"
                rows="3"
                class="form-textarea"
              ></textarea>
              <div class="form-hint">{{ saveFormData.description?.length || 0 }}/200</div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="footer-btn cancel-btn" @click="handleSaveCancel">取消</button>
          <button
            class="footer-btn save-btn"
            @click="handleSaveConfirm"
            :disabled="!saveFormData.name.trim() || loading"
          >
            {{ loading ? '保存中...' : '保存筛选条件' }}
          </button>
        </div>
      </div>
    </div>

    <!-- 详情对话框 -->
    <div v-if="selectedResultItem" class="detail-modal">
      <div class="modal-overlay" @click.self="selectedResultItem = null">
        <div class="modal-content">
          <div class="modal-header">
            <div class="header-left">
              <div class="item-icon" :style="{ color: selectedResultItem.color }">
                {{ selectedResultItem.icon }}
              </div>
              <div class="item-info">
                <h3 class="item-name">{{ selectedResultItem.name }}</h3>
                <div class="item-meta">
                  <span class="meta-item">{{ selectedResultItem.time }}</span>
                  <span class="meta-separator">·</span>
                  <span class="meta-item">{{ selectedResultItem.area }}</span>
                  <span class="meta-separator">·</span>
                  <span class="meta-item">{{ getDataTypeLabel(selectedResultItem.dataType) }}</span>
                </div>
              </div>
            </div>
            <button class="modal-close" @click="selectedResultItem = null">×</button>
          </div>

          <div class="modal-body">
            <div class="detail-sections">
              <div class="detail-section">
                <h4 class="section-title">基本信息</h4>
                <div class="section-content">
                  <div class="info-row">
                    <span class="info-label">状态：</span>
                    <span class="info-value">
                      <span
                        class="status-badge"
                        :style="{ backgroundColor: getStatusColor(selectedResultItem.status) }"
                      >
                        {{ getStatusLabel(selectedResultItem.status) }}
                      </span>
                    </span>
                  </div>
                  <div class="info-row">
                    <span class="info-label">数据类型：</span>
                    <span class="info-value">{{ getDataTypeLabel(selectedResultItem.dataType) }}</span>
                  </div>
                  <div class="info-row">
                    <span class="info-label">所属区域：</span>
                    <span class="info-value">{{ selectedResultItem.area }}</span>
                  </div>
                  <div class="info-row">
                    <span class="info-label">发生时间：</span>
                    <span class="info-value">{{ selectedResultItem.time }}</span>
                  </div>
                </div>
              </div>

              <div v-if="selectedResultItem.value" class="detail-section">
                <h4 class="section-title">数值信息</h4>
                <div class="section-content">
                  <div class="value-display">
                    <div class="value-number">{{ selectedResultItem.value }}</div>
                    <div class="value-unit">{{ selectedResultItem.unit }}</div>
                  </div>
                  <div class="value-comparison">
                    <div class="comparison-item">
                      <div class="comparison-label">较昨日</div>
                      <div class="comparison-value" :class="{ positive: Math.random() > 0.5 }">
                        {{ (Math.random() > 0.5 ? '+' : '-') }}{{ Math.floor(Math.random() * 20) }}%
                      </div>
                    </div>
                    <div class="comparison-item">
                      <div class="comparison-label">较上周</div>
                      <div class="comparison-value" :class="{ positive: Math.random() > 0.5 }">
                        {{ (Math.random() > 0.5 ? '+' : '-') }}{{ Math.floor(Math.random() * 30) }}%
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div v-if="selectedResultItem.tags && selectedResultItem.tags.length > 0" class="detail-section">
                <h4 class="section-title">标签</h4>
                <div class="section-content">
                  <div class="tags-container">
                    <span
                      v-for="tag in selectedResultItem.tags"
                      :key="tag"
                      class="detail-tag"
                    >
                      {{ tag }}
                    </span>
                  </div>
                </div>
              </div>

              <div class="detail-section">
                <h4 class="section-title">相关操作</h4>
                <div class="section-content">
                  <div class="action-buttons">
                    <button class="action-btn primary-btn" @click="handleResultAction('viewOnMap')">
                      <span class="btn-icon">🗺️</span>
                      <span class="btn-text">在地图查看</span>
                    </button>
                    <button class="action-btn secondary-btn" @click="handleResultAction('viewChart')">
                      <span class="btn-icon">📈</span>
                      <span class="btn-text">查看趋势</span>
                    </button>
                    <button class="action-btn secondary-btn" @click="handleResultAction('download')">
                      <span class="btn-icon">📥</span>
                      <span class="btn-text">下载数据</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch, onMounted, nextTick } from 'vue'

// 导入API接口
import {
  getFilterConditions,
  saveFilterCondition,
  recallFilterCondition,
  clearFilterConditions,
  batchDeleteFilters,
  updateFilterUseCount,
  toggleFilterTop,
  sessionStorageFilter,
  FILTER_CONSTANTS,
  getFilterResultCount,
  debounce,
  FilterUtils,
  type FilterCondition,
  type FilterConditions,
  type FilterQueryParams,
  type FilterResults,
  type FilterResultItem
} from '@/api/system/quick/globalFilter'

// 组件属性
const props = withDefaults(defineProps<{
  showIndustryFilter?: boolean
  enableRealtimeCount?: boolean
  enableAutoSave?: boolean
  defaultConditions?: FilterConditions
  showResultsDemo?: boolean
}>(), {
  showIndustryFilter: true,
  enableRealtimeCount: true,
  enableAutoSave: true,
  defaultConditions: () => FILTER_CONSTANTS.DEFAULT_CONDITIONS,
  showResultsDemo: true
})

// 组件事件
const emit = defineEmits<{
  (e: 'filterChange', conditions: FilterConditions): void
  (e: 'resultCount', count: number): void
  (e: 'filterSaved', filter: FilterCondition): void
  (e: 'filterRecalled', filter: FilterCondition): void
  (e: 'filterCleared'): void
  (e: 'resultItemClick', item: FilterResultItem): void
}>()

// 响应式状态
const currentConditions = ref<FilterConditions>(
  props.defaultConditions || sessionStorageFilter.get() || FILTER_CONSTANTS.DEFAULT_CONDITIONS
)
const savedFilters = ref<FilterCondition[]>([])
const selectedTags = ref<Array<{ id: string; text: string; type: string; value: any }>>([])
const resultCount = ref<number>(0)
const loading = ref<boolean>(false)
const isUpdating = ref<boolean>(false)
const showRecallDropdown = ref<boolean>(false)
const showFilterManager = ref<boolean>(false)
const showSaveDialog = ref<boolean>(false)
const selectedFilterIds = ref<string[]>([])
const searchFilterName = ref<string>('')
const filterCategory = ref<string>('')
const sortBy = ref<string>('updateTime_desc')
const currentPage = ref<number>(1)
const pageSize = ref<number>(10)
const batchDeleting = ref<boolean>(false)
const batchExporting = ref<boolean>(false)
const areaCodesText = ref<string>('')
const showResultsDemo = ref<boolean>(props.showResultsDemo)
const filteredResults = ref<FilterResults>({
  total: 0,
  groups: [],
  timestamp: new Date().toISOString()
})
const activeResultGroup = ref<string>('operating')
const currentResultPage = ref<number>(1)
const pageResultSize = ref<number>(10)
const selectedResultItem = ref<FilterResultItem | null>(null)
const listContainer = ref<HTMLElement | null>(null)

// 保存表单数据
const saveFormData = ref({
  name: '',
  category: 'common',
  description: '',
  isTop: false,
  isShared: false,
  isPrivate: true
})

// 计算属性
const TIME_RANGE_TYPES = computed(() => FILTER_CONSTANTS.TIME_RANGE_TYPES)
const AREA_RANGE_TYPES = computed(() => FILTER_CONSTANTS.AREA_RANGE_TYPES)
const DATA_TYPES = computed(() => FILTER_CONSTANTS.DATA_TYPES)
const STATUS_TYPES = computed(() => FILTER_CONSTANTS.STATUS_TYPES)
const CATEGORIES = computed(() => FILTER_CONSTANTS.CATEGORIES)

const showTimeRangeInput = computed(() =>
  ['lastNHours', 'custom'].includes(currentConditions.value.timeRange.type)
)

const showAreaCodes = computed(() =>
  ['district', 'street', 'community', 'grid'].includes(currentConditions.value.areaRange.type)
)

const dataTypeOptions = computed(() => {
  return DATA_TYPES.value.map(item => ({
    ...item,
    disabled: false
  }))
})

const statusTypeOptions = computed(() => {
  const currentDataTypes = currentConditions.value.dataType

  if (currentDataTypes.includes('event')) {
    const validStatus = FILTER_CONSTANTS.DATA_STATUS_MAPPING.event
    return STATUS_TYPES.value.map(item => ({
      ...item,
      disabled: !validStatus.includes(item.value)
    }))
  }

  if (currentDataTypes.includes('warning')) {
    const validStatus = FILTER_CONSTANTS.DATA_STATUS_MAPPING.warning
    return STATUS_TYPES.value.map(item => ({
      ...item,
      disabled: !validStatus.includes(item.value)
    }))
  }

  return STATUS_TYPES.value.map(item => ({
    ...item,
    disabled: false
  }))
})

const categoryOptions = computed(() => CATEGORIES.value)

const recallOptions = computed(() => {
  return savedFilters.value
    .sort((a, b) => {
      if (a.isTop !== b.isTop) return b.isTop ? 1 : -1
      if (a.useCount !== b.useCount) return b.useCount - a.useCount
      return new Date(b.updateTime || b.createTime).getTime() - new Date(a.updateTime || a.createTime).getTime()
    })
    .slice(0, 5)
    .map(filter => ({
      value: filter.id!,
      content: filter.name,
      category: getCategoryLabel(filter.category),
      useCount: filter.useCount,
      filter
    }))
})

const filteredFilters = computed(() => {
  let filtered = [...savedFilters.value]

  // 按名称搜索
  if (searchFilterName.value) {
    const keyword = searchFilterName.value.toLowerCase()
    filtered = filtered.filter(f =>
      f.name.toLowerCase().includes(keyword) ||
      (f.description && f.description.toLowerCase().includes(keyword))
    )
  }

  // 按分类筛选
  if (filterCategory.value) {
    filtered = filtered.filter(f => f.category === filterCategory.value)
  }

  // 排序
  filtered.sort((a, b) => {
    const [field, order] = sortBy.value.split('_')
    const aValue = field === 'updateTime' ? (a.updateTime || a.createTime) :
      field === 'useCount' ? a.useCount :
        field === 'createTime' ? a.createTime : ''
    const bValue = field === 'updateTime' ? (b.updateTime || b.createTime) :
      field === 'useCount' ? b.useCount :
        field === 'createTime' ? b.createTime : ''

    if (order === 'desc') {
      return new Date(bValue).getTime() - new Date(aValue).getTime()
    } else {
      return new Date(aValue).getTime() - new Date(bValue).getTime()
    }
  })

  return filtered
})

const totalPages = computed(() =>
  Math.ceil(filteredFilters.value.length / pageSize.value)
)

const paginatedFilters = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return filteredFilters.value.slice(start, end)
})

const selectAllChecked = computed(() => {
  return paginatedFilters.value.length > 0 &&
    paginatedFilters.value.every(f => selectedFilterIds.value.includes(f.id!))
})

const selectAllIndeterminate = computed(() => {
  const selectedCount = paginatedFilters.value.filter(f => selectedFilterIds.value.includes(f.id!)).length
  return selectedCount > 0 && selectedCount < paginatedFilters.value.length
})

const formatResultCount = computed(() => {
  if (resultCount.value === 0) return '0条'
  if (resultCount.value >= 10000) return `${(resultCount.value / 10000).toFixed(1)}万+`
  if (resultCount.value >= 1000) return `${(resultCount.value / 1000).toFixed(1)}千+`
  return `${resultCount.value.toLocaleString()}条`
})

// 筛选结果相关的计算属性
const resultStats = computed(() => [
  { icon: '📊', label: '总数', value: filteredResults.value.total, color: '#409eff' },
  { icon: '✅', label: '正常', value: filteredResults.value.summary?.normal || 0, color: '#67c23a' },
  { icon: '⚠️', label: '预警', value: filteredResults.value.summary?.warning || 0, color: '#e6a23c' },
  { icon: '🔧', label: '处置中', value: filteredResults.value.summary?.handling || 0, color: '#f56c6c' }
])

const getActiveGroup = computed(() => {
  return filteredResults.value.groups.find(g => g.type === activeResultGroup.value)
})

const getActiveGroupIcon = computed(() => {
  return getActiveGroup.value?.icon || '📊'
})

const getActiveGroupLabel = computed(() => {
  return getActiveGroup.value?.label || '数据'
})

const getActiveGroupCount = computed(() => {
  return getActiveGroup.value?.count || 0
})

const getActiveGroupItems = computed(() => {
  return getActiveGroup.value?.items || []
})

const totalResultPages = computed(() => {
  return Math.ceil(getActiveGroupCount.value / pageResultSize.value)
})

const startIndex = computed(() => {
  return (currentResultPage.value - 1) * pageResultSize.value
})

const endIndex = computed(() => {
  return Math.min(startIndex.value + pageResultSize.value, getActiveGroupCount.value)
})

const showPagination = computed(() => {
  return getActiveGroupCount.value > pageResultSize.value
})

const visiblePages = computed(() => {
  const pages: number[] = []
  const maxVisible = 5

  if (totalResultPages.value <= maxVisible) {
    for (let i = 1; i <= totalResultPages.value; i++) {
      pages.push(i)
    }
  } else {
    const start = Math.max(1, currentResultPage.value - 2)
    const end = Math.min(totalResultPages.value, start + maxVisible - 1)

    for (let i = start; i <= end; i++) {
      pages.push(i)
    }
  }

  return pages
})

const showEllipsis = computed(() => {
  return totalResultPages.value > visiblePages.value.length
})

// 标签类型图标映射
const tagIconMap: Record<string, string> = {
  time: '🕒',
  area: '📍',
  dataType: '📊',
  status: '🚩',
  industry: '🏭'
}

// 模拟数据示例 - 在实际项目中可以删除这些并使用真实API
const mockFilters: FilterCondition[] = [
  {
    id: '1',
    name: '今日全域数据',
    category: 'common',
    isShared: false,
    isPrivate: true,
    isTop: true,
    conditions: {
      timeRange: { type: 'today' },
      areaRange: { type: 'all' },
      dataType: ['all'],
      statusType: ['all'],
      industryParams: {},
      customParams: {}
    },
    createTime: new Date().toISOString(),
    useCount: 15,
    updateTime: new Date().toISOString(),
    description: '今日所有区域的全部数据'
  },
  {
    id: '2',
    name: '近7天预警数据',
    category: 'personal',
    isShared: false,
    isPrivate: true,
    isTop: false,
    conditions: {
      timeRange: { type: 'last7' },
      areaRange: { type: 'district' },
      dataType: ['warning'],
      statusType: ['warning'],
      industryParams: {},
      customParams: {}
    },
    createTime: new Date(Date.now() - 86400000).toISOString(),
    useCount: 8,
    updateTime: new Date(Date.now() - 43200000).toISOString(),
    description: '近7天区县级预警数据'
  },
  {
    id: '3',
    name: '本月事件处理',
    category: 'department',
    isShared: true,
    isPrivate: false,
    isTop: true,
    conditions: {
      timeRange: { type: 'currentMonth' },
      areaRange: { type: 'all' },
      dataType: ['event'],
      statusType: ['handling', 'completed'],
      industryParams: {},
      customParams: {}
    },
    description: '用于监控本月事件处理情况',
    createTime: new Date(Date.now() - 172800000).toISOString(),
    useCount: 23,
    updateTime: new Date(Date.now() - 21600000).toISOString()
  },
  {
    id: '4',
    name: '近24小时运行数据',
    category: 'project',
    isShared: true,
    isPrivate: false,
    isTop: false,
    conditions: {
      timeRange: { type: 'lastNHours', value: 24 },
      areaRange: { type: 'all' },
      dataType: ['operating'],
      statusType: ['normal', 'abnormal'],
      industryParams: {},
      customParams: {}
    },
    description: '近24小时系统运行状态监控',
    createTime: new Date(Date.now() - 259200000).toISOString(),
    useCount: 12,
    updateTime: new Date(Date.now() - 86400000).toISOString()
  },
  {
    id: '5',
    name: '街道级资源统计',
    category: 'common',
    isShared: false,
    isPrivate: true,
    isTop: false,
    conditions: {
      timeRange: { type: 'currentMonth' },
      areaRange: { type: 'street', codes: ['110101001', '110101002'] },
      dataType: ['resource', 'statistical'],
      statusType: ['all'],
      industryParams: {},
      customParams: {}
    },
    description: '街道级资源数据统计',
    createTime: new Date(Date.now() - 345600000).toISOString(),
    useCount: 5,
    updateTime: new Date(Date.now() - 129600000).toISOString()
  }
]

// 方法定义
/**
 * 生成模拟筛选结果
 */
const generateMockResults = (): FilterResults => {
  // 基于当前筛选条件生成模拟数据
  const { timeRange, areaRange, dataType, statusType } = currentConditions.value

  // 计算基础数量
  let baseCount = 1000

  // 根据时间范围调整
  if (timeRange.type === 'last7') baseCount *= 3
  if (timeRange.type === 'last30') baseCount *= 10
  if (timeRange.type === 'currentMonth') baseCount *= 12
  if (timeRange.type === 'lastMonth') baseCount *= 8
  if (timeRange.type === 'lastNHours' && typeof timeRange.value === 'number') {
    baseCount *= timeRange.value / 24
  }

  // 根据区域范围调整
  if (areaRange.type === 'district') baseCount *= 0.3
  if (areaRange.type === 'street') baseCount *= 0.1
  if (areaRange.type === 'community') baseCount *= 0.05
  if (areaRange.type === 'grid') baseCount *= 0.02
  if (areaRange.codes && areaRange.codes.length > 0) {
    baseCount *= (areaRange.codes.length * 0.1)
  }

  // 根据数据类型调整
  const dataTypeCount = dataType.filter(t => t !== 'all').length
  if (dataTypeCount === 1) baseCount *= 0.2
  if (dataTypeCount === 2) baseCount *= 0.4
  if (dataTypeCount === 3) baseCount *= 0.6
  if (dataTypeCount >= 4) baseCount *= 0.8

  // 根据状态类型调整
  const statusTypeCount = statusType.filter(t => t !== 'all').length
  if (statusTypeCount === 1) baseCount *= 0.3
  if (statusTypeCount === 2) baseCount *= 0.5
  if (statusTypeCount >= 3) baseCount *= 0.7

  // 生成组数据
  const groups: FilterResultGroup[] = []

  // 运行数据组
  if (dataType.includes('all') || dataType.includes('operating')) {
    const count = Math.floor(baseCount * 0.3)
    groups.push({
      type: 'operating',
      label: '运行数据',
      icon: '⚙️',
      color: '#409eff',
      count,
      items: Array.from({ length: Math.min(count, 10) }, (_, i) => ({
        id: `op_${Date.now()}_${i}`,
        name: `运行监控点${i + 1}`,
        dataType: 'operating',
        status: ['normal', 'abnormal'][Math.floor(Math.random() * 2)],
        area: getAreaLabel(areaRange.type),
        time: generateTimeLabel(timeRange.type),
        value: Math.floor(Math.random() * 100),
        unit: '台',
        icon: '⚙️',
        color: '#409eff',
        tags: ['设备监控', '实时数据', '运行状态']
      }))
    })
  }

  // 预警数据组
  if (dataType.includes('all') || dataType.includes('warning')) {
    const count = Math.floor(baseCount * 0.2)
    groups.push({
      type: 'warning',
      label: '预警数据',
      icon: '⚠️',
      color: '#e6a23c',
      count,
      items: Array.from({ length: Math.min(count, 8) }, (_, i) => ({
        id: `warn_${Date.now()}_${i}`,
        name: `预警事件${i + 1}`,
        dataType: 'warning',
        status: statusType.includes('all') || statusType.includes('warning') ? 'warning' : 'normal',
        area: getAreaLabel(areaRange.type),
        time: generateTimeLabel(timeRange.type),
        value: Math.floor(Math.random() * 1000),
        unit: '次',
        icon: '⚠️',
        color: '#e6a23c',
        tags: ['一级预警', '紧急处理', '监控中心']
      }))
    })
  }

  // 事件数据组
  if (dataType.includes('all') || dataType.includes('event')) {
    const count = Math.floor(baseCount * 0.25)
    groups.push({
      type: 'event',
      label: '事件数据',
      icon: '📝',
      color: '#f56c6c',
      count,
      items: Array.from({ length: Math.min(count, 12) }, (_, i) => ({
        id: `event_${Date.now()}_${i}`,
        name: `处置事件${i + 1}`,
        dataType: 'event',
        status: statusType.includes('all')
          ? ['handling', 'completed', 'untreated'][Math.floor(Math.random() * 3)]
          : statusType[Math.floor(Math.random() * statusType.length)],
        area: getAreaLabel(areaRange.type),
        time: generateTimeLabel(timeRange.type),
        value: Math.floor(Math.random() * 500),
        unit: '件',
        icon: '📝',
        color: '#f56c6c',
        tags: ['处置中', '紧急', '待处理']
      }))
    })
  }

  // 资源数据组
  if (dataType.includes('all') || dataType.includes('resource')) {
    const count = Math.floor(baseCount * 0.25)
    groups.push({
      type: 'resource',
      label: '资源数据',
      icon: '📦',
      color: '#67c23a',
      count,
      items: Array.from({ length: Math.min(count, 15) }, (_, i) => ({
        id: `res_${Date.now()}_${i}`,
        name: `资源点位${i + 1}`,
        dataType: 'resource',
        status: ['normal', 'abnormal'][Math.floor(Math.random() * 2)],
        area: getAreaLabel(areaRange.type),
        time: generateTimeLabel(timeRange.type),
        value: Math.floor(Math.random() * 1000),
        unit: '个',
        icon: '📦',
        color: '#67c23a',
        tags: ['资源管理', '库存', '调度']
      }))
    })
  }

  return {
    total: groups.reduce((sum, group) => sum + group.count, 0),
    groups,
    summary: {
      normal: Math.floor(baseCount * 0.6),
      abnormal: Math.floor(baseCount * 0.2),
      warning: Math.floor(baseCount * 0.1),
      handling: Math.floor(baseCount * 0.1)
    },
    timestamp: new Date().toISOString()
  }
}

/**
 * 获取区域标签
 */
const getAreaLabel = (type: string): string => {
  const config = AREA_RANGE_TYPES.value.find(t => t.value === type)
  return config?.label || type
}

/**
 * 生成时间标签
 */
const generateTimeLabel = (type: string): string => {
  const now = new Date()
  const formatDate = (date: Date) => date.toLocaleString('zh-CN', { month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' })

  switch (type) {
    case 'today':
      return formatDate(now)
    case 'last7':
      const last7 = new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000)
      return `${formatDate(last7)} - ${formatDate(now)}`
    case 'last30':
      const last30 = new Date(now.getTime() - 30 * 24 * 60 * 60 * 1000)
      return `${formatDate(last30)} - ${formatDate(now)}`
    case 'currentMonth':
      return `${now.getMonth() + 1}月`
    case 'lastMonth':
      const lastMonth = new Date(now.getFullYear(), now.getMonth() - 1, 1)
      return `${lastMonth.getMonth() + 1}月`
    default:
      return formatDate(now)
  }
}

/**
 * 加载保存的筛选条件
 */
const loadSavedFilters = async () => {
  try {
    loading.value = true
    const params: FilterQueryParams = {
      search: searchFilterName.value || undefined,
      category: filterCategory.value || undefined,
      sortBy: sortBy.value,
      page: currentPage.value,
      pageSize: pageSize.value
    }

    // 模拟API调用 - 实际项目中请使用真实API
    console.log('模拟调用 getFilterConditions:', params)

    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 500))

    // 使用模拟数据 - 实际项目中请使用以下代码：
    // const response = await getFilterConditions(params)
    // savedFilters.value = response.data

    // 使用模拟数据
    savedFilters.value = [...mockFilters]

  } catch (error) {
    console.error('加载筛选条件失败:', error)
    // 模拟出错时使用本地模拟数据
    savedFilters.value = [...mockFilters]
  } finally {
    loading.value = false
  }
}

/**
 * 生成已选条件标签
 */
const generateTags = () => {
  const tags: Array<{ id: string; text: string; type: string; value: any }> = []

  // 时间标签
  const timeText = FilterUtils.formatTimeRange(currentConditions.value.timeRange)
  if (currentConditions.value.timeRange.type !== 'today') {
    tags.push({
      id: 'time-tag',
      text: `时间: ${timeText}`,
      type: 'time',
      value: currentConditions.value.timeRange
    })
  }

  // 区域标签
  const areaText = FilterUtils.formatAreaRange(currentConditions.value.areaRange)
  if (currentConditions.value.areaRange.type !== 'all') {
    tags.push({
      id: 'area-tag',
      text: `区域: ${areaText}`,
      type: 'area',
      value: currentConditions.value.areaRange
    })
  }

  // 数据类型标签
  const dataTypes = currentConditions.value.dataType
    .filter(type => type !== 'all')
    .map(type => getDataTypeLabel(type))

  if (dataTypes.length > 0) {
    tags.push({
      id: 'data-type-tag',
      text: `数据类型: ${dataTypes.join('、')}`,
      type: 'dataType',
      value: currentConditions.value.dataType
    })
  }

  // 状态类型标签
  const statusTypes = currentConditions.value.statusType
    .filter(type => type !== 'all')
    .map(type => getStatusTypeLabel(type))

  if (statusTypes.length > 0) {
    tags.push({
      id: 'status-tag',
      text: `状态: ${statusTypes.join('、')}`,
      type: 'status',
      value: currentConditions.value.statusType
    })
  }

  selectedTags.value = tags
}

/**
 * 筛选条件变化处理
 */
const handleConditionChange = debounce(async (type: string, value: any) => {
  try {
    // 处理"全部"选项的互斥逻辑
    if (type === 'dataType' && Array.isArray(value)) {
      if (value.includes('all')) {
        currentConditions.value.dataType = ['all']
      } else if (value.length > 1 && value.includes('all')) {
        currentConditions.value.dataType = value.filter((v: string) => v !== 'all')
      }
    }

    if (type === 'statusType' && Array.isArray(value)) {
      if (value.includes('all')) {
        currentConditions.value.statusType = ['all']
      } else if (value.length > 1 && value.includes('all')) {
        currentConditions.value.statusType = value.filter((v: string) => v !== 'all')
      }
    }

    // 保存到会话存储
    if (props.enableAutoSave) {
      sessionStorageFilter.save(currentConditions.value)
    }

    // 触发外部事件
    emit('filterChange', currentConditions.value)

    // 计算筛选结果
    if (props.enableRealtimeCount) {
      await calculateResultCount()
    }

    // 更新筛选结果
    await refreshResults()

    // 更新标签显示
    generateTags()

    // 添加更新动画
    isUpdating.value = true
    setTimeout(() => {
      isUpdating.value = false
    }, 300)
  } catch (error) {
    console.error('筛选条件更新失败:', error)
  }
}, 300)

/**
 * 时间范围变化处理
 */
const handleTimeRangeChange = () => {
  handleConditionChange('timeRange', currentConditions.value.timeRange)
}

/**
 * 时间范围输入处理
 */
const handleTimeRangeInput = debounce(() => {
  handleConditionChange('timeRange', currentConditions.value.timeRange)
}, 500)

/**
 * 自定义日期变化处理
 */
const handleCustomDateChange = () => {
  if (currentConditions.value.timeRange.startTime && currentConditions.value.timeRange.endTime) {
    currentConditions.value.timeRange.value = [
      currentConditions.value.timeRange.startTime,
      currentConditions.value.timeRange.endTime
    ]
    handleConditionChange('timeRange', currentConditions.value.timeRange)
  }
}

/**
 * 区域范围变化处理
 */
const handleAreaRangeChange = () => {
  handleConditionChange('areaRange', currentConditions.value.areaRange)
}

/**
 * 区域编码变化处理
 */
const handleAreaCodesChange = () => {
  if (areaCodesText.value) {
    currentConditions.value.areaRange.codes = areaCodesText.value
      .split(',')
      .map(code => code.trim())
      .filter(code => code)
    handleConditionChange('areaRange', currentConditions.value.areaRange)
  }
}

/**
 * 数据类型变化处理
 */
const handleDataTypeChange = () => {
  handleConditionChange('dataType', currentConditions.value.dataType)
}

/**
 * 状态类型变化处理
 */
const handleStatusTypeChange = () => {
  handleConditionChange('statusType', currentConditions.value.statusType)
}

/**
 * 获取数据类型标签
 */
const getDataTypeLabel = (value: string) => {
  return DATA_TYPES.value.find(t => t.value === value)?.label || value
}

/**
 * 获取状态类型标签
 */
const getStatusTypeLabel = (value: string) => {
  return STATUS_TYPES.value.find(t => t.value === value)?.label || value
}

/**
 * 获取分类标签
 */
const getCategoryLabel = (value: string) => {
  return CATEGORIES.value.find(c => c.value === value)?.label || value
}

/**
 * 保存筛选条件
 */
const handleSaveFilter = () => {
  // 验证筛选条件是否为空
  if (FilterUtils.isEmptyConditions(currentConditions.value)) {
    alert('筛选条件为空，请先设置筛选条件')
    return
  }

  // 显示保存对话框
  saveFormData.value = {
    name: generateDefaultFilterName(),
    category: 'common',
    description: '',
    isTop: false,
    isShared: false,
    isPrivate: true
  }
  showSaveDialog.value = true
}

/**
 * 生成默认筛选条件名称
 */
const generateDefaultFilterName = (): string => {
  const now = new Date()
  const dateStr = `${now.getMonth() + 1}月${now.getDate()}日`

  const areaType = currentConditions.value.areaRange.type
  const areaLabel = AREA_RANGE_TYPES.value.find(t => t.value === areaType)?.label || areaType

  const dataTypes = currentConditions.value.dataType
    .filter(type => type !== 'all')
    .map(type => getDataTypeLabel(type))
    .join('、')

  const statusTypes = currentConditions.value.statusType
    .filter(type => type !== 'all')
    .map(type => getStatusTypeLabel(type))
    .join('、')

  const baseName = `${dateStr} ${areaLabel}`
  let typeName = ''

  if (dataTypes && statusTypes) {
    typeName = `${dataTypes} - ${statusTypes}`
  } else if (dataTypes) {
    typeName = dataTypes
  } else if (statusTypes) {
    typeName = statusTypes
  } else {
    typeName = '全部数据'
  }

  return `${baseName} ${typeName}`
}

/**
 * 保存确认
 */
const handleSaveConfirm = async () => {
  if (!saveFormData.value.name.trim()) {
    alert('请输入筛选条件名称')
    return
  }

  try {
    loading.value = true

    const saveData = {
      name: saveFormData.value.name,
      category: saveFormData.value.category,
      description: saveFormData.value.description || undefined,
      isShared: saveFormData.value.isShared,
      isPrivate: saveFormData.value.isPrivate,
      isTop: saveFormData.value.isTop,
      conditions: FilterUtils.cloneConditions(currentConditions.value)
    }

    // 模拟API调用 - 实际项目中请使用：
    // const savedFilter = await saveFilterCondition(saveData)

    console.log('模拟调用 saveFilterCondition:', saveData)

    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 500))

    // 创建模拟保存的筛选条件
    const savedFilter: FilterCondition = {
      id: Date.now().toString(),
      ...saveData,
      createTime: new Date().toISOString(),
      useCount: 0,
      updateTime: new Date().toISOString()
    }

    // 添加到列表
    savedFilters.value.unshift(savedFilter)
    showSaveDialog.value = false

    // 触发事件
    emit('filterSaved', savedFilter)

    // 显示成功消息
    alert('筛选条件保存成功！')
  } catch (error) {
    console.error('保存筛选条件失败:', error)
    alert('保存筛选条件失败')
  } finally {
    loading.value = false
  }
}

/**
 * 保存取消
 */
const handleSaveCancel = () => {
  showSaveDialog.value = false
}

/**
 * 切换召回下拉菜单
 */
const toggleRecallDropdown = () => {
  showRecallDropdown.value = !showRecallDropdown.value
  if (showRecallDropdown.value) {
    loadSavedFilters()
  }
}

/**
 * 召回筛选条件
 */
const handleRecallFilter = async (item: any) => {
  const filter = item.filter || item
  if (!filter.id) {
    alert('筛选条件ID不存在')
    return
  }

  try {
    loading.value = true
    showRecallDropdown.value = false

    // 模拟API调用 - 实际项目中请使用：
    // const recalledFilter = await recallFilterCondition(filter.id)

    console.log('模拟召回筛选条件:', filter.id)

    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 300))

    // 更新当前条件
    currentConditions.value = FilterUtils.cloneConditions(filter.conditions)

    // 更新使用次数
    filter.useCount = (filter.useCount || 0) + 1
    filter.updateTime = new Date().toISOString()

    // 触发更新动画
    isUpdating.value = true
    setTimeout(() => {
      isUpdating.value = false
    }, 500)

    // 触发条件变化
    await handleConditionChange('', currentConditions.value)

    // 触发事件
    emit('filterRecalled', filter)
  } catch (error) {
    console.error('召回筛选条件失败:', error)
    alert('召回筛选条件失败')
  } finally {
    loading.value = false
  }
}

/**
 * 清空筛选条件
 */
const handleClearFilter = () => {
  if (confirm('确定要清空所有筛选条件吗？')) {
    currentConditions.value = FilterUtils.cloneConditions(FILTER_CONSTANTS.DEFAULT_CONDITIONS)
    sessionStorageFilter.clear()
    handleConditionChange('', currentConditions.value)
    emit('filterCleared')
  }
}

/**
 * 计算结果数量
 */
const calculateResultCount = async () => {
  try {
    loading.value = true

    // 模拟API调用 - 实际项目中请使用：
    // const response = await getFilterResultCount(currentConditions.value)
    // resultCount.value = response.count

    console.log('模拟获取筛选结果计数:', currentConditions.value)

    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 500))

    // 模拟结果计算 - 基于当前条件生成随机数
    let baseCount = 1000

    // 根据时间范围调整
    if (currentConditions.value.timeRange.type === 'last7') baseCount *= 3
    if (currentConditions.value.timeRange.type === 'last30') baseCount *= 10
    if (currentConditions.value.timeRange.type === 'currentMonth') baseCount *= 12
    if (currentConditions.value.timeRange.type === 'lastMonth') baseCount *= 8

    // 根据区域范围调整
    if (currentConditions.value.areaRange.type === 'district') baseCount *= 0.3
    if (currentConditions.value.areaRange.type === 'street') baseCount *= 0.1
    if (currentConditions.value.areaRange.type === 'community') baseCount *= 0.05
    if (currentConditions.value.areaRange.type === 'grid') baseCount *= 0.02

    // 根据数据类型调整
    const dataTypeCount = currentConditions.value.dataType.filter(t => t !== 'all').length
    if (dataTypeCount === 1) baseCount *= 0.2
    if (dataTypeCount === 2) baseCount *= 0.4
    if (dataTypeCount === 3) baseCount *= 0.6

    // 根据状态类型调整
    const statusTypeCount = currentConditions.value.statusType.filter(t => t !== 'all').length
    if (statusTypeCount === 1) baseCount *= 0.3
    if (statusTypeCount === 2) baseCount *= 0.5

    // 添加随机性
    const randomFactor = 0.8 + Math.random() * 0.4 // 0.8-1.2
    const result = Math.floor(baseCount * randomFactor)

    resultCount.value = result
    emit('resultCount', result)
  } catch (error) {
    console.error('获取结果数量失败:', error)
    resultCount.value = 0
  } finally {
    loading.value = false
  }
}

/**
 * 标签点击处理
 */
const handleTagClick = (tag: any) => {
  console.log('标签点击:', tag)
  // 可以在这里实现标签点击的高亮或其他交互
}

/**
 * 移除标签
 */
const removeTag = (tag: any) => {
  switch (tag.type) {
    case 'time':
      currentConditions.value.timeRange = { type: 'today' }
      break
    case 'area':
      currentConditions.value.areaRange = { type: 'all' }
      break
    case 'dataType':
      currentConditions.value.dataType = ['all']
      break
    case 'status':
      currentConditions.value.statusType = ['all']
      break
  }

  handleConditionChange('', currentConditions.value)
}

/**
 * 清除所有标签
 */
const clearAllTags = () => {
  if (confirm('确定要清除所有已选条件吗？')) {
    currentConditions.value = FilterUtils.cloneConditions(FILTER_CONSTANTS.DEFAULT_CONDITIONS)
    handleConditionChange('', currentConditions.value)
  }
}

/**
 * 获取标签图标
 */
const getTagIcon = (type: string) => {
  return tagIconMap[type] || '🏷️'
}

/**
 * 搜索筛选条件
 */
const handleSearchFilter = debounce(() => {
  currentPage.value = 1
  loadSavedFilters()
}, 500)

/**
 * 分类筛选处理
 */
const handleCategoryFilter = () => {
  currentPage.value = 1
  loadSavedFilters()
}

/**
 * 排序变化处理
 */
const handleSortChange = () => {
  loadSavedFilters()
}

/**
 * 全选/取消全选
 */
const handleSelectAll = (event: Event) => {
  const checked = (event.target as HTMLInputElement).checked
  if (checked) {
    selectedFilterIds.value = paginatedFilters.value
      .map(filter => filter.id!)
      .filter(id => id)
  } else {
    selectedFilterIds.value = []
  }
}

/**
 * 单个筛选条件选择
 */
const handleFilterSelect = (id: string, event: Event) => {
  const checked = (event.target as HTMLInputElement).checked
  if (checked) {
    if (!selectedFilterIds.value.includes(id)) {
      selectedFilterIds.value.push(id)
    }
  } else {
    selectedFilterIds.value = selectedFilterIds.value.filter(item => item !== id)
  }
}

/**
 * 删除筛选条件
 */
const handleDeleteFilter = async (filter: FilterCondition) => {
  if (!filter.id) return

  if (confirm(`确定要删除筛选条件"${filter.name}"吗？`)) {
    try {
      // 模拟API调用 - 实际项目中请使用：
      // await batchDeleteFilters([filter.id])

      console.log('模拟删除筛选条件:', filter.id)

      // 从列表中移除
      savedFilters.value = savedFilters.value.filter(f => f.id !== filter.id)
      selectedFilterIds.value = selectedFilterIds.value.filter(id => id !== filter.id)

      alert('删除成功')
    } catch (error) {
      console.error('删除筛选条件失败:', error)
      alert('删除失败')
    }
  }
}

/**
 * 置顶/取消置顶筛选条件
 */
const handleToggleTop = async (filter: FilterCondition) => {
  if (!filter.id) return

  try {
    // 模拟API调用 - 实际项目中请使用：
    // await toggleFilterTop(filter.id, !filter.isTop)

    console.log('模拟切换置顶状态:', filter.id, !filter.isTop)

    // 更新状态
    filter.isTop = !filter.isTop
    filter.updateTime = new Date().toISOString()
  } catch (error) {
    console.error('置顶操作失败:', error)
  }
}

/**
 * 批量删除
 */
const handleBatchDelete = async () => {
  if (selectedFilterIds.value.length === 0) {
    alert('请先选择要删除的筛选条件')
    return
  }

  if (confirm(`确定要删除选中的 ${selectedFilterIds.value.length} 个筛选条件吗？`)) {
    try {
      batchDeleting.value = true

      // 模拟API调用 - 实际项目中请使用：
      // await batchDeleteFilters(selectedFilterIds.value)

      console.log('模拟批量删除筛选条件:', selectedFilterIds.value)

      // 模拟延迟
      await new Promise(resolve => setTimeout(resolve, 1000))

      // 从列表中移除
      savedFilters.value = savedFilters.value.filter(f => !selectedFilterIds.value.includes(f.id!))
      selectedFilterIds.value = []

      alert('批量删除成功！')
    } catch (error) {
      console.error('批量删除失败:', error)
      alert('批量删除失败')
    } finally {
      batchDeleting.value = false
    }
  }
}

/**
 * 批量导出
 */
const handleBatchExport = async () => {
  if (selectedFilterIds.value.length === 0) {
    alert('请先选择要导出的筛选条件')
    return
  }

  try {
    batchExporting.value = true

    const selectedFilters = savedFilters.value.filter(f => selectedFilterIds.value.includes(f.id!))

    // 创建导出数据
    const exportData = {
      version: '1.0',
      exportTime: new Date().toISOString(),
      count: selectedFilters.length,
      filters: selectedFilters.map(f => ({
        ...f,
        // 移除id和内部字段
        id: undefined,
        createdBy: undefined,
        conditions: FilterUtils.cloneConditions(f.conditions)
      }))
    }

    // 创建下载链接
    const dataStr = JSON.stringify(exportData, null, 2)
    const dataBlob = new Blob([dataStr], { type: 'application/json' })
    const url = URL.createObjectURL(dataBlob)

    const link = document.createElement('a')
    link.href = url
    const timestamp = new Date().toISOString().slice(0, 10).replace(/-/g, '')
    link.download = `筛选条件_${selectedFilters.length}条_${timestamp}.json`
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    URL.revokeObjectURL(url)

    alert('导出成功！文件已下载')
  } catch (error) {
    console.error('批量导出失败:', error)
    alert('批量导出失败')
  } finally {
    batchExporting.value = false
  }
}

/**
 * 导入筛选条件
 */
const handleImportClick = () => {
  // 创建文件输入框
  const input = document.createElement('input')
  input.type = 'file'
  input.accept = '.json'
  input.onchange = async (e: any) => {
    const file = e.target.files[0]
    if (!file) return

    try {
      loading.value = true
      const text = await file.text()
      const data = JSON.parse(text)

      // 验证数据格式
      if (!data.filters || !Array.isArray(data.filters)) {
        throw new Error('文件格式不正确')
      }

      // 导入筛选条件
      const importedFilters: FilterCondition[] = data.filters.map((f: any) => ({
        ...f,
        id: Date.now().toString() + Math.random().toString(36).substr(2, 9),
        createTime: new Date().toISOString(),
        updateTime: new Date().toISOString(),
        useCount: 0
      }))

      savedFilters.value.unshift(...importedFilters)
      alert(`成功导入 ${importedFilters.length} 个筛选条件`)
    } catch (error) {
      console.error('导入失败:', error)
      alert('导入失败，请检查文件格式')
    } finally {
      loading.value = false
    }
  }

  input.click()
}

/**
 * 导出筛选条件
 */
const handleExportClick = () => {
  handleBatchExport()
}

/**
 * 格式化更新时间
 */
const formatUpdateTime = (timeStr: string): string => {
  try {
    const date = new Date(timeStr)
    const now = new Date()
    const diffMs = now.getTime() - date.getTime()
    const diffDays = Math.floor(diffMs / (1000 * 60 * 60 * 24))

    if (diffDays === 0) {
      const diffHours = Math.floor(diffMs / (1000 * 60 * 60))
      if (diffHours === 0) {
        const diffMinutes = Math.floor(diffMs / (1000 * 60))
        if (diffMinutes === 0) return '刚刚'
        return `${diffMinutes}分钟前`
      }
      return `${diffHours}小时前`
    } else if (diffDays === 1) {
      return '昨天'
    } else if (diffDays < 7) {
      return `${diffDays}天前`
    } else if (diffDays < 30) {
      return `${Math.floor(diffDays / 7)}周前`
    } else {
      return date.toLocaleDateString('zh-CN', { month: 'numeric', day: 'numeric' })
    }
  } catch {
    return '未知时间'
  }
}

/**
 * 关闭管理对话框
 */
const handleManagerClose = () => {
  showFilterManager.value = false
  selectedFilterIds.value = []
  searchFilterName.value = ''
  filterCategory.value = ''
  currentPage.value = 1
}

/**
 * 切换结果展示
 */
const toggleResultsDemo = () => {
  showResultsDemo.value = !showResultsDemo.value
}

/**
 * 获取状态标签
 */
const getStatusLabel = (status: string): string => {
  return FilterUtils.getStatusLabel(status)
}

/**
 * 获取状态颜色
 */
const getStatusColor = (status: string): string => {
  return FilterUtils.getStatusColor(status)
}

/**
 * 刷新筛选结果
 */
const refreshResults = async () => {
  try {
    loading.value = true

    // 模拟API调用 - 实际项目中请使用：
    // const response = await getFilterResults(currentConditions.value)
    // filteredResults.value = response

    console.log('模拟获取筛选结果:', currentConditions.value)

    // 模拟延迟
    await new Promise(resolve => setTimeout(resolve, 300))

    // 生成模拟数据
    filteredResults.value = generateMockResults()

    // 设置默认活动组
    if (filteredResults.value.groups.length > 0 && !filteredResults.value.groups.find(g => g.type === activeResultGroup.value)) {
      activeResultGroup.value = filteredResults.value.groups[0].type
    }

    // 添加刷新动画
    if (listContainer.value) {
      listContainer.value.style.opacity = '0.5'
      setTimeout(() => {
        if (listContainer.value) {
          listContainer.value.style.opacity = '1'
        }
      }, 300)
    }
  } catch (error) {
    console.error('刷新筛选结果失败:', error)
    filteredResults.value = {
      total: 0,
      groups: [],
      timestamp: new Date().toISOString()
    }
  } finally {
    loading.value = false
  }
}

/**
 * 导出结果
 */
const exportResults = () => {
  const data = {
    exportTime: new Date().toISOString(),
    conditions: currentConditions.value,
    total: filteredResults.value.total,
    activeGroup: getActiveGroup.value,
    items: getActiveGroupItems.value.slice(startIndex.value, endIndex.value)
  }

  const dataStr = JSON.stringify(data, null, 2)
  const dataBlob = new Blob([dataStr], { type: 'application/json' })
  const url = URL.createObjectURL(dataBlob)

  const link = document.createElement('a')
  link.href = url
  link.download = `筛选结果_${getActiveGroupLabel.value}_${new Date().toISOString().slice(0, 10)}.json`
  document.body.appendChild(link)
  link.click()
  document.body.removeChild(link)
  URL.revokeObjectURL(url)

  alert('导出成功！文件已下载')
}

/**
 * 显示详情
 */
const showDetail = (item: FilterResultItem) => {
  selectedResultItem.value = item
  emit('resultItemClick', item)
}

/**
 * 在地图显示
 */
const showOnMap = (item: FilterResultItem) => {
  console.log('在地图显示:', item)
  // 在实际项目中，这里会触发地图显示事件
  alert(`将在地图显示: ${item.name}`)
}

/**
 * 显示图表
 */
const showChart = (item: FilterResultItem) => {
  console.log('显示图表:', item)
  // 在实际项目中，这里会触发图表显示事件
  alert(`将显示图表: ${item.name}`)
}

/**
 * 重置筛选条件
 */
const resetFilters = () => {
  currentConditions.value = FilterUtils.cloneConditions(FILTER_CONSTANTS.DEFAULT_CONDITIONS)
  handleConditionChange('', currentConditions.value)
}

/**
 * 处理结果操作
 */
const handleResultAction = (action: string) => {
  if (!selectedResultItem.value) return

  switch (action) {
    case 'viewOnMap':
      showOnMap(selectedResultItem.value)
      break
    case 'viewChart':
      showChart(selectedResultItem.value)
      break
    case 'download':
      // 模拟下载
      alert('开始下载数据...')
      break
  }
}

// 初始化
onMounted(() => {
  // 加载模拟数据
  loadSavedFilters()
  generateTags()
  calculateResultCount()
  refreshResults()

  // 点击外部关闭下拉菜单
  document.addEventListener('click', (e) => {
    if (showRecallDropdown.value && !(e.target as HTMLElement).closest('.dropdown-wrapper')) {
      showRecallDropdown.value = false
    }
  })
})

// 监听数据类型变化，更新状态选项
watch(() => currentConditions.value.dataType, (newTypes) => {
  if (newTypes.includes('event')) {
    const validStatus = FILTER_CONSTANTS.DATA_STATUS_MAPPING.event
    currentConditions.value.statusType = currentConditions.value.statusType.filter(
      status => validStatus.includes(status)
    )
    if (currentConditions.value.statusType.length === 0) {
      currentConditions.value.statusType = ['all']
    }
  }
}, { deep: true })

// 监听筛选条件变化，更新结果
watch(() => currentConditions.value, () => {
  refreshResults()
}, { deep: true, immediate: false })
</script>

<style scoped>
/* 主容器 */
.global-filter-container {
  //height: 300px;
  min-height: 120px;
  background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 50%, #f8f9fa 100%);
  border-bottom: 2px solid rgba(220, 223, 230, 0.8);
  padding: 0 24px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  position: relative;
  z-index: 1000;
  overflow: hidden;
}

.global-filter-container.filter-updating::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 200%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(64, 158, 255, 0.1),
    transparent
  );
  animation: shimmer 0.8s ease;
}

@keyframes shimmer {
  0% { left: -100%; }
  100% { left: 100%; }
}

/* 顶部筛选栏 */
.filter-bar {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 32px;
  padding: 16px 0;
}

/* 左侧筛选条件区 */
.filter-conditions {
  flex: 1;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
  align-items: center;
  max-width: calc(100% - 400px);
}

.filter-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.filter-label {
  color: #303133;
  font-size: 14px;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 6px;
}

.filter-label::before {
  content: '•';
  color: #409eff;
  font-size: 16px;
}

.filter-control {
  width: 100%;
}

/* 时间范围选择器 */
.time-range-selector {
  display: flex;
  align-items: center;
  gap: 8px;
  flex-wrap: wrap;
}

.time-select {
  flex: 1;
  min-width: 120px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  color: #303133;
  padding: 10px 12px;
  font-size: 14px;
  outline: none;
  transition: all 0.3s ease;
  height: 40px;
  cursor: pointer;
}

.time-select:focus {
  border-color: #409eff;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1);
}

.time-range-input {
  flex: 1;
  display: flex;
  gap: 8px;
}

.hour-input {
  width: 100px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  color: #303133;
  padding: 10px 12px;
  font-size: 14px;
  outline: none;
}

.hour-input::placeholder {
  color: #909399;
}

.custom-date-inputs {
  display: flex;
  align-items: center;
  gap: 8px;
  flex: 1;
}

.date-input {
  flex: 1;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  color: #303133;
  padding: 10px 12px;
  font-size: 14px;
  outline: none;
}

.date-separator {
  color: #606266;
  font-size: 12px;
}

/* 区域选择器 */
.area-selector {
  display: flex;
  align-items: center;
  gap: 8px;
}

.area-select {
  flex: 1;
  min-width: 120px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  color: #303133;
  padding: 10px 12px;
  font-size: 14px;
  outline: none;
  transition: all 0.3s ease;
  height: 40px;
  cursor: pointer;
}

.area-codes-input {
  flex: 1;
}

.codes-input {
  width: 100%;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  color: #303133;
  padding: 10px 12px;
  font-size: 14px;
  outline: none;
}

.codes-input::placeholder {
  color: #909399;
}

/* 复选框组 */
.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  background: #f8f9fa;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  padding: 10px;
  max-height: 80px;
  overflow-y: auto;
}

.checkbox-label {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #303133;
  font-size: 13px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  transition: all 0.2s ease;
}

.checkbox-label:hover {
  background: #ecf5ff;
}

.checkbox-input {
  width: 16px;
  height: 16px;
  cursor: pointer;
  accent-color: #409eff;
}

.checkbox-text {
  white-space: nowrap;
}

/* 右侧操作区 */
.filter-actions {
  display: flex;
  align-items: center;
  gap: 20px;
  min-width: 380px;
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.action-btn {
  height: 40px;
  padding: 0 16px;
  font-size: 14px;
  font-weight: 500;
  border-radius: 8px;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 8px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  color: #303133;
  cursor: pointer;
  outline: none;
}

.action-btn:hover:not(:disabled) {
  background: #f5f7fa;
  border-color: #c6e2ff;
  color: #409eff;
  transform: translateY(-1px);
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.15);
}

.action-btn:active:not(:disabled) {
  background: #ecf5ff;
  border-color: #409eff;
  transform: translateY(0);
}

.action-btn:disabled {
  background: #f5f7fa;
  border-color: #ebeef5;
  color: #c0c4cc;
  cursor: not-allowed;
  transform: none !important;
}

.save-btn {
  background: linear-gradient(135deg, #409eff 0%, #3375ff 100%);
  border: none;
  color: white;
  font-weight: 600;
}

.save-btn:hover:not(:disabled) {
  background: linear-gradient(135deg, #3375ff 0%, #2666ff 100%);
  border: none;
  color: white;
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.3);
}

.save-btn:disabled {
  background: #c0c4cc;
  border: none;
  color: #fff;
}

.btn-icon {
  font-size: 16px;
}

.btn-text {
  white-space: nowrap;
}

/* 下拉菜单 */
.dropdown-wrapper {
  position: relative;
}

.dropdown-arrow {
  margin-left: 4px;
  font-size: 10px;
  opacity: 0.7;
}

.recall-dropdown {
  position: absolute;
  top: calc(100% + 8px);
  left: 0;
  width: 280px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 12px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  z-index: 1100;
  overflow: hidden;
}

.dropdown-header {
  padding: 16px;
  background: #f8f9fa;
  border-bottom: 1px solid #e4e7ed;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #303133;
  font-size: 14px;
  font-weight: 600;
}

.count-badge {
  background: #409eff;
  color: white;
  font-size: 12px;
  padding: 2px 8px;
  border-radius: 10px;
}

.dropdown-body {
  max-height: 300px;
  overflow-y: auto;
}

.dropdown-item {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f2f5;
  cursor: pointer;
  transition: all 0.2s ease;
}

.dropdown-item:hover {
  background: #f5f7fa;
}

.item-name {
  color: #303133;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 4px;
}

.item-meta {
  display: flex;
  justify-content: space-between;
  color: #606266;
  font-size: 12px;
}

.dropdown-footer {
  padding: 12px 16px;
  text-align: center;
  color: #409eff;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.2s ease;
  background: #f8f9fa;
}

.dropdown-footer:hover {
  background: #ecf5ff;
}

/* 结果计数器 */
.result-counter {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
  padding: 10px 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  min-width: 160px;
}

.counter-content {
  flex: 1;
}

.counter-label {
  color: #606266;
  font-size: 12px;
  font-weight: 500;
  margin-bottom: 4px;
}

.counter-value {
  color: #67c23a;
  font-size: 20px;
  font-weight: 700;
}

.refresh-btn {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 6px;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #606266;
  font-size: 16px;
  transition: all 0.2s ease;
}

.refresh-btn:hover:not(:disabled) {
  background: #f5f7fa;
  border-color: #409eff;
  color: #409eff;
}

.refresh-btn:disabled {
  background: #f5f7fa;
  color: #c0c4cc;
  cursor: not-allowed;
}

/* 已选标签容器 */
.selected-tags-container {
  height: 60px;
  padding: 8px 0;
  border-top: 1px solid #e4e7ed;
}

.tags-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 8px;
  padding: 0 4px;
}

.tags-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.tags-icon {
  font-size: 14px;
  color: #409eff;
}

.tags-title {
  color: #303133;
  font-size: 13px;
  font-weight: 500;
}

.tags-count {
  color: #ffffff;
  font-size: 12px;
  background: #409eff;
  padding: 2px 8px;
  border-radius: 10px;
  font-weight: 600;
}

.clear-all-btn {
  background: none;
  border: 1px solid #dcdfe6;
  color: #606266;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 12px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.clear-all-btn:hover {
  background: #f5f7fa;
  border-color: #ff6b6b;
  color: #ff6b6b;
}

.selected-tags {
  height: 32px;
  display: flex;
  align-items: center;
  gap: 10px;
  overflow-x: auto;
  padding: 0 4px;
  scroll-behavior: smooth;
}

.selected-tags::-webkit-scrollbar {
  height: 4px;
}

.selected-tags::-webkit-scrollbar-track {
  background: #f0f2f5;
  border-radius: 2px;
}

.selected-tags::-webkit-scrollbar-thumb {
  background: #c0c4cc;
  border-radius: 2px;
}

.selected-tags::-webkit-scrollbar-thumb:hover {
  background: #909399;
}

/* 标签样式 */
.tag-slide-enter-active,
.tag-slide-leave-active {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.tag-slide-enter-from,
.tag-slide-leave-to {
  opacity: 0;
  transform: scale(0.8) translateX(-10px);
}

.tag {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 6px 12px;
  background: #ffffff;
  border: 1px solid #dcdfe6;
  border-radius: 16px;
  color: #303133;
  font-size: 13px;
  cursor: pointer;
  transition: all 0.3s ease;
  flex-shrink: 0;
  animation: tagSlideIn 0.4s ease forwards;
  opacity: 0;
  transform: translateY(10px);
  white-space: nowrap;
}

.tag:hover {
  background: #f5f7fa;
  border-color: #409eff;
  transform: translateY(-1px);
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.15);
}

.tag-icon {
  font-size: 14px;
}

.tag-text {
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.tag-close {
  margin-left: 4px;
  background: none;
  border: none;
  color: #909399;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  padding: 0;
  width: 16px;
  height: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  transition: all 0.2s ease;
}

.tag-close:hover {
  background: #ff6b6b;
  color: white;
}

/* 筛选结果示例区域 */
.results-demo-section {
  margin-top: 20px;
  border-top: 1px solid #e4e7ed;
  padding-top: 20px;
  animation: slideDown 0.4s ease;
}

.results-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.header-left {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.results-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.results-subtitle {
  font-size: 14px;
  color: #606266;
}

.header-right {
  flex-shrink: 0;
}

.results-toggle {
  padding: 8px 16px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  color: #606266;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s ease;
}

.results-toggle:hover {
  background: #f5f7fa;
  border-color: #409eff;
  color: #409eff;
}

.toggle-icon {
  font-size: 12px;
}

.toggle-text {
  white-space: nowrap;
}

.results-demo-content {
  margin-top: 16px;
  animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

/* 结果概览 */
.results-overview {
  background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
  padding: 24px;
  border-radius: 12px;
  border: 1px solid #e4e7ed;
  margin-bottom: 20px;
}

.overview-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 24px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 20px;
  background: #ffffff;
  border: 1px solid #e4e7ed;
  border-radius: 10px;
  flex: 1;
  min-width: 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.stat-icon {
  font-size: 24px;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(64, 158, 255, 0.1);
  border-radius: 8px;
  flex-shrink: 0;
}

.stat-content {
  display: flex;
  flex-direction: column;
  min-width: 0;
}

.stat-value {
  font-size: 20px;
  font-weight: 700;
  color: #303133;
  line-height: 1.2;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.stat-label {
  font-size: 12px;
  color: #909399;
  font-weight: 500;
  white-space: nowrap;
}

/* 结果分组导航 */
.results-navigation {
  display: flex;
  gap: 12px;
  overflow-x: auto;
  padding-bottom: 4px;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 20px;
  background: #ffffff;
  border: 2px solid #e4e7ed;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  min-width: 180px;
  flex-shrink: 0;
}

.nav-item:hover {
  border-color: #409eff;
  background: #f5f7fa;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.1);
}

.nav-item.active {
  border-color: #409eff;
  background: #ecf5ff;
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.15);
}

.nav-icon {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  color: white;
  font-size: 18px;
  flex-shrink: 0;
}

.nav-content {
  display: flex;
  flex-direction: column;
  gap: 4px;
  overflow: hidden;
}

.nav-label {
  font-size: 14px;
  font-weight: 600;
  color: #303133;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.nav-count {
  font-size: 12px;
  color: #909399;
  font-weight: 500;
}

/* 结果详情 */
.results-detail {
  background: #ffffff;
  border: 1px solid #e4e7ed;
  border-radius: 12px;
  overflow: hidden;
}

.detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
  border-bottom: 1px solid #e4e7ed;
}

.header-title {
  display: flex;
  align-items: center;
  gap: 12px;
}

.title-icon {
  font-size: 24px;
  color: #409eff;
}

.title-text {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.title-count {
  background: #409eff;
  color: white;
  font-size: 12px;
  font-weight: 600;
  padding: 2px 10px;
  border-radius: 12px;
  margin-left: 8px;
}

.header-actions {
  display: flex;
  gap: 12px;
}

/* 结果列表 */
.results-list {
  max-height: 600px;
  overflow-y: auto;
  padding: 24px;
}

.list-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
  transition: opacity 0.3s ease;
}

.result-item {
  background: #ffffff;
  border: 1px solid #e4e7ed;
  border-radius: 10px;
  padding: 20px;
  transition: all 0.3s ease;
  animation: slideIn 0.4s ease forwards;
  opacity: 0;
  transform: translateY(10px);
}

.result-item:hover {
  border-color: #409eff;
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.1);
  transform: translateY(-2px);
}

.result-item.status-normal {
  border-left: 4px solid #67c23a;
}

.result-item.status-abnormal {
  border-left: 4px solid #f56c6c;
}

.result-item.status-warning {
  border-left: 4px solid #e6a23c;
}

.result-item.status-handling {
  border-left: 4px solid #409eff;
}

.result-item.status-completed {
  border-left: 4px solid #909399;
}

.result-item.status-untreated {
  border-left: 4px solid #f56c6c;
}

.result-item.status-transit {
  border-left: 4px solid #e6a23c;
}

@keyframes slideIn {
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.item-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}

.item-icon {
  width: 48px;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  background: rgba(64, 158, 255, 0.1);
  font-size: 24px;
  flex-shrink: 0;
}

.item-title {
  flex: 1;
  min-width: 0;
}

.title-main {
  margin: 0 0 4px 0;
  font-size: 16px;
  font-weight: 600;
  color: #303133;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.title-sub {
  font-size: 13px;
  color: #909399;
  display: flex;
  align-items: center;
  gap: 8px;
}

.item-status {
  flex-shrink: 0;
}

.status-badge {
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
  color: white;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.item-body {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
}

.item-meta {
  display: flex;
  align-items: center;
  gap: 16px;
  flex: 1;
}

.meta-tags {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.meta-tag {
  padding: 4px 10px;
  background: #f5f7fa;
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  font-size: 12px;
  color: #606266;
  font-weight: 500;
}

.meta-value {
  display: flex;
  align-items: baseline;
  gap: 4px;
}

.value-number {
  font-size: 24px;
  font-weight: 700;
  color: #303133;
}

.value-unit {
  font-size: 14px;
  color: #909399;
}

.item-actions {
  display: flex;
  gap: 8px;
}

.action-icon {
  padding: 8px 12px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s ease;
}

.action-icon:hover {
  transform: translateY(-2px);
  border-color: transparent;
}

.detail-btn:hover {
  background: #409eff;
  color: white;
}

.map-btn:hover {
  background: #67c23a;
  color: white;
}

.chart-btn:hover {
  background: #e6a23c;
  color: white;
}

.action-text {
  font-size: 12px;
  font-weight: 500;
}

/* 分页 */
.results-pagination {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 24px;
  border-top: 1px solid #e4e7ed;
}

.pagination-info {
  font-size: 14px;
  color: #606266;
}

.pagination-controls {
  display: flex;
  align-items: center;
  gap: 12px;
}

.page-btn {
  padding: 8px 16px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  color: #303133;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.page-btn:hover:not(:disabled) {
  border-color: #409eff;
  color: #409eff;
  background: #ecf5ff;
}

.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  background: #f5f7fa;
}

.page-numbers {
  display: flex;
  gap: 4px;
}

.page-number {
  min-width: 36px;
  height: 36px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  color: #303133;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.page-number:hover {
  border-color: #409eff;
  color: #409eff;
}

.page-number.active {
  background: #409eff;
  border-color: #409eff;
  color: white;
}

.page-ellipsis {
  display: flex;
  align-items: center;
  padding: 0 8px;
  color: #909399;
  font-size: 14px;
}

/* 空状态 */
.empty-results {
  text-align: center;
  padding: 60px 20px;
  background: #f8f9fa;
  border-radius: 10px;
  border: 2px dashed #dcdfe6;
  margin: 24px;
}

.empty-icon {
  font-size: 64px;
  color: #c0c4cc;
  margin-bottom: 20px;
  opacity: 0.6;
}

.empty-title {
  font-size: 20px;
  font-weight: 600;
  color: #303133;
  margin-bottom: 12px;
}

.empty-desc {
  font-size: 14px;
  color: #606266;
  line-height: 1.6;
  margin-bottom: 24px;
}

.empty-action {
  padding: 12px 32px;
  background: #409eff;
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.empty-action:hover {
  background: #3375ff;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

/* 模态框 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
  backdrop-filter: blur(2px);
}

.modal-content {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 16px 48px rgba(0, 0, 0, 0.15);
  max-width: 90vw;
  max-height: 90vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.manager-modal {
  width: 900px;
}

.save-modal {
  width: 500px;
}

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 24px;
  background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
  color: #303133;
  border-bottom: 1px solid #dcdfe6;
}

.header-left {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.modal-title {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.modal-subtitle {
  font-size: 13px;
  color: #606266;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.header-btn {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  color: #303133;
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 13px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 6px;
  transition: all 0.2s ease;
}

.header-btn:hover {
  background: #f5f7fa;
  border-color: #409eff;
  color: #409eff;
}

.modal-close {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  color: #606266;
  width: 32px;
  height: 32px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 20px;
  transition: all 0.2s ease;
}

.modal-close:hover {
  background: #f5f7fa;
  border-color: #ff6b6b;
  color: #ff6b6b;
  transform: rotate(90deg);
}

.modal-body {
  padding: 24px;
  overflow-y: auto;
  flex: 1;
  background: #ffffff;
}

/* 管理工具栏 */
.manager-toolbar {
  display: flex;
  gap: 16px;
  margin-bottom: 20px;
}

.search-box {
  flex: 1;
  position: relative;
}

.search-input {
  width: 100%;
  padding: 10px 16px 10px 40px;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  font-size: 14px;
  color: #303133;
  background: #ffffff;
  transition: all 0.3s ease;
}

.search-input:focus {
  border-color: #409eff;
  outline: none;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1);
}

.search-input::placeholder {
  color: #909399;
}

.search-icon {
  position: absolute;
  left: 12px;
  top: 50%;
  transform: translateY(-50%);
  color: #909399;
  pointer-events: none;
}

.filter-controls {
  display: flex;
  gap: 12px;
}

.category-select,
.sort-select {
  padding: 10px 16px;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  font-size: 14px;
  color: #303133;
  background: #ffffff;
  cursor: pointer;
  min-width: 120px;
  transition: all 0.3s ease;
}

.category-select:focus,
.sort-select:focus {
  border-color: #409eff;
  outline: none;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1);
}

/* 批量操作 */
.batch-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px;
  background: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #e4e7ed;
  margin-bottom: 20px;
}

.batch-count {
  color: #409eff;
  font-weight: 600;
}

.batch-buttons {
  display: flex;
  gap: 12px;
}

.batch-btn {
  padding: 10px 20px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  border: none;
  transition: all 0.3s ease;
}

.batch-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.delete-btn {
  background: #ff6b6b;
  color: white;
}

.delete-btn:hover:not(:disabled) {
  background: #ff5252;
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(255, 107, 107, 0.3);
}

.export-btn {
  background: #409eff;
  color: white;
}

.export-btn:hover:not(:disabled) {
  background: #3375ff;
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

/* 筛选条件列表容器 */
.filter-list-container {
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 20px;
}

.list-header {
  display: flex;
  background: #f8f9fa;
  font-weight: 600;
  color: #303133;
  border-bottom: 1px solid #e4e7ed;
  height: 50px;
}

.header-cell {
  padding: 0 16px;
  display: flex;
  align-items: center;
}

.select-cell {
  width: 60px;
  justify-content: center;
}

.name-cell {
  flex: 3;
  min-width: 200px;
}

.category-cell {
  flex: 1;
  min-width: 100px;
}

.status-cell {
  flex: 1;
  min-width: 120px;
}

.count-cell {
  flex: 1;
  min-width: 80px;
}

.time-cell {
  flex: 1;
  min-width: 100px;
}

.action-cell {
  flex: 2;
  min-width: 150px;
}

.select-all-checkbox {
  width: 18px;
  height: 18px;
  cursor: pointer;
}

.list-body {
  max-height: 400px;
  overflow-y: auto;
}

.filter-row {
  display: flex;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #f0f2f5;
  transition: all 0.3s ease;
}

.filter-row:hover {
  background-color: #f8f9fa;
}

.row-selected {
  background-color: #ecf5ff;
}

.row-top {
  background-color: #fff8e1;
  border-left: 3px solid #e6a23c;
}

.row-cell {
  padding: 0 16px;
}

.row-checkbox {
  width: 18px;
  height: 18px;
  cursor: pointer;
}

.filter-name {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.top-indicator {
  color: #ffc107;
  margin-right: 6px;
}

.name-text {
  font-weight: 500;
  color: #303133;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.filter-desc {
  font-size: 12px;
  color: #909399;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.category-tag {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 500;
}

.category-common {
  background: #ecf5ff;
  color: #409eff;
}

.category-personal {
  background: #fde2e2;
  color: #f56c6c;
}

.category-department {
  background: #f0f9eb;
  color: #67c23a;
}

.category-project {
  background: #f0f9ff;
  color: #409eff;
}

.category-temporary {
  background: #fff0f6;
  color: #f759ab;
}

.status-badges {
  display: flex;
  gap: 6px;
  flex-wrap: wrap;
}

.status-badge {
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 11px;
  font-weight: 500;
}

.top-badge {
  background: #fff2e8;
  color: #fa8c16;
}

.shared-badge {
  background: #f0f9eb;
  color: #52c41a;
}

.private-badge {
  background: #fff0f6;
  color: #eb2f96;
}

.use-count {
  display: flex;
  align-items: center;
  gap: 6px;
}

.count-number {
  font-weight: 600;
  color: #303133;
}

.update-time {
  color: #606266;
  font-size: 13px;
}

.action-buttons {
  display: flex;
  gap: 8px;
}

.action-icon {
  width: 32px;
  height: 32px;
  border-radius: 6px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  cursor: pointer;
  font-size: 14px;
  color: #606266;
  transition: all 0.2s ease;
  display: flex;
  align-items: center;
  justify-content: center;
}

.action-icon:hover {
  transform: translateY(-2px);
  border-color: transparent;
}

.recall-icon:hover {
  background: #409eff;
  color: white;
}

.top-icon:hover {
  background: #e6a23c;
  color: white;
}

.delete-icon:hover {
  background: #ff6b6b;
  color: white;
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
  color: #909399;
}

.empty-icon {
  font-size: 48px;
  margin-bottom: 16px;
  opacity: 0.5;
}

.empty-text {
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 8px;
  color: #303133;
}

.empty-hint {
  font-size: 14px;
  color: #606266;
}

/* 分页 */
.pagination-container {
  display: flex;
  justify-content: center;
}

.pagination {
  display: flex;
  align-items: center;
  gap: 16px;
}

.page-btn {
  padding: 8px 16px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  color: #303133;
  cursor: pointer;
  transition: all 0.2s ease;
}

.page-btn:hover:not(:disabled) {
  border-color: #409eff;
  color: #409eff;
  background: #ecf5ff;
}

.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
  background: #f5f7fa;
}

.page-info {
  color: #606266;
  font-size: 14px;
}

/* 保存对话框预览区 */
.save-preview {
  background: #f8f9fa;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 24px;
}

.preview-title {
  font-size: 14px;
  font-weight: 600;
  color: #303133;
  margin-bottom: 12px;
}

.preview-content {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.preview-row {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 13px;
}

.preview-label {
  color: #606266;
  min-width: 80px;
}

.preview-value {
  color: #303133;
  font-weight: 500;
  flex: 1;
  word-break: break-all;
}

/* 保存表单 */
.save-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-label {
  font-size: 14px;
  color: #606266;
  font-weight: 500;
}

.form-label.required::after {
  content: '*';
  color: #ff6b6b;
  margin-left: 4px;
}

.form-input,
.form-textarea {
  padding: 10px 16px;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  font-size: 14px;
  color: #303133;
  background: #ffffff;
  transition: all 0.3s ease;
}

.form-input:focus,
.form-textarea:focus {
  border-color: #409eff;
  outline: none;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1);
}

.form-textarea {
  resize: vertical;
  min-height: 80px;
}

.form-hint {
  font-size: 12px;
  color: #909399;
  text-align: right;
}

.category-buttons {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.category-btn {
  padding: 8px 16px;
  border: 1px solid #dcdfe6;
  background: #ffffff;
  border-radius: 6px;
  font-size: 13px;
  color: #606266;
  cursor: pointer;
  transition: all 0.2s ease;
}

.category-btn:hover {
  border-color: #409eff;
  color: #409eff;
  background: #ecf5ff;
}

.category-btn.active {
  background: #409eff;
  color: white;
  border-color: #409eff;
}

.setting-option {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
}

.setting-checkbox {
  width: 16px;
  height: 16px;
  cursor: pointer;
  accent-color: #409eff;
}

.setting-text {
  font-size: 14px;
  color: #303133;
}

/* 模态框底部按钮 */
.modal-footer {
  padding: 20px 24px;
  border-top: 1px solid #e4e7ed;
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  background: #ffffff;
}

.footer-btn {
  padding: 10px 24px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  border: none;
  transition: all 0.3s ease;
}

.cancel-btn {
  background: #f5f7fa;
  color: #606266;
  border: 1px solid #dcdfe6;
}

.cancel-btn:hover {
  background: #e4e7ed;
  color: #303133;
}

.save-btn {
  background: #409eff;
  color: white;
}

.save-btn:hover:not(:disabled) {
  background: #3375ff;
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

.save-btn:disabled {
  background: #c0c4cc;
  cursor: not-allowed;
  transform: none !important;
  box-shadow: none !important;
}

/* 详情对话框 */
.detail-modal .modal-overlay {
  backdrop-filter: blur(4px);
}

.detail-modal .modal-content {
  width: 600px;
  animation: modalSlideIn 0.4s cubic-bezier(0.16, 1, 0.3, 1);
}

@keyframes modalSlideIn {
  from {
    opacity: 0;
    transform: translateY(30px) scale(0.95);
  }
  to {
    opacity: 1;
    transform: translateY(0) scale(1);
  }
}

.detail-modal .modal-header {
  padding: 24px;
}

.detail-modal .item-icon {
  width: 56px;
  height: 56px;
  font-size: 28px;
  background: rgba(64, 158, 255, 0.15);
  border-radius: 12px;
}

.detail-modal .item-info {
  flex: 1;
  margin-left: 16px;
}

.detail-modal .item-name {
  margin: 0 0 8px 0;
  font-size: 20px;
  font-weight: 600;
  color: #303133;
}

.detail-modal .item-meta {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  color: #606266;
}

.detail-modal .meta-separator {
  color: #c0c4cc;
  font-weight: bold;
}

.detail-modal .modal-close {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  color: #606266;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 20px;
  transition: all 0.2s ease;
  flex-shrink: 0;
}

.detail-modal .modal-close:hover {
  background: #f56c6c;
  border-color: #f56c6c;
  color: white;
  transform: rotate(90deg);
}

.detail-sections {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.detail-section {
  background: #f8f9fa;
  border-radius: 10px;
  padding: 20px;
  border: 1px solid #e4e7ed;
}

.section-title {
  margin: 0 0 16px 0;
  font-size: 16px;
  font-weight: 600;
  color: #303133;
  display: flex;
  align-items: center;
  gap: 8px;
}

.section-title::before {
  content: '';
  width: 4px;
  height: 16px;
  background: #409eff;
  border-radius: 2px;
}

.section-content {
  font-size: 14px;
  color: #606266;
}

.info-row {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
  padding-bottom: 12px;
  border-bottom: 1px solid #f0f2f5;
}

.info-row:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.info-label {
  width: 100px;
  color: #909399;
  font-weight: 500;
  flex-shrink: 0;
}

.info-value {
  flex: 1;
  color: #303133;
  font-weight: 500;
}

.value-display {
  display: flex;
  align-items: baseline;
  gap: 8px;
  margin-bottom: 20px;
  padding: 16px;
  background: #ffffff;
  border-radius: 8px;
  border: 1px solid #e4e7ed;
}

.value-display .value-number {
  font-size: 32px;
  font-weight: 700;
  color: #303133;
}

.value-display .value-unit {
  font-size: 16px;
  color: #909399;
  font-weight: 500;
}

.value-comparison {
  display: flex;
  gap: 24px;
}

.comparison-item {
  flex: 1;
  padding: 12px;
  background: #ffffff;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  text-align: center;
}

.comparison-label {
  font-size: 12px;
  color: #909399;
  margin-bottom: 4px;
}

.comparison-value {
  font-size: 18px;
  font-weight: 600;
  color: #f56c6c;
}

.comparison-value.positive {
  color: #67c23a;
}

.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.detail-tag {
  padding: 6px 12px;
  background: #ffffff;
  border: 1px solid #e4e7ed;
  border-radius: 6px;
  font-size: 12px;
  color: #606266;
  font-weight: 500;
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.action-buttons .action-btn {
  flex: 1;
  justify-content: center;
}

.primary-btn {
  background: #409eff;
  border-color: #409eff;
  color: white;
}

.primary-btn:hover {
  background: #3375ff;
  border-color: #3375ff;
  color: white;
}

.secondary-btn {
  background: #ffffff;
  border-color: #dcdfe6;
  color: #303133;
}

.secondary-btn:hover {
  background: #f5f7fa;
  border-color: #409eff;
  color: #409eff;
}

/* 动画定义 */
@keyframes tagSlideIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes slideDown {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 响应式设计 */
@media (max-width: 1400px) {
  .filter-conditions {
    max-width: calc(100% - 320px);
  }

  .filter-actions {
    min-width: 300px;
  }
}

@media (max-width: 1200px) {
  .global-filter-container {
    height: auto;
    min-height: 140px;
  }

  .filter-bar {
    flex-direction: column;
    gap: 16px;
    align-items: stretch;
  }

  .filter-conditions {
    max-width: 100%;
    grid-template-columns: 1fr 1fr;
  }

  .filter-actions {
    width: 100%;
    min-width: auto;
  }

  .action-buttons {
    justify-content: center;
  }

  .overview-stats {
    flex-wrap: wrap;
  }

  .stat-item {
    flex: 1 1 calc(50% - 10px);
    min-width: 0;
  }
}

@media (max-width: 768px) {
  .filter-conditions {
    grid-template-columns: 1fr;
  }

  .modal-content {
    width: 95vw;
  }

  .manager-modal,
  .save-modal {
    width: 95vw;
  }

  .list-header,
  .filter-row {
    flex-wrap: wrap;
    height: auto;
  }

  .header-cell,
  .row-cell {
    flex: 1 1 50%;
    padding: 8px;
  }

  .list-body {
    max-height: 50vh;
  }

  .overview-stats {
    flex-direction: column;
  }

  .stat-item {
    width: 100%;
  }

  .results-navigation {
    flex-wrap: nowrap;
    overflow-x: auto;
  }

  .nav-item {
    min-width: 160px;
  }

  .detail-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }

  .header-actions {
    width: 100%;
  }

  .item-body {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }

  .item-meta {
    width: 100%;
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }

  .item-actions {
    width: 100%;
    justify-content: flex-start;
  }

  .results-pagination {
    flex-direction: column;
    gap: 16px;
    align-items: flex-start;
  }
}

@media (max-width: 480px) {
  .stat-item {
    min-width: 100%;
  }

  .nav-item {
    min-width: 140px;
  }

  .value-number {
    font-size: 20px;
  }

  .action-icon .action-text {
    display: none;
  }
}
</style>
