<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="studentId">
        <el-select
            v-model="queryParams.studentId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入学员"
            :remote-method="remoteStudentMethod"
            :loading="loading">
            <el-option
              v-for="item in studentListLikeInfo"
              :key="item.studentId"
              :label="item.studentName"
              :value="item.studentId">
            </el-option>
          </el-select>
      </el-form-item>
      
      <el-form-item label="证件类型" prop="license">        
        <el-select v-model="queryParams.license" placeholder="请选择证件类型" clearable>
          <el-option
            v-for="(item, index) in licenseList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="科目" prop="level">
        
        <el-select v-model="queryParams.level" placeholder="请选择科目" clearable>
          <el-option
            v-for="(item, index) in levelList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
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
          v-hasPermi="['student:studentScore:add']"
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
          v-hasPermi="['student:studentScore:edit']"
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
          v-hasPermi="['student:studentScore:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分数编号" align="center" prop="id" />
      <!-- <el-table-column label="学员编号" align="center" prop="studentId" /> -->
      <el-table-column label="姓名" align="center" prop="studentName" />      
      <el-table-column label="证件类型" align="center" prop="license">
        <template slot-scope="scope">
          <template v-for="(item, index) in licenseList">
            <template v-if="item.id==scope.row.license">
              <span>{{ item.name }}</span>
            </template>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="科目" align="center" prop="level">
        <template slot-scope="scope">
          <template v-for="(item, index) in levelList">
            <template v-if="item.id==scope.row.level">
              <span>{{ item.name }}</span>
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="分数" align="center" prop="score"/>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:studentScore:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:studentScore:remove']"
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

    <!-- 添加或修改成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学员" prop="studentId">
          <el-select
            v-model="form.studentId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入学员"
            :remote-method="remoteStudentMethod"
            :loading="loading">
            <el-option
              v-for="item in studentListLikeInfo"
              :key="item.studentId"
              :label="item.studentName"
              :value="item.studentId">
            </el-option>
          </el-select>
        </el-form-item>

      <el-form-item label="证件类型" prop="license">
        <el-radio-group v-model="form.license">
          <el-radio v-for="(item, index) in licenseList" :key="item.id" :label="item.id">
            {{ item.name }}
            </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="科目" prop="level">
        <el-radio-group v-model="form.level">
          <el-radio v-for="(item, index) in levelList" :key="item.id" :label="item.id">
          {{ item.name }}
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="分数" prop="score">
        <el-input-number v-model="form.score" placeholder="分数"></el-input-number>
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
import { listScore, getScore, delScore, addScore, updateScore } from "@/api/student/score";
import { listDetail } from "@/api/student/detail";
import { listLevel } from "@/api/exam/level";
import { listLicense } from "@/api/exam/license";

export default {
  name: "ScoreList",
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
      // 成绩表格数据
      scoreList:[],
      //科目数据
      levelList: [],
      // 证件表格数据
      licenseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //学员列表，用来下拉列表模糊查询
      studentListLikeInfo:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学员不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "分数不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学员列表 */
    remoteStudentMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
        listDetail({studentName:query}).then((res) => {
          this.studentListLikeInfo = res.rows.filter(item => {
            return item.studentName.indexOf(query) > -1;
          });
        });
        }, 200);
      } else {
        this.studentListLikeInfo = [];
      }
    },
    /** 查询成绩列表 */
    getList() {
      this.loading = true;
      
      /** 加载数据字典**/
      listLicense(null).then(response => {
        this.licenseList = response.rows;
      });
      listLevel(null).then(response => {
        this.levelList = response.rows;
      });

      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows;
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
        studentId: undefined,
        level: 1,
        license: 1,
        score: 0
      };
      /* 模糊搜索清空 */
      this.studentListLikeInfo=[];
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
      this.title = "添加成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getScore(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成绩";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScore(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除分数编号为"' + ids + '"的数据项？').then(function() {
        return delScore(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/studentScore/export', {
        ...this.queryParams
      }, `license_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
