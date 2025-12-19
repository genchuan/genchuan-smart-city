<template>
  <div
    :class="prefixCls"
    class="relative h-[100%] lt-md:px-10px lt-sm:px-10px lt-xl:px-10px lt-xl:px-10px"
  >
    <div class="relative mx-auto h-full flex">
      <div
        :class="`${prefixCls}__left flex-1 bg-gray-500 bg-opacity-20 relative p-30px lt-xl:hidden overflow-x-hidden overflow-y-auto`"
      >
        <!-- 左上角的 logo + 系统标题 -->
        <div class="relative flex items-center text-white">
          <img
            alt=""
            class="mr-10px h-48px w-48px"
            src="@/assets/imgs/logo.png"
            v-if="VITE_LOGO_SHOW"
          />
          <span class="text-20px font-bold" v-if="VITE_LOGO_SHOW">{{
            underlineToHump(appStore.getTitle)
          }}</span>
        </div>
        <!-- 左边的背景图 + 欢迎语（修改后：图片在下，文字在上居中） -->
        <div class="h-[calc(100%-60px)] flex flex-col items-center justify-center">
          <TransitionGroup
            appear
            enter-active-class="animate__animated animate__bounceInLeft"
            tag="div"
            class="flex flex-col items-center"
          >
            <img key="1" alt="" class="w-350px mt-10" src="@/assets/svgs/login-box-bg.svg" />
            <div key="2" class="text-3xl text-white text-center">{{ t('login.welcome') }}</div>
            <div key="3" class="mt-5 text-14px font-normal text-white text-center max-w-2xl">
              {{ t('login.message') }}
            </div>
            <div
              key="4"
              style="
                position: fixed;
                bottom: 15px;
                display: flex;
                align-items: center;
                font-size: 14px;
                font-weight: normal;
                color: white;
              "
              v-if="VITE_LOGO_SHOW"
            >
              <img
                alt=""
                style="margin-right: 10px; height: 80px; width: 80px"
                src="@/assets/imgs/kefu.png"
              />
              <img
                alt=""
                style="margin-right: 10px; height: 80px; width: 80px"
                src="@/assets/imgs/gongzhonghao.png"
              />
              <div>
                <p>福建亘川科技有限公司</p>
                <p>地址： 福建省泉州市丰泽区星光耀广场18幢1611-1612单元</p>
                <p>电话： 17859895988 </p>
                <p>邮箱： 990267803@qq.com</p>
              </div>
            </div>
          </TransitionGroup>
        </div>
      </div>
      <div
        class="relative flex-1 p-30px dark:bg-[var(--login-bg-color)] lt-sm:p-10px overflow-x-hidden overflow-y-auto"
      >
        <!-- 右上角的主题、语言选择 -->
        <div
          class="flex items-center justify-between at-2xl:justify-end at-xl:justify-end"
          style="color: var(--el-text-color-primary)"
        >
          <div class="flex items-center at-2xl:hidden at-xl:hidden">
            <img
              alt=""
              class="mr-10px h-48px w-48px"
              src="@/assets/imgs/logo.png"
              v-if="VITE_LOGO_SHOW"
            />
            <span class="text-20px font-bold">{{ underlineToHump(appStore.getTitle) }}</span>
          </div>
          <div class="flex items-center justify-end space-x-10px h-48px">
            <ThemeSwitch />
            <LocaleDropdown />
          </div>
        </div>
        <!-- 右边的登录界面 -->
        <Transition appear enter-active-class="animate__animated animate__bounceInRight">
          <div
            class="m-auto h-[calc(100%-60px)] w-[100%] flex items-center at-2xl:max-w-500px at-lg:max-w-500px at-md:max-w-500px at-xl:max-w-500px"
          >
            <!--三方登录-->
            <SSOLoginVue class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
            <!-- 账号登录 -->
            <LoginForm class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
            <!-- 手机登录
            <MobileForm class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
             二维码登录
            <QrCodeForm class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
             注册
            <RegisterForm class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
             三方登录
            <SSOLoginVue class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />
             忘记密码
            <ForgetPasswordForm class="m-auto h-auto p-20px lt-xl:(rounded-3xl light:bg-white)" />-->
          </div>
        </Transition>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { underlineToHump } from '@/utils'
import { useDesign } from '@/hooks/web/useDesign'
import { useAppStore } from '@/store/modules/app'
import { ThemeSwitch } from '@/layout/components/ThemeSwitch'
import { LocaleDropdown } from '@/layout/components/LocaleDropdown'
// import { LoginForm, MobileForm, QrCodeForm, RegisterForm, SSOLoginVue, ForgetPasswordForm } from './components'
import { LoginForm, SSOLoginVue } from './components'
// logo 是否显示
const VITE_LOGO_SHOW = JSON.parse(import.meta.env.VITE_LOGO_SHOW)
defineOptions({ name: 'Login' })
const { t } = useI18n()
const appStore = useAppStore()
const { getPrefixCls } = useDesign()
const prefixCls = getPrefixCls('login')
</script>
<style lang="scss" scoped>
$prefix-cls: #{$namespace}-login;
.#{$prefix-cls} {
  overflow: auto;

  &__left {
    &::before {
      position: absolute;
      top: 0;
      left: 0;
      z-index: -1;
      width: 100%;
      height: 100%;
      background-image: url('@/assets/svgs/login-bg.svg');
      background-position: center;
      background-repeat: no-repeat;
      content: '';
    }
  }
}
</style>
<style lang="scss">
.dark .login-form {
  .el-divider__text {
    background-color: var(--login-bg-color);
  }

  .el-card {
    background-color: var(--login-bg-color);
  }
}
</style>
