<template>
  <el-container class="layout-container">
    <!-- 左侧菜单 -->
    <el-aside width="200px">
      <div class="el-aside__logo"></div>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#232323"
        text-color="#fff"
        router
      >
        <el-menu-item index="/article/category">
          <el-icon>
            <Management />
          </el-icon>
          <span>文章分类</span>
        </el-menu-item>
        <el-menu-item index="/article/manage">
          <el-icon>
            <Promotion />
          </el-icon>
          <span>文章管理</span>
        </el-menu-item>
        <el-sub-menu>
          <template #title>
            <el-icon>
              <UserFilled />
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item index="/user/info">
            <el-icon>
              <User />
            </el-icon>
            <span>基本资料</span>
          </el-menu-item>
          <el-menu-item index="/user/avatar">
            <el-icon>
              <Crop />
            </el-icon>
            <span>更换头像</span>
          </el-menu-item>
          <el-menu-item index="/user/resetPassword">
            <el-icon>
              <EditPen />
            </el-icon>
            <span>重置密码</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <!-- 右侧主区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div>
          小白：<strong>{{
            userInfoStore.info.nickname == ""
              ? userInfoStore.info.username
              : userInfoStore.info.nickname
          }}</strong>
        </div>
        <el-dropdown placement="bottom-end">
          <span class="el-dropdown__box">
            <el-avatar
              :src="
                (userInfoStore.info.userPic = ''
                  ? avatar
                  : userInfoStore.info.userPic)
              "
            />
            <el-icon>
              <CaretBottom />
            </el-icon>
          </span>
          <template #dropdown>
            <!-- <el-dropdown-menu placement="bottom-end" @command="handleCommand">
              <el-dropdown-item command="info" :icon="User"
                >基本资料</el-dropdown-item
              >
              <el-dropdown-item command="avatar" :icon="Crop"
                >更换头像</el-dropdown-item
              >
              <el-dropdown-item command="resetPassword" :icon="EditPen"
                >重置密码</el-dropdown-item
              >
              <el-dropdown-item command="logout" :icon="SwitchButton"
                >退出登录</el-dropdown-item
              >
            </el-dropdown-menu> -->
            <el-dropdown-menu placement="bottom-end">
              <el-dropdown-item @click="handleCommand('info')" :icon="User"
                >基本资料</el-dropdown-item
              >
              <el-dropdown-item @click="handleCommand('avatar')" :icon="Crop"
                >更换头像</el-dropdown-item
              >
              <el-dropdown-item
                @click="handleCommand('resetPassword')"
                :icon="EditPen"
                >重置密码</el-dropdown-item
              >
              <el-dropdown-item
                @click="handleCommand('logout')"
                :icon="SwitchButton"
                >退出登录</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <!-- 中间区域 -->
      <el-main>
        <!-- <div style="width: 1290px; height: 570px; border: 1px solid red"> -->
        <router-view></router-view>
        <!-- </div> -->
      </el-main>
      <!-- 底部区域 -->
      <el-footer>大事件 ©2025 Created by Stavage</el-footer>
    </el-container>
  </el-container>
</template>
<script setup>
import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom,
} from "@element-plus/icons-vue";
import avatar from "@/assets/default.png";

import { getUserInfoService } from "@/api/user.js";
import { useUserInfoStore } from "@/stores/userInfo.js";
import { useTokenStore } from "@/stores/token.js";
const userInfoStore = useUserInfoStore();
const tokenStore = useTokenStore();
const getUserInfo = async () => {
  let res = await getUserInfoService();
  userInfoStore.setInfo(res.data);
  // console.log(res.data);
};
getUserInfo();

import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from "element-plus";
const router = useRouter();
const handleCommand = (command) => {
  console.log(command);
  if (command === "logout") {
    ElMessageBox.confirm("你确认退出登录码？", "温馨提示", {
      confirmButtonText: "确认",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(async () => {
        userInfoStore.removeInfo();
        tokenStore.removeToken();
        router.push("/login");
      })
      .catch(() => {
        ElMessage({
          type: "info",
          message: "取消退出",
        });
      });
  } else {
    router.push("/user/" + command);
  }
};
</script>
<style lang="scss" scoped>
.layout-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;

    &__logo {
      height: 120px;
      background: url("@/assets/logo.png") no-repeat center / 120px auto;
    }

    .el-menu {
      border-right: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}
</style>