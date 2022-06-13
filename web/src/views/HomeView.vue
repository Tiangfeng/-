<template>
<!--  功能区域-->
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>导入</el-button>
      <el-button>导出</el-button>
    </div>

<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px; margin-bottom: 2px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable />
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="nickname" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
          >编辑</el-button
          >
          <el-popconfirm title="确定要删除这一项吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button link type="primary" size="small" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

<!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :pager-count="11"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          background
      />

      <el-dialog
          v-model="dialogVisible"
          title="新增"
          width="30%"
      >
<!--        表单-->
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" style="width: 80%" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%" />
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男" size="small">男</el-radio>
            <el-radio v-model="form.sex" label="女" size="small">女</el-radio>
            <el-radio v-model="form.sex" label="未知" size="small">未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save"
            >确认</el-button
            >
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

import request from '@/util/request';

export default {
  name: 'HomeView',
  components: {

  },
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: '',
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("http://localhost:9090/user", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
    },
    save() {
      if (this.form.id) {
        request.put("http://localhost:9090/user", this.form).then(
            res => {
              console.log(res);
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "更新成功"
                });
                this.load();
                this.dialogVisible = false;
              } else {
                this.$message({
                  type: "error",
                  message: "更新失败"
                });
              }
            }
        )
      } else {
        request.post("http://localhost:9090/user", this.form).then(res => {
          console.log(res);
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            });
            this.load();
            this.dialogVisible = false;
          } else {
            this.$message({
              type: "error",
              message: "新增失败"
            });
          }
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum;
      this.load();
    },
    handleDelete(id) {
      console.log(id);
      request.delete("http://localhost:9090/user/" + id).then(
          res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "删除成功"
              });
              this.load();
            } else {
              this.$message({
                type: "error",
                message: "删除失败"
              });
            }
          }
      )
    }

  }
}
</script>
