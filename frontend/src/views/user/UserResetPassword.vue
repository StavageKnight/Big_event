<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>修改密码</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-form
          :model="password"
          :rules="rules"
          label-width="100px"
          size="large"
        >
          <el-form-item label="原密码" prop="old_pwd">
            <el-input v-model="password.old_pwd"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="new_pwd">
            <el-input v-model="password.new_pwd"></el-input>
          </el-form-item>
          <el-form-item label="重复新密码" prop="re_pwd">
            <el-input v-model="password.re_pwd"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updatePassword"
              >提交修改</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>
  <script setup>
import { ref, toRaw } from "vue";
import { ElMessage } from "element-plus";
import { updatePasswordService } from "@/api/user";

const password = ref({
  old_pwd: "",
  new_pwd: "",
  re_pwd: "",
});

import { useRouter } from "vue-router";
const router = useRouter();
const updatePassword = async () => {
  // passwordData.value.old_pwd = password.value.old_pwd;
  const passwordData = toRaw(password.value);
  let res = await updatePasswordService(passwordData);
  router.push("/login");
  ElMessage.success(res.message);
};
const rules = {
  old_pwd: [
    { required: true, message: "请输入原密码", trigger: "blur" },
    {
      pattern: /^\S{5,12}$/,
      message: "密码必须是5-12位的非空字符串",
      trigger: "blur",
    },
  ],
  new_pwd: [
    { required: true, message: "请输入新密码", trigger: "blur" },
    {
      pattern: /^\S{5,12}$/,
      message: "密码必须是5-12位的非空字符串",
      trigger: "blur",
    },
  ],
  re_pwd: [
    { required: true, message: "请再次输入新密码", trigger: "blur" },
    {
      validator: (rule, value, callback) => {
        if (value !== password.value.new_pwd) {
          callback(new Error("两次输入的新密码不一致"));
        } else {
          callback();
        }
      },
      trigger: "blur",
    },
  ],
};
</script>