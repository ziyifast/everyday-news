<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { resetPasswordService } from '@/api/user.js'
import { ElMessage } from 'element-plus'
// 引入路由
import router from '@/router/index.js'
import { ref } from 'vue'

//通过ref表示响应式对象，响应式对象需要先.value之后才能拿到真实的值
const resetPwdData = ref({
    oldPwd: '',
    newPwd: '',
    rePwd: ''
})

//引入token
import { useTokenStore } from '@/store/token.js'
const useToken = useTokenStore()
const resetPassword = async () => {
    let result = await resetPasswordService(resetPwdData.value)
    ElMessage.success(result.msg ? result.msg : '修改成功')
    router.push('/login')

}


//登录表单验证规则
// const rules = ref
//密码二次校验规则
const validatePass2 = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== resetPwdData.value.rePwd) {
        callback(new Error("两次密码不一致"))
    } else {
        callback()
    }
}
const resetPwdRules = ({
    oldPwd: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 5, max: 16, message: '用户名必须为5-16个字符', trigger: 'blur' },
    ],
    newPwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 16, message: '密码必须为6-16个字符', trigger: 'blur' },
    ],
    rePwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 16, message: '密码必须为6-16个字符', trigger: 'blur' },
    ]
})

//clearRegisterData
const clearRegisterData = () => {
    resetPwdData.value = {
        oldPwd: '',
        newPwd: '',
        rePwd: ''
    }
}
</script>

<template>
    <el-row class="reset-page">
    
        <el-col :span="6" :offset="3" class="form">
    
            <el-form ref="form" size="large" autocomplete="off" :model="resetPwdData" :rules="resetPwdRules">
    
                <el-form-item>
    
                    <h1>修改密码</h1>
    
                </el-form-item>
    
                <el-form-item prop="oldPwd">
    
                    <el-input :prefix-icon="User" type="password" placeholder="请输入原密码" v-model="resetPwdData.oldPwd"></el-input>
    
                </el-form-item>
    
                <el-form-item prop="newPwd">
    
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="resetPwdData.newPwd"></el-input>
    
                </el-form-item>
    
                <el-form-item prop="rePwd">
    
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请再次输入密码" v-model="resetPwdData.rePwd"></el-input>
    
                </el-form-item>
    
                <el-form-item>
    
                    <el-button class="button" type="primary" auto-insert-space @click="resetPassword()">提交</el-button>
    
                </el-form-item>
    
            </el-form>
    
        </el-col>
    
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */

.reset-page {
    height: 100vh;
    background-color: #fff;
    .bg {
        background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto, url('@/assets/login_bg.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }
    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
        .title {
            margin: 0 auto;
        }
        .button {
            width: 100%;
        }
        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>