<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ElMessage,ElMessageBox} from 'element-plus'
import { ref } from 'vue'
import { articleCategoryListService,addArticleCategoryService,updateArticleCategoryService,
    deleteArticleCategoryService } from '@/api/article.js'
//获取文章分类
const getArticleCategoryList = async () => {
    let result = await articleCategoryListService()
    if (result.code === 0) {
        categorys.value = result.data
        return
    }
    ElMessage.error(result.message ? '' : '服务异常')
    return

}
getArticleCategoryList()

const categorys = ref([

])

const title = ref('')
//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const categoryModel = ref({
    id:0,
    categoryName: '',
    categoryAlias: ''
})
//清空模型数据
const clearCategoryModel = ()=>{
    categoryModel.value.categoryName='',
    categoryModel.value.categoryAlias=''
}
//添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}
//回显数据
const updateCategoryEcho = (row) => {
    categoryModel.value.id = row.id
    categoryModel.value.categoryName = row.categoryName
    categoryModel.value.categoryAlias = row.categoryAlias
}

//修改文章分类
const updateCategory = async () => {
    let result = await updateArticleCategoryService(categoryModel.value)
    if (result.code === 0) {
        ElMessage.success('修改成功')
        dialogVisible.value = false
        getArticleCategoryList()
        return
    }
    ElMessage.error(result.message ? '' : '服务异常')
    return
}

//添加文章分类
const addArticleCategory = async () => {
    addArticleCategory.value=''
    let result = await addArticleCategoryService(categoryModel.value)
    if (result.code === 0) {
        ElMessage.success('添加成功')
        dialogVisible.value = false
        getArticleCategoryList()
        categoryModel.value = ''
        return
    }
    ElMessage.error(result.message ? '' : '服务异常')
    return
}

//删除文章分类
//删除分类  给删除按钮绑定事件
const deleteCategory = (id) => {
    ElMessageBox.confirm(
        '你确认删除该分类信息吗？',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //用户点击了确认
            let result = await deleteArticleCategoryService(id)
            ElMessage.success(result.message?result.message:'删除成功')
            //再次调用getAllCategory，获取所有文章分类
            getArticleCategoryList()
            return
        })
        .catch(() => {
            //用户点击了取消
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
}
 
//控制弹窗
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary" @click=";title='添加分类';dialogVisible = true;clearCategoryModel()">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="title='修改分类';updateCategoryEcho(row);dialogVisible = true;" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row.id)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
    </el-card>
    <!-- 添加分类弹窗 -->
<el-dialog v-model="dialogVisible" :title="title" width="30%">
    <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
        <el-form-item label="分类名称" prop="categoryName">
            <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="分类别名" prop="categoryAlias">
            <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
        </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false;">取消</el-button>
            <el-button type="primary" @click="title==='添加分类'?addArticleCategory():updateCategory();"> 确认 </el-button>
        </span>
    </template>
</el-dialog>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>