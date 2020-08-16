<template>
  <div>
    <Row type="flex" justify="end" style="margin: 10px 0 20px;">
      <span style="margin-right: auto">
        <Button type="primary" icon="md-add" @click="form = {};addModal = true;">新增</Button>
        <Button type="primary" icon="md-trash" @click="list">批量删除</Button>
      </span>
      <Button type="primary" icon="md-search" @click="list">查询</Button>
    </Row>
    <Table max-height="650" :columns="columns" :data="tableData" :loading="loading">
      <template slot-scope="{ row, index }" slot="action">
        <Button type="primary" size="small" @click="get(row.id)" style="margin-right: 5px">修 改</Button>
        <Button type="error" size="small" @click="deletes(row.id)">删 除</Button>
      </template>
    </Table>
    <Pagination ref="page" v-bind:list="list" />

    <!-- 新增修改modal -->
    <Modal v-model="addModal" :mask-closable="false" :loading="modalLoading" ok-text="提 交" title="新增" @on-ok="formValidate">
      <Form ref="form" :model="form" :rules="rule" :label-width="80">
        <FormItem label="名称" prop="name">
          <Input v-model="form.name" placeholder="请输入..."></Input>
        </FormItem>
        <FormItem label="课程ID" prop="courseId">
          <Input v-model="form.courseId" placeholder="请输入..."></Input>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script>
    import Pagination from "../components/commons/pagination";

    export default {
        name: 'chapter',
        components: { Pagination },
        mounted() {
            let _this = this;
            _this.list();
        },
        data() {
            return{
                loading: false,
                modalLoading: true,
                columns: [
                    {
                        title: 'ID',
                        key: 'id'
                    },
                    {
                        title: '名称',
                        key: 'name'
                    },
                    {
                        title: '课程ID',
                        key: 'courseId'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        align: 'center'
                    }
                ],
                tableData: [],
                addModal: false,
                form: {
                    name: '',
                    courseId: ''
                },
                rule: {
                    name: [
                        { required: true, message: '名称不能为空', trigger: 'blur' }
                    ],
                    courseId: [
                        { required: true, message: '课程ID不能为空', trigger: 'blur' }
                    ],
                }
            }
        },
        methods: {
            list() {
                let _this = this;
                _this.loading = true;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/listChapter', {
                    page: _this.$refs.page.pageNum,
                    size: _this.$refs.page.pageSize
                }).then((response)=>{
                    console.log('result = ', response);
                    _this.loading = false;
                    let result = response.data.result;
                    _this.tableData = result.list;
                    _this.$refs.page.total = result.total;
                })
            },
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
            save() {
                let _this = this;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/saveChapter', _this.form).then((response)=>{
                    console.log('result = ', response);
                    let data = response.data;
                    if (data.success) {
                        _this.$Message.success(data.message);
                        _this.addModal = false;
                        _this.list();
                    } else {
                        _this.modalSubmitFail(_this, data.message);
                    }

                });
            },
            get(id) {
                let _this = this;
                _this.addModal = true;
                _this.$ajax.get('http://127.0.0.1:9000/business/admin/chapter/getChapter/' + id).then((response)=>{
                    console.log('result = ', response);
                    _this.form = response.data.result;
                })
            },
            deletes(id) {
                let _this = this;
                _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/deleteChapter/' + id).then((response)=>{
                    console.log('result = ', response);
                    let data = response.data;
                    if (data.success) {
                        _this.$Message.success(data.message);
                        _this.list();
                    } else {
                        _this.$Message.error(data.message);
                    }
                })
            },
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