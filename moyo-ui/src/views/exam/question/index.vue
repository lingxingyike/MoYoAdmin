<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="题型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择题型" clearable>
          <el-option
            v-for="(item, index) in typeList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
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

      <el-form-item label="题目" prop="topic">
        <el-input v-model="queryParams.topic" placeholder="请输入题目" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['exam:question:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['exam:question:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['exam:question:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionList"
      @selection-change="handleSelectionChange" 
      stripe
      @expand-change="expandChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column type="expand">
        <template #default="props">
          <el-table :data="props.row.optionslist">
            <el-table-column label="编号" prop="id" />
            <el-table-column label="选项" prop="content" />
            <el-table-column label="答案" prop="isAnswer" >
              <template slot-scope="scope">
                  <span>
                  {{
                    scope.row.isAnswer===1
                      ? '是'
                      : '否'
                  }}
                  </span>
              </template>
            </el-table-column>
            <el-table-column label="排序" prop="sequence" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini" type="text" icon="el-icon-edit" @click="handleOptionsUpdate(scope.row)"
                  v-hasPermi="['exam:options:edit']">修改</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click="handleOptionsDelete(scope.row)"
                  v-hasPermi="['exam:options:remove']">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>

      <el-table-column label="题目编号" align="center" prop="id" />
      
      <el-table-column label="题型" align="center" prop="type">
        <template slot-scope="scope">
          <template v-for="(item, index) in typeList">
            <template v-if="item.id==scope.row.type">
              <span>{{ item.name }}</span>
            </template>
          </template>
        </template>
      </el-table-column>
      
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
      
      <el-table-column label="题目" align="center" prop="topic" :show-overflow-tooltip="true" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleOptionsAdd(scope.row.id)"
            v-hasPermi="['exam:options:add']">选项添加</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['exam:question:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['exam:question:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改题目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      
      <el-form-item label="题型" prop="type">
        <el-radio-group v-model="form.type">
          <el-radio v-for="(item, index) in typeList" :key="item.id" :label="item.id">
            {{ item.name }}
            </el-radio>
        </el-radio-group>
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

        <el-form-item label="题目" prop="topic">
          <el-input v-model="form.topic" type="textarea" placeholder="请输入题目" />
        </el-form-item>

        
        <el-form-item label="图片路径" prop="picUrl">
          <el-input v-model="form.picUrl" placeholder="请输入图片路径" />
        </el-form-item>
      </el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    
    <!-- 添加或修改选项对话框 -->
    <el-dialog :title="titleOptions" :visible.sync="openOptions" width="500px" append-to-body>
      <el-form ref="formOptions" :model="formOptions" :rules="rules" label-width="80px">

      <el-form-item label="排序" prop="sequence">
        <el-input-number v-model="formOptions.sequence" placeholder="排序"></el-input-number>
      </el-form-item>

        <el-form-item label="答案" prop="isAnswer">          
          <el-radio-group v-model="formOptions.isAnswer">
            <el-radio  :key="index" :label="0">否</el-radio>
            <el-radio  :key="index" :label="1" >是</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="选项" prop="content">
          <el-input v-model="formOptions.content" type="textarea" placeholder="请输入选项" />
        </el-form-item>
      </el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitOptionsForm">确 定</el-button>
        <el-button @click="cancelOptions">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listQuestion, getQuestion, delQuestion, addQuestion, updateQuestion } from "@/api/exam/question";
// import { getOptions, delOptions, addOptions, updateOptions } from "@/api/exam/options";
import { listOptions,getOptions, delOptions, addOptions, updateOptions } from "@/api/exam/options";
import { listLevel } from "@/api/exam/level";
import { listLicense } from "@/api/exam/license";
import { listType } from "@/api/exam/type";

export default {
  name: "Question",
  dicts: ['sys_yes_no'],
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
      // 题目表格数据
      questionList: [],
      //科目数据
      levelList: [],
      //题型数据
      typeList: [],
      //证件类型
      licenseList: [],
      // 题目弹出层标题
      title: "",
      // 选项弹出层标题
      titleOptions: "",
      // 是否显示弹出层
      open: false,
      // 是否显示选项弹出层
      openOptions: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        level:undefined,
        license:undefined,
        typeof:undefined,
        topic: undefined
      },
      // 表单参数
      form: {},
      // 选项表单参数
      formOptions: {},
      // 表单校验
      rules: {
        topic: [
          { required: true, message: "题目不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 展开事件----动态获取内嵌表数据 */
    expandChange(row, expandedRows) {
      // 该处是用于判断是展开还是收起行，只有展开的时候做请求，避免多次请求！
      // 展开的时候expandedRows有值，收起的时候为空.
      if (expandedRows.length > 0) {        
        listOptions({
            questionId: row.id
        }).then(response => {
            // 遍历当前页面表
            this.questionList.forEach((temp, index) => {
              // 找到当前点击的行，把动态获取到的数据赋值进去
              if (temp.id === row.id) {
                this.questionList[index].optionslist = response.rows;
              }
            });
        });
      }
    },
    /** 查询题目列表 */
    getList() {
      this.loading = true;
      /** 加载数据字典**/
      listLicense(null).then(response => {
        this.licenseList = response.rows;
      });
      listLevel(null).then(response => {
        this.levelList = response.rows;
      });
      listType(this.queryParams).then(response => {
        this.typeList = response.rows;
      });

      listQuestion(this.queryParams).then(response => {
        //给每行数据强制追加一个数据项
        response.rows.map(item=>{
          item.optionslist=[];
        });
        this.questionList = response.rows;
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
        type: this.typeList[0].id,
        level: this.levelList[0].id,
        license: this.licenseList[0].id,
        topic: undefined,
        picUrl: undefined
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
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加题目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getQuestion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改题目";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateQuestion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuestion(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除题目编号为"' + ids + '"的数据项？').then(function () {
        return delQuestion(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('exam/question/export', {
        ...this.queryParams
      }, `question_${new Date().getTime()}.xlsx`)
    },

    /** 选项数据 **/
    
    // 取消按钮
    cancelOptions() {
      this.openOptions = false;
      this.resetOptions();
    },
    // 表单重置
    resetOptions() {
      this.formOptions = {
        id: undefined,
        questionId: undefined,
        content: undefined,
        isAnswer: 0,
        sequence: 0
      };
      this.resetForm("formOptions");
    },
    /** 重置按钮操作 */
    resetOptionsQuery() {
      this.resetForm("queryOptionsForm");
      this.handleOptionsQuery();
    },
    /** 新增按钮操作 */
    handleOptionsAdd(id) {
      this.resetOptions();
      this.formOptions.questionId=id;
      this.openOptions = true;
      this.titleOptions = "添加选项";
    },
    /** 修改按钮操作 */
    handleOptionsUpdate(row) {
      this.resetOptions();
      const id = row.id || this.ids
      getOptions(id).then(response => {
        this.formOptions = response.data;
        this.openOptions = true;
        this.titleOptions = "修改选项";
      });
    },
    /** 提交按钮 */
    submitOptionsForm: function () {
      this.$refs["formOptions"].validate(valid => {
        if (valid) {
          if (this.formOptions.id != undefined) {
            updateOptions(this.formOptions).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openOptions = false;
              this.getList();
            });
          } else {
            addOptions(this.formOptions).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openOptions = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleOptionsDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除选项编号为"' + ids + '"的数据项？').then(function () {
        return delOptions(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    }
  }
};
</script>
