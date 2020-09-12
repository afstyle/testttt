<template>
  <div>
    <Row type="flex" justify="end" style="margin: 10px 0 20px;">
      <span style="margin-right: auto">
        <Button type="primary" icon="md-add" @click="form = {};formModal = true;">新增</Button>
        <Button type="primary" icon="md-trash" @click="list">批量删除</Button>
      </span>
      <Button type="primary" icon="md-search" @click="list">查询</Button>
    </Row>
    <Table max-height="650" :columns="columns" :data="tableData" :loading="loading">
      <template slot-scope="{ row, index }" slot="action">
        <Button type="primary" size="small" @click="get(row.id)" style="margin-right: 5px">修 改</Button>
        <Button type="error" size="small" @click="deletes(row.id)">删 除</Button>
      </template>
      <template slot-scope="{ row, index }" slot="level">
        {{options.level | optionTurn(row.level)}}
      </template>
      <template slot-scope="{ row, index }" slot="charge">
        {{options.charge | optionTurn(row.charge)}}
      </template>
      <template slot-scope="{ row, index }" slot="status">
        {{options.status | optionTurn(row.status)}}
      </template>
    </Table>
    <Pagination ref="page" v-bind:list="list" />

    <!-- 新增修改modal -->
    <Modal v-model="formModal" :mask-closable="false" :loading="modalLoading" :ok-text="form && form.id ? '保存' : '新增'" :title="form && form.id ? '修改' : '新增'" @on-ok="formValidate">
      <Form ref="form" :model="form" :rules="rule" :label-width="80">
        <FormItem label="名称" prop="name">
          <Input v-model="form.name" placeholder="请输入..." />
        </FormItem>
        <FormItem label="概述" prop="summary">
          <Input v-model="form.summary" placeholder="请输入..." />
        </FormItem>
        <FormItem label="时长" prop="time">
          <Input v-model="form.time" placeholder="请输入..." />
        </FormItem>
        <FormItem label="价格（元）" prop="price">
          <Input v-model="form.price" placeholder="请输入..." />
        </FormItem>
        <FormItem label="封面" prop="image">
          <Input v-model="form.image" placeholder="请输入..." />
        </FormItem>
        <FormItem label="级别" prop="level">
          <Select v-model="form.level">
            <Option v-for="item in options.level" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="收费" prop="charge">
          <Select v-model="form.charge">
            <Option v-for="item in options.charge" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="状态" prop="status">
          <Select v-model="form.status">
            <Option v-for="item in options.status" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="报名数" prop="enroll">
          <Input v-model="form.enroll" placeholder="请输入..." />
        </FormItem>
        <FormItem label="顺序" prop="sort">
          <Input v-model="form.sort" placeholder="请输入..." />
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script>
  import Pagination from "../components/commons/pagination";

  export default {
    name: 'business-course',
    components: { Pagination },
    mounted() {
      let _this = this;
      _this.list();
    },
    data() {
      return{
        // loading
        loading: false,
        modalLoading: true,

        // table
        columns: [
          {
            title: 'id',
            key: 'id'
          },
          {
            title: '名称',
            key: 'name'
          },
          {
            title: '概述',
            key: 'summary'
          },
          {
            title: '时长',
            key: 'time'
          },
          {
            title: '价格（元）',
            key: 'price'
          },
          {
            title: '封面',
            key: 'image'
          },
          {
            title: '级别',
            slot: 'level'
          },
          {
            title: '收费',
            slot: 'charge'
          },
          {
            title: '状态',
            slot: 'status'
          },
          {
            title: '报名数',
            key: 'enroll'
          },
          {
            title: '顺序',
            key: 'sort'
          },
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        tableData: [],

        // modal
        formModal: false,

        // form
        form: {},
        rule: {
          name: [
            { required: true, message: '名称不能为空', trigger: 'blur' },
            { type: 'string', min: 1, max: 50, message: '填写长度在1~50位', trigger: 'blur' }
          ],
          summary: [
            { type: 'string', min: 1, max: 2000, message: '填写长度在1~2,000位', trigger: 'blur' }
          ],
          image: [
            { type: 'string', min: 1, max: 100, message: '填写长度在1~100位', trigger: 'blur' }
          ],
        },
        options: {
          level: COURSE_LEVEL,
          charge: COURSE_CHARGE,
          status: COURSE_STATUS,
        },
      }
    },
    methods: {
      /**
       * 查询
       */
      list() {
        let _this = this;
        _this.loading = true;
        _this.Http.post('business/admin/course/listCourse', {
          page: _this.$refs.page.pageNum,
          size: _this.$refs.page.pageSize
        }).then((res)=>{
          _this.loading = false;
          let result = res.result;
          _this.tableData = result.list;
          _this.$refs.page.total = result.total;
        })
      },

      /**
       * 表单验证
       */
      formValidate() {
        let _this = this;
        _this.$refs['form'].validate((valid) => {
          if (valid) {
            _this.save();
          } else {
            _this.modalSubmitFail(_this);
          }
        })
      },

      /**
       * 保存
       */
      save() {
        let _this = this;
        _this.Http.post('business/admin/course/saveCourse', _this.form).then((res)=>{
          if (res.success) {
            _this.$Message.success(res.message);
            _this.formModal = false;
            _this.list();
          } else {
            _this.modalSubmitFail(_this, res.message);
          }

        });
      },

      /**
       * 获取
       * @param id
       */
      get(id) {
        let _this = this;
        _this.formModal = true;
        _this.Http.get('business/admin/course/getCourse/' + id).then((res)=>{
          _this.form = res.result;
        })
      },

      /**
       * 删除
       * @param id
       */
      deletes(id) {
        let _this = this;
        _this.$Modal.confirm({
          title: '确认',
          content: '<p>是否确认删除此项</p>',
          onOk: () => {
            _this.Http.delete('business/admin/course/deleteCourse/' + id).then((res)=>{
              if (res.success) {
                _this.$Message.success(res.message);
                _this.list();
              } else {
                _this.$Message.error(res.message);
              }
            })
          }
        });
      },

      /**
       * 失败
       * @param t
       * @param msg
       */
      modalSubmitFail(t, msg) {
        t.$Message.error(msg || '表单验证失败!');
        t.modalLoading = false;
        t.$nextTick(() => {
          t.modalLoading = true;
        })
      },
    }
  }
</script>