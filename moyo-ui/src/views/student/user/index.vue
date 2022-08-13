<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学员编码" prop="studentNo">
        <el-input
          v-model="queryParams.studentNo"
          placeholder="请输入学员编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学员名称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入学员名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['student:studentUser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['student:studentUser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['student:studentUser:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentUserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学员编号" align="center" prop="id" />
      <el-table-column label="学员编码" align="center" prop="studentNo" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:studentUser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:studentUser:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学员编码" prop="studentNo">
          <el-input v-model="form.studentNo" placeholder="请输入编码名称" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <!-- 详情信息 -->
        <el-form-item label="姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="证件号码" prop="identity">
          <el-input v-model="form.identity" placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1" >女</el-radio>
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="form.age" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话号码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/student/user";
import { getDetail, delDetail, addDetail, updateDetail } from "@/api/student/detail";

export default {
  name: "Student",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 学员表格数据
      studentUserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentNo: undefined,
        username: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        studentNo: [
          { required: true, message: "学员编码不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        studentName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "电话号码不能为空", trigger: "blur" }
        ],
        identity: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学员列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        this.studentUserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        studentNo: undefined,
        username: undefined,
        password: undefined,
        //详情信息
        studentId:undefined,
        detailId:undefined,
        studentName:undefined,
        identity:undefined,
        address:undefined,
        sex:0,
        age:18,
        phone:undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudent(id).then(response => {
        this.form = response.data;
        /**详情信息 **/  
        getDetail(id).then(resDetail => {
          var data = resDetail.data;
          /**详情信息 **/
          this.form.studentId=this.form.id;
          this.$set(this.form, "detailId", data.id);
          this.$set(this.form, "studentName", data.studentName);
          this.$set(this.form, "identity", data.identity);
          this.$set(this.form, "address", data.address);
          this.$set(this.form, "sex", data.sex);
          this.$set(this.form, "age", data.age);
          this.$set(this.form, "phone", data.phone);
          //显示窗体
          this.open = true;
          this.title = "修改学员";
        });
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStudent({              
              id: this.form.id,
              studentNo: this.form.studentNo,
              username: this.form.username,
              password: this.form.password,
            }).then(response => {              
              updateDetail({    
                id:this.form.detailId,            
                studentId:this.form.studentId,
                studentName:this.form.studentName,
                identity:this.form.identity,
                address:this.form.address,
                sex:this.form.sex,
                age:this.form.age,
                phone:this.form.phone,
              }).then(resDetail => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            });
          } else {
            addStudent({
              id: this.form.id,
              studentNo: this.form.studentNo,
              username: this.form.username,
              password: this.form.password,
            }).then(response => {
              addDetail({
                id:this.form.detailId,
                studentId:response.data.id,
                studentName:this.form.studentName,
                identity:this.form.identity,
                address:this.form.address,
                sex:this.form.sex,
                age:this.form.age,
                phone:this.form.phone,
              }).then(resDetail => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除学员编号为"' + ids + '"的数据项？').then(function() {
        return delDetail(ids) && delStudent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/studentUser/export', {
        ...this.queryParams
      }, `studentUser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
